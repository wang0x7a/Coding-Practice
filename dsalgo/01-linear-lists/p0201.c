/* P0201: Spelling checking
 * */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAX_DICT_LEN 10000
#define MAX_WORD_LEN 20 

typedef char *Word;
typedef Word *Dict; 

Dict build_dict();
Dict lookup(Word word, Dict dict);
int calc_dist(Word a, Word b);
int calc_dist_helper(Word a, int len_a, Word b, int len_b);
int calc_dist_helper2(Word a, int len_a, Word b, int len_b);
int min3(int a, int b, int c);

int main() {
  Dict dict = build_dict();

  while(1) {
    Word word = malloc(sizeof(char) * MAX_WORD_LEN);
    scanf("%s", word);

    if (*word == '#')
      break;

    Dict cand = lookup(word, dict);
    if (cand[0] == NULL)
      printf("%s:\n", word);
    else if (cand[0] == word)
      printf("%s is correct\n", word);
    else {
      printf("%s:", word);
      int i;
      for (i = 0; cand[i] != NULL; i++) {
        printf(" %s", cand[i]);
      }
      printf("\n");
    }
  }

  exit(0);

}

Dict build_dict() {
  Dict dict = malloc(sizeof(Word) * MAX_DICT_LEN);

  int i;
  for (i = 0; ; i++) {
    Word word = malloc(sizeof(char) * MAX_WORD_LEN);
    scanf("%s", word);
    if (*word == '#') {
      dict[i] = NULL;
      break;
    }

    dict[i] = word;
  }

  return dict;
}

Dict lookup(Word word, Dict dict) {
  Dict cand = malloc(sizeof(Word) * MAX_DICT_LEN);
  cand[0] = NULL;

  int i, j;
  for (i = 0, j = 0; dict[i] != NULL; i++) {
    int dist = calc_dist(word, dict[i]);
    if (dist == 0) {
      j = 0;
      cand[j] = word;
      break;
    }
    else if (dist == 1) {
      cand[j++] = dict[i];
    }
  }
  cand[++j] = NULL;

  return cand;
}

int calc_dist(Word a, Word b) {
  int len_a = strlen(a);
  int len_b = strlen(b);


  return calc_dist_helper(a, len_a, b, len_b);
}

int calc_dist_helper2(Word a, int len_a, Word b, int len_b) {
}

int calc_dist_helper(Word a, int len_a, Word b, int len_b) {
  if (len_a == 0) return len_b;
  if (len_b == 0) return len_a;

  if (abs(len_a - len_b) > 1) return 2;

  int cost = a[len_a - 1] == b[len_b - 1] ? 0 : 1;

  int x = calc_dist_helper(a, len_a - 1, b, len_b) + 1;
  int y = calc_dist_helper(a, len_a, b, len_b - 1) + 1;
  int z = calc_dist_helper(a, len_a - 1, b, len_b - 1) + cost;

  return min3(x, y, z);
}

int min3(int x, int y, int z) {
  if (x <= y) return x <= z ? x : z;
  else return y <= z ? y : z;
}
