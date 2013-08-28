#ifndef _BINHEAP_H_
#define _BINHEAP_H_

typedef int ElemType;

struct Heap;
typedef struct Heap *PriorityQueue;

PriorityQueue initialize(int maxElements);
void destroy(PriorityQueue pq);
void makeEmpty(PriorityQueue pq);
void insert(ElemType x, PriorityQueue pq);
ElemType deleteMin(PriorityQueue pq);
ElemType findMin(PriorityQueue pq);
int isEmpty(PriorityQueue pq);
int isFull(PriorityQueue pq);

int getParent(int i);
int getLeft(int i);
int getRight(int i);

#endif   /* _BINHEAP_H_ */
