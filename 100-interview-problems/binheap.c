#include <stdio.h>
#include <stdlib.h>
#include "binheap.h"

struct Heap {
   int capacity;
   int size;
   ElemType *elements;
};

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
