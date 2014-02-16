#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STR_LEN 10
#define MAX_SUBSTR_LEN 3

typedef char *String;

void insert(char *str, int str_len, char *substr, int substr_len, char *res);

int main() {
  //char str[MAX_STR_LEN];
  //char substr[MAX_SUBSTR_LEN];
  String str = malloc(sizeof(char));
  String substr = malloc(sizeof(char)); 

  while (1) {
    scanf("%s %s", str, substr);
    
    int str_len = strlen(str);
    int substr_len = strlen(substr);
    int res_len = str_len + substr_len;
    //printf("%d\n", res_len);
    char res[res_len];

    insert(str, str_len, substr, substr_len, res);

    int i;
    for (i = 0; i < res_len; i++)
      printf("%c", res[i]);
    printf("\n");
  }
}

void insert(char *str, int str_len, char *substr, int substr_len, 
    char *res) {

  int res_len = str_len + substr_len;

  int i, max_pos;
  for (i = 1, max_pos = 0; i < str_len; i++) {
    if (str[max_pos] < str[i]) max_pos = i;
  }

  //printf("max_pos: %d\n", max_pos);

  for (i = 0; i < res_len; i++) {
    if (i <= max_pos)
      res[i] = str[i];
    else if (i >= max_pos + 1 && i < max_pos + 1 + substr_len)
      res[i] = substr[i - max_pos - 1];
    else
      res[i] = str[i - substr_len];
  }

}
