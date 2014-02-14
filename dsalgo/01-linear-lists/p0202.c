/* P0202: Card Stacking
 * */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int * move(int *cards, int card_num, int player_num, int skip);
int * init_deck(int card_num);
void print_deck(int *deck, int card_num);
int next_index(int *deck, int card_num, int curr_index, int skip);

int main() {
  int player_num, card_num, skip;

  scanf("%d %d %d", &player_num, &card_num, &skip);

  int *deck = init_deck(card_num); 
  int i;
  for (i = 0; i < card_num; i++)
    printf("%d ", deck[i]);
  printf("\n");

  int *cand = move(deck, card_num, player_num, skip);
  for (i = 0; i < card_num / player_num; i++)
    printf("%d ", cand[i]);
  printf("\n");

  exit(0);
}

int * move(int *cards, int card_num, int player_num, int skip) {
  int cards_per_player = card_num / player_num;
  int *cand = malloc(sizeof(int) * cards_per_player);

  int i = 0, index = 0;
  int count = 1;
  while (i < cards_per_player) {
    /*
    if (cards[index] == 0) {
      index = (index + 1) % card_num;
      continue;
    }
    */

    printf("count: %d\n", count);
    if (count == player_num) {
      cand[i++] = cards[index];
      printf("%d\n", cand[i - 1]);
    }
    //else if (count < player_num)
    cards[index] = 0;
    printf("index: %d\n", index);
    print_deck(cards, card_num);

    // update count
    count = (count++) % player_num;
    // update index
    /*
    int j = 0;
    index = (index + 1) % card_num;
    while (j < skip) {
      index = (index + 1) % card_num;
      if (cards[index] != 0) j++;
    }
    */
    // stop immediately when dealing out all cards,
    // to avoid stack overflow in next_index
    if (i < cards_per_player)
      index = next_index(cards, card_num, index, skip);
    printf("index: %d\n", index);
  }

  return cand;
}
int next_index(int *deck, int card_num, int curr_index, int skip) {
  curr_index = (curr_index + 1) % card_num; 
  if (skip == 0) {
    if (deck[curr_index] != 0)
      return curr_index;
    else {
      next_index(deck, card_num, curr_index, skip);
    }
  }

  if (deck[curr_index] == 0)
    next_index(deck, card_num, curr_index, skip);
  else
    next_index(deck, card_num, curr_index, skip - 1);
}

int * init_deck(int card_num) {
  int *deck = malloc(sizeof(int) * card_num);

  int i;
  for (i = 0; i < card_num; i++)
    deck[i] = i + 1;

  return deck;

}

void print_deck(int *deck, int card_num) {
  int i;
  for (i = 0; i < card_num; i++)
    printf("%d ", deck[i]);
  printf("\n");
}
