/* 1.2 Implement a function #void reverse(char* str)# in C or C++,
 * which reverses a null-terminated string.
 */

#include <stdio.h>
#include <stdlib.h>

#define MAX_LEN 100

void reverse(char *str);
void printStr(char *str);

/* Test routine */
int main(void) {
   char *str, c, *start;
   str = malloc(MAX_LEN * sizeof(char));
   start = str;

   int i = 0;
   while ((i < MAX_LEN - 1) && (c = getchar()) != '\n') {
      *str++ = c;
      i++;
   }
   *str = '\0';

   printf("The original string is: ");
   printStr(start);
   printf("reversing...\n");
   reverse(start);
   printf("The reversed string is: ");
   printStr(start);

}

void printStr(char *str) {
   while (*str != '\0')
      printf("%c", *str++);
   printf("\n");
}

void reverse(char *str) {
   if (str) {
      /* locate the end of this string */
      char *end, tmp, *ancor;
      ancor = end = str;
      // NOT *end++
      while (*(++end) != '\0')
         ;
      //while (*end) {
      //   end++;
      //} 

      end--;

      /* swap */
      while (str < end) {
         tmp = *end;
         *end = *str;
         *str = tmp;

         str++;
         end--;
      }
   }
   else
      printf("The string is NULL!");
}
