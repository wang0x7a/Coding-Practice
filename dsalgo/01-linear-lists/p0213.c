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

  int index = 80;
  int i;
  /* IMPORTANT: i++ is exected before index > 0 */
  for (i = 1; index > 0; i++) {
    group[i] = group[i - 1] + (int)log10(i * 1.0) + 1; 
    index -= group[i];
  } // for

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
  while (n > 0) {
    j %= 10;
    n--;
  }

  printf("%d\n", j);

}
