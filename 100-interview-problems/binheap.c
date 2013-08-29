#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "binheap.h"

struct Heap {
   int capacity;
   int size;
   ElemType *elements;
};

/* Indices start from 0 */
/*
int getParent(int i) {
   return (i - 1) / 2;
}

int getLeft(int i) {
  return 2 * i + 1;
}

int getRight(int i) {
  return 2 * i + 2;
}
*/

/* We could reserve pq->elements[0] as a sentinel, 
 * and the indices start from 1
 */
int getParent(int i) {
   return i / 2;
}

int getLeft(int i) {
   return 2 * i;
}

int getRight(int i) {
   return 2 * i + 1;
}

int isEmpty(PriorityQueue pq) {
   if (pq == NULL) {
      printf("Invalid input - NULL priority queue!");
      return -1;
   }
   else {
      if (pq->size == 0) return 1;
      else return 0;
   }
}

int isFull(PriorityQueue pq) { 
   if (pq == NULL) {
      printf("Invalid input - NULL priority queue!");
      return -1;
   }
   else {
      if (pq->size == pq->capacity) return 1;
      else return 0;
      /* check the case pq->size > pq->capacity ?? */
   }
}

PriorityQueue initialize(int maxElements) {
   PriorityQueue pq;

   pq = malloc(sizeof(struct Heap));
   if (NULL == pq)
      printf("Out of space!");
   else {
      pq->elements = malloc((maxElements + 1) * sizeof(ElemType));
      if (pq->elements == NULL) {
         printf("Out of space!");
         pq = NULL;
      }
      else {
         pq->capacity = maxElements;
         pq->size = 0;
      }
   }

   return pq;
}

void destroy(PriorityQueue pq) {
   if (NULL == pq)
      printf("The priority queue is already NULL!\n");
   else {
      free(pq);
      pq = NULL;
      printf("Successfully destroyed the priority queue.\n");
   }
}

void makeEmpty(PriorityQueue pq) {
   if (NULL != pq)
      free(pq);

   pq = initialize(0);
}

void insert(ElemType x, PriorityQueue pq) {
   int i;

   if (isFull(pq)) {
      printf("The priority queue is full!\n");
      return;
   }

   /* We are now constructing a min heap. */
   for (i = ++pq->size; pq->elements[i / 2] > x; i /= 2)
      /* if parent > child (current position, NOT current value), then exchange */
      pq->elements[i] = pq->elements[i / 2];

   pq->elements[i] = x;
}

int deleteMin(PriorityQueue pq) {
   int i, child;

   int minElement, lastElement;

   if (isEmpty(pq)) {
      printf("The priority queue is EMPTY!");
      return pq->elements[0];
   }

   minElement = pq->elements[1];
   /* We initially remove the last element from the heap. */
   lastElement = pq->elements[pq->size--];

   for (i = 1; i * 2 <= pq->size; i = child) {
      child = i * 2;

      /* check if the parent i has only one child or two children. */
      if (child != pq->size && pq->elements[child + 1] < pq->elements[child])
         child++;

      if (lastElement > pq->elements[child])
         pq->elements[i] = pq->elements[child];
      else
         break;
   }
   pq->elements[i] = lastElement;
   return minElement;
}

int findMin(PriorityQueue pq) {
   if (pq == NULL) {
      printf("The priority queue is NULL!");
      return INT_MIN;
   }
   else {
      if (isEmpty(pq)) {
         printf("The priority queue is EMPTY!");
         return pq->elements[0];
      }
      else {
         return pq->elements[1];
      }
   }
}

/* test routine */
int main(void) {
   PriorityQueue pq = initialize(10);

   char c;
   int i = 0;
   while (i < 10 && (c = getchar()) != '\n') {
      if (c == ' ') continue;
      else {
         insert(c - '0', pq);
         i++;
      }
   }

   printf("The current min is: %d\n", findMin(pq));
   printf("Delete the current min? (y/n): ");
   scanf("%c", &c);
   if (c == 'y') {
      deleteMin(pq);
      printf("Deleted! The current min is: %d\n", findMin(pq));
   }
   else if (c == 'n')
      printf("The current min is: %d\n", findMin(pq));
}
