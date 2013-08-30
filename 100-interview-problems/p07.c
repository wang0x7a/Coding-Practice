/* Check if two linked lists intersect with each other,
 *
 * Extension:
 * 1) What if there is a cycle in te linked list?
 * 2) Find out the point of intersection. 
 * */

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

struct Node;
typedef struct Node *PtrToNode;
typedef struct Node *Position;
typedef struct Node *List;

struct Node {
   int nData;
   List pNext;
};

int isIntersected(List l1, List l2);
Position getLast(List list);
Position isCircular(List list);
Position getBeginning(List list);

/* test routine */
int main(void) {
   List list = malloc(sizeof(struct Node));
   list->nData = INT_MIN;
   list->pNext = NULL;
   List head = list;

   char c;
   printf("please enter the elements of the list: \n");
   while ((c = getchar()) != '\n') {
      if (c == ' ') continue;
      else {
         Position node = malloc(sizeof(struct Node));
         node->nData = c - '0';
         node->pNext = NULL;
         list->pNext = node;
         list = node;
      }
   }
   printf("The last element is %d: \n", list->nData);
   list->pNext = head;
   printf("Is the current list circular? %d\n", isCircular(head) != NULL);
}
/*
List createCircularList(int x, List list) {
   if (list == NULL) {
      list = malloc(sizeof(struct Node));
      if (list == NULL)
         printf("Out of space!");
      else {
         list->nData = x;
         list->pNext = NULL;
      }
   }
   else 
}
*/

/* There is no cycle in either of the linked list */
int isIntersected(List l1, List l2) {
   //return getLast(l1) == getLast(l2);
   Position flag1 = isCircular(l1);
   Position flag2 = isCircular(l2);

   if ((flag1 == NULL) && (flag2 == NULL))
      return getLast(l1) == getLast(l2);
   else if ((flag1 != NULL && flag2 == NULL) || (flag1 == NULL && flag2 != NULL)) 
      return 0;
   else {
      Position start = flag2;
      while (flag1 != flag2) {
         flag2 = flag2->pNext;
         if (start == flag2) return 0;
      }
      return 1;
   }
}

Position getLast(List list) {
   if (list == NULL) {
      printf("The list is NULL, please initialize it!");
      return NULL;
   }

   Position current = list;
   while (current->pNext)
      current = current->pNext;

   return current;
}

Position isCircular(List list) {
   if (list == NULL) {
      printf("The input list is NULL!");
      return NULL;
   }

   Position fast, slow;
   fast = slow = list;

   /* It would be a bad idea to return a NULL, instead of the last node. */
   while (fast->pNext != NULL) {
      slow = slow->pNext;
      fast = fast->pNext->pNext;
      if (slow == fast) return slow;
   }

   return NULL;
   /*
   Position fast, slow;
   if (list == NULL || list->pNext == NULL)
      return 0;

   slow = list->pNext;
   fast = list->pNext->pNext;

   while (fast != NULL) {
      if (slow == fast) return 1;
      else {
         slow = slow->pNext;
         fast = fast->pNext->pNext;
      }
   }

   return 0;
   */
}
