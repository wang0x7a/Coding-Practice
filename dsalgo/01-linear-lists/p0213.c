#include <stdio.h>
#include <limits.h>
#include <math.h>

/* Assume the max group index is x, then
 * n * (1 + x) / x < INT_MAX 
 * */
#define MAX_GROUP 32000

void main() {
  int group[MAX_GROUP];
  group[0] = 0;

  int i, acc = 0;
  for (i = 1; acc <= INT_MAX; i++) {
    group[i] = group[i - 1] + (int)log10(i) + 1; 
    acc += group[i];
  }

  int index = 19;
  for (i = 1, acc = 0; acc < index; i++)
    acc += group[i];

  index -= group[--i];

}
