/* P0204: Perid in prefixes/
 * */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define MAX_STR_LEN 1000000

// use a global variable storing in the stack to reduce time complexity.
char str[MAX_STR_LEN]; 
// r[0] is kept a sentinel
int r[MAX_STR_LEN];

/* notes:
 * 1. the longth of A is at most MAX_STR_LEN / 2.
 * 2. recursive? find_period();
 * 3. DP might be a better choice, we could solve and record solutions of
 *    substr, and construct the solutions with the solved ones directly.
 * */

int solve(int len);
void init(int len);
int cmp(int period, int k);

int main() {
  int n;

  int len;
  int count = 1;
  int i;
  while (1) {
    scanf("%d", &n);

    if (n == 0) break;

    scanf("%s", str);
    len = strlen(str);

    printf("Test case #%d\n", count++);
    for (i = 2; i <= len; i++) {
      int res = solve(i);
      if (res > 0)
        printf("%d %d\n", i, res);
    }
    printf("\n");
  }

  exit(0);
}

void init(int len) {
  int i;
  for (i = 0; i <= len; i++)
    r[i] = INT_MIN;

  //if (str[0] == str[1]) r[1] = 2;

  return;
}

int solve(int len) {
  int max = INT_MIN;
  
  //printf("len: %d", len);
  // the length of A: [1, len / 2]
  int i;
  for (i = len / 2; i >= 1; i++) {
  //for (i = 1; i <= len; i++) {
    if (len % i != 0) break;

    // the possible number of repeations
    int j = len / i;
    if (r[i - 1] > 0) return j * r[i - 1];

    //printf("i: %d, j: %d", i, j);
    int tmp = cmp(i, j);
    //printf("%d ", tmp);
    if (tmp > max) {
      max = tmp;
      r[len - 1] = max; 
    }
  }

  return max;
}

int cmp(int period, int k) {
  int i;
  for (i = 1; i < k; i++) {
    int next = i * period;
    int j;
    for (j = 0; j < period; j++) {
      if (str[j] != str[next + j])
        return INT_MIN;
    }
  }

  return k;
}
