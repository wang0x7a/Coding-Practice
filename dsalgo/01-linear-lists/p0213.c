#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <math.h>

/* Assume the max group index is x, then
 * n * (1 + x) / x < INT_MAX 
 * */
#define MAX_GROUP 32000

void construct_groups(int *group);
int solve(int index, int group[]);

int main() {
  //int group[MAX_GROUP];
  int *group = malloc(sizeof(int) * MAX_GROUP); 

  construct_groups(group);

  int case_num, index;
  scanf("%d", &case_num);

  while (case_num-- > 0) {
    scanf("%d", &index);
    printf("%d\n", solve(index, group));
  }

  return 0;
}

void construct_groups(int *group) {
  group[0] = 0;

  int i;
  int acc = 0;
  /* IMPORTANT: i++ is exected before index > 0 */
  for (i = 1; acc >= 0; i++) { 
    group[i] = group[i - 1] + (int)log10(i * 1.0) + 1;
    acc += group[i];
  } 
}

int solve(int index, int* group) {
  int i;
  for (i = 1; index > 0; i++)
    index -= group[i];

  /* That the for loop stops means that the traget digit is in
   * group[i] with the updated index [1, group[i]]
   * */
  index += group[--i];

  /* Now, search the digit with the corresponding index in group[i].
   * */
  int j;
  for (j = 1; group[j] < index; j++)
    ;

  /* The digit should be some digit of the integer j*/
  index -= group[j - 1];
  int n = (int)log10(j * 1.0) + 1 - index + 1; 
  /* reverse index for mod op: for a 4-digit num, index = 1 => n = 4 */
  j /= pow(10, n - 1);
  j %= 10;

  return j;
}
