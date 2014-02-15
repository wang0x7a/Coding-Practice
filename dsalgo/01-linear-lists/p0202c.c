/* P0202: Card Stacking
 * */

#include <stdio.h>
#include <stdlib.h>

#define MAX_CARD_NUM 50000
#define MAX_DECK_SIZE 100000 

int deck[MAX_DECK_SIZE];
int cand[MAX_CARD_NUM];

void move(int deck[], int cand[], int deck_size, int player_num, int skip);
void print_deck(int deck[], int deck_size);
int next_index(int deck[], int deck_size, int curr_index, int skip);
int next_index2(int deck[], int deck_size, int curr_index, int skip);
void sort(int cards[], int card_num);
void sort_helper(int cards[], int left, int right);
void swap(int cards[], int i, int j);

int main() {
  int player_num, deck_size, skip;

  scanf("%d %d %d", &player_num, &deck_size, &skip);

  //int *deck = init_deck(card_num); 
  // no need to initialize a deck, we could use -1 to mark a card
  // has been dealt

  int i;
  
  /*
  for (i = 0; i < deck_size; i++)
    printf("%d ", deck[i]);
  printf("\n");
  */

  
  int cards_per_player = deck_size / player_num;

  move(deck, cand, deck_size, player_num, skip);

  sort(cand, deck_size / player_num);

  for (i = 0; i < deck_size / player_num; i++)
    printf("%d\n", cand[i]);

  exit(0);
}

void move(int deck[], int cand[], int deck_size, int player_num, int skip) {
  int cards_per_player = deck_size / player_num;

  int index = 0, count = 1;
  //int index = 1, count = 1;
  int i = 0;
  while (i < cards_per_player) {
  //while (count < deck_size) {
    if (count % player_num == 0) {
      //printf("bessie:%d ", count % 3);
      // no need to check this, since we already checked in next_index
      //if (deck[index] != -1)
      cand[i++] = index + 1; 
      //cand[i++] = index; 
    }

    deck[index] = -1;
    count++;

    if (i < cards_per_player)
      //index = next_index2(deck, deck_size, index, skip);
      index = next_index(deck, deck_size, index, skip);
  }
}

int next_index(int deck[], int deck_size, int curr_index, int skip) {
  // move forward with one step
  curr_index = (curr_index + 1) % deck_size;
  //printf("before:%d ", curr_index);
  
  while (skip >= 0) {
    if (skip == 0) {
      if (deck[curr_index] == -1) {
        curr_index = (curr_index + 1) % deck_size;
        continue;
      }
      else
        break;
    }

    if (deck[curr_index] != -1)
      skip--;
    curr_index = (curr_index + 1) % deck_size;
  }
  //printf("after:%d ", curr_index);
  //printf("\n");
  return curr_index;
}

int next_index2(int deck[], int deck_size, int curr_index, int skip) {
  int count = 0;
  while (1) {
    if (deck[curr_index] != -1 && (++count == skip + 1))
      return curr_index;
    if (++curr_index > deck_size)
      curr_index = 1;
  }
}

void sort(int cards[], int card_num) {
  sort_helper(cards, 0, card_num - 1);
}

void sort_helper(int cards[], int left, int right) {
  int i, last;

  if (left >= right)
    return;
  last = left;
  for (i = left + 1; i <= right; i++)
    if (cards[i] < cards[left])
      swap(cards, ++last, i);
  swap(cards, left, last);
  sort_helper(cards, left, last - 1);
  sort_helper(cards, last + 1, right);

}

void swap(int cards[], int i, int j) {
  int tmp;

  tmp = cards[i];
  cards[i] = cards[j];
  cards[j] = tmp;
}
