#include <stdio.h>
#include <stdlib.h>
#include "stack.h"

struct Node {
   ElemType value;
   Stack next;
   Position min;       /* The address of the min node */  
};

int main(void) {
   printf("Please enter the elements to initialize the stack:\n");

   Stack stack;
   char c;
   while((c=getchar()) != '\n') {
      if (c == ' ') continue;
      else stack = push(c - '0', stack);
   }

   printf("The minimun value of this stack is: %d\n", findMin(stack));
}

Stack push(ElemType x, Stack stack) {
   Stack top = malloc(sizeof(struct Node));
   if (NULL == top) {
      printf("Fail to create the inserted node.");
   }
   else {
      top->value = x;
      top->next = NULL;
      /*
      if (NULL == stack) {
         top->min = (Position)top;
      }
      else {
         if (x < stack->min->value) {
            top->min = (Position)top;
         }
         else
            top->min = stack->min;
      }
      */
      if (NULL == stack || x < stack->min->value)
         top->min = (Position)top;
      else
         top->min = stack->min;
   }
   printf("Current Min: %d\n", top->min->value);
  
   return top; 
}

ElemType pop(Stack stack) {
   ElemType x;

   if (NULL == stack) {
      printf("Fail with a NULL stack");
      x = -1;
   }
   else {
      x = stack->value;
      stack = stack->next;
   }
   return x;
}

ElemType findMin(Stack stack) {
   if (NULL == stack) {
      printf("Fail with a NULL stack");
      return -1;
   }
   else {
      return stack->min->value;
   }
}
