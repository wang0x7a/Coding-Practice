#include <stdio.h>
#include <ctype.h>

#define MAXLEN 100

void init(int *s);
int atoi(char s[]);
int maxSum(int *s);

int main(void) {
   int s[MAXLEN];
   init(s);

   int i;
   printf("The initial int arrya is:\n");
   for (i = 0; s[i] != '\0'; i++) {
      printf("%d ", s[i]);
   }
   printf("\n");

   printf("The max sum of this array is: %d\n", maxSum(s));
}

int maxSum(int *a) {
   int max = 0, current = 0;
   int i;

   for (i = 0; a[i] != '\0'; i++) {
      current += a[i];

      if (current > max) max = current;
      else if (0 > current) current = 0; 
   }
   return max;
}

int atoi(char s[]) {
   int i, n, sign;

   for (i = 0; isspace(s[i]); i++)
      ;

   sign = (s[i] == '-') ? -1 : 1;
   if (s[i] == '-' || s[i] == '+')
      i++;

   for (n = 0; isdigit(s[i]); i++) {
      n = 10 * n + s[i] - '0';
   }
   //printf("%d\n", sign * n);
   return sign * n;
}

void init(int *s) {
   char c;
   char tmp[6];
   int i = 0, j = 0;

   printf("Please enter the integer numbers, and each number should have fewer than 5 digits:\n");
   while ((c = getchar()) != '\n') {
      if (c == ' ') {
         tmp[j] = '\0';
         s[i++] = atoi(tmp);
         //printf("%d\n", s[i-1]);
         j = 0;
         continue;
      }
      else {
        tmp[j++] = c;
        //printf("%d\n", tmp[j - 1]);
      }
   }
   s[i++] = atoi(tmp);
   //printf("%d\n", s[i-1]);
   s[i] = '\0';
}
