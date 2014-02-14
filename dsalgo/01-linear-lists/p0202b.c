/* P0202: Card Stacking
 * */

#include <stdio.h>
#include <stdlib.h>

struct Card;
typedef struct Card *PtrToCard;
typedef struct Card *Deck;

struct Card {
  int num;
  PtrToCard next;
};

Deck move(Deck deck, int skip);
Deck remove_at(Deck deck);
Deck insert_at(Deck deck, int num);
Deck deal(Deck deck, int card_num, int player_num, int skip);
Deck init_deck(int card_num);
void print_deck(Deck deck, int card_num);

int main() {
  int player_num, card_num, skip;

  scanf("%d %d %d", &player_num, &card_num, &skip);

  Deck deck = init_deck(card_num); 
  int i;
  /*
  for (i = 0; i < card_num; i++, deck = deck->next)
    printf("%d ", deck->num);
  printf("\n");
  */

  Deck my_deck = deal(deck, card_num, player_num, skip);
  /*
  for (i = 0; i < card_num / player_num; i++, my_deck = my_deck->next)
    printf("%d ", my_deck->num);
  printf("\n");
  */
  my_deck = my_deck->next;
  while (my_deck != NULL) {
    printf("%d\n", my_deck->num);
    my_deck = my_deck->next;
  }
  printf("\n");

  exit(0);
}

Deck deal(Deck deck, int card_num, int player_num, int skip) {
  int cards_per_player = card_num / player_num;
  Deck my_deck = malloc(sizeof(struct Card)); 
  my_deck->next = NULL;

  int i = 0, count = 1;
  while (i < cards_per_player) {
    if (count % player_num == 0) {
      printf("insert: %d\n", deck->num);
      my_deck = insert_at(my_deck, deck->num);
      i++;
    }

    count++;

    deck = remove_at(deck);
    card_num--;
    print_deck(deck, card_num);
    deck = move(deck, skip);
  }

  return my_deck;
}

Deck move(Deck deck, int skip) {
  int i;
  for (i = 0; i < skip; i++)
    deck = deck->next;

  return deck;
}

Deck remove_at(Deck deck) {
  // remove the current card
  PtrToCard removal = deck->next;
  deck->num = removal->num;
  deck->next = removal->next;
  free(removal);

  return deck;
}

Deck insert_at(Deck deck, int num) {
  //if (deck->next == NULL) {
  /*
  if (deck->num == 0) {
    deck->num = num;
    return deck;
  }
  */

  Deck head = deck;

  PtrToCard card = malloc(sizeof(struct Card));
  card->num = num;

  //for (; deck->next != NULL && num > deck->num; deck = deck->next)
  while (deck->next != NULL) {
    if (deck->num < num && deck->next->num > num)
      break;

    deck = deck->next;
  }
  card->next = deck->next;
  deck->next = card;

  return head;
}

Deck init_deck(int card_num) {
  PtrToCard head = malloc(sizeof(struct Card));
  head->num = 1;
  head->next = NULL;

  PtrToCard current = head;
  PtrToCard next;
  int i;
  for (i = 2; i <= card_num; i++) {
    next = malloc(sizeof(struct Card));
    next->num = i;
    next->next = NULL;

    current->next = next;
    current = next;
  }

  current->next = head;

  return (Deck)head;
  // return the last card in the deck, and deal from the first one
  // and thus, we can save one field in Card to construct a doubly
  // linke list
  //return (Deck)current;
}

void print_deck(Deck deck, int card_num) {
  int i;
  for (i = 0; i < card_num; deck = deck->next, i++)
    printf("%d ", deck->num);
  printf("\n");
}
