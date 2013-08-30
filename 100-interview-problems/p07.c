/* Check if two linked lists intersect with each other,
 *
 * Extension:
 * 1) What if there is a cycle in te linked list?
 * 2) Find out the point of intersection. 
 * */

#include <stdio.h>
#include <stdlib.h>

struct Node;
typedef struct Node *PtrToNode;
typedef struct Node *Position;
typedef struct Node *List;

struct Node {
   int nDate;
   List pNext;
};

int isIntersected(List l1, List l2);
Position getLast(List list);
int isCircular(List list);
Position getBeginning(List list);

/* test routine */

/* There is no cycle in either of the linked list */
int isIntersected(List l1, List l2) {
   return getLast(l1) == getLast(l2);
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

int isCircular(List list) {
   if (list == NULL) {
      printf("The input list is NULL!");
      return 0;
   }

   Position fast, slow;
   fast = slow = list;

   /* It would be a bad idea to return a NULL, instead of the last node. */
   while (fast->pNext != NULL) {
      slow = slow->pNext;
      fast = fast->pNext->pNext;
      if (slow == fast) return 1;
   }

   return 0;
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
