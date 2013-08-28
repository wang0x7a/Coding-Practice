#ifndef _STACK_H_
#define _STACK_H_

struct Node;
typedef struct Node *Stack;
typedef struct Node *Position;
typedef int ElemType;

ElemType pop(Stack stack);
Stack push(ElemType x, Stack stack);
ElemType findMin(Stack stack);

#endif /* _STACK_H_ */
