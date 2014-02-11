#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LEN 1000000

typedef char *String;



int main() {
  int n;

  String str;
  while (1) {
    scanf("%d", &n);

    if (n == 0) break;

    str = malloc(sizeof(char) * n);
    scanf("%s", str);
    //printf("%s\n", str);


  }

  return 0;
}

void getPeriod(String str, int len) {
  if (len == 1) return;

  int i;
  for (i = 2; len % i == 0 && len / i > 0; i++) {
    String dest = malloc(sizeof(char) * (MAX_LEN / 2));
    int j;
    int period = len / i;
    for (j = 0; j < i; j++) {
      
    }
  }
}
