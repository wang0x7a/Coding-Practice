/* P0204: Perid in prefixes/
 * */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define MAX_STR_LEN 1000000

// use a global variable storing in the stack to reduce time complexity.
char str[MAX_STR_LEN]; 
int r[MAX_STR_LEN];

void solve(char *str, int len);

int main() {
  int n;

  int len;
  int count = 1;
  int i;
  while (1) {
    scanf("%d", &n);

    if (n == 0) break;

    scanf("%s", str);
    //len = strlen(str);
    //init(len);

    printf("Test case #%d\n", count++);
    solve(str, n);
    /*
    int i;
    for (i = 0; i < n; i++)
      printf("%d ", r[i]);
    */
    printf("\n");
  }

  exit(0);
}

void solve(char *str, int len) {
  r[0] = 0;

  int i = 1;    // index in str
  int j = 0;    // the length of the previous prefix
  while (i < len) {
    if (str[i] == str[j]) {
      j++;
      r[i] = j;
      int p = i + 1 - j;
      if (j % p == 0)
        printf("%d %d\n", i + 1, j / p + 1);
      i++;
    }
    else {
      if (j != 0)
        j = r[j - 1];
      else {
        r[i] = 0;
        i++;
      }
    }
  }
}
