#include <stdio.h>
#include <stdlib.h>
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

PriorityQueue initialize(int maxElements) {
   PriorityQueue pq;

   pq = malloc(sizeof(struct Heap));
   if (NULL == pq)
      printf("Out of space!");
   else {
      pq->elements = calloc(sizeof(maxElements + 1) * sizeof(ElemType));
      if (pg->elements == NULL) {
         printf("Out of space!");
         pg = NULL;
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
   
}
