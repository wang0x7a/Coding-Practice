#include <stdio.h>
#include <stdlib.h>
#include "bst.h"

struct TreeNode {
   int m_nValue;
   SearchTree m_pLeft;   // in doubly linked list, it is the preceed pointer
   SearchTree m_pRight;  // and this is the next pointer.
};

SearchTree insert(int x, SearchTree t);
SearchTree toDoubleList(SearchTree t);
void helper(PtrToSearchTree head, PtrToSearchTree tail, SearchTree t);
void printList(SearchTree t);

int main() {
   printf("Please insert the elements of a binary search tree:\n");

   SearchTree tree;
   char c;
   while ((c = getchar()) != '\n') {
      if (c == ' ') continue;
      else tree = insert(c, tree);
   }

   // convert BST to a doubly linked list
   tree = toDoubleList(tree);

   printList(tree); 
   free(tree);   

   printf("\n");
   return 0;
   exit(0);
}

SearchTree insert(int x, SearchTree t) {

   if (t == NULL) {
      t = malloc(sizeof(struct TreeNode));
      if (t == NULL)
         printf("Out of space!!!");
      else {
         t->m_nValue = x;
         t->m_pLeft = t->m_pRight = NULL;
      }
   }
   /* Modify the subtrees accordingly */
   else if (x < t->m_nValue)
      t->m_pLeft = insert(x, t->m_pLeft);
   else if (x > t->m_nValue)
      t->m_pRight = insert(x, t->m_pRight);
   /* Else x is in the tree already; we'll do nothing. */  

   /* IMPORTNAT!: we need to return the modified subtrees */
   return t;
}

SearchTree toDoubleList(SearchTree t) {
   // head and tail are locally global vars,
   // tracking the subtrees head and tail, and the original tree finally
   SearchTree head, tail;
   helper(&head, &tail, t);
   return head;
}

void helper(PtrToSearchTree head, PtrToSearchTree tail, SearchTree root) {
   // local head and tail for a subtree
   SearchTree hd, tl;

   // basic "return":
   // given a NULL node, its head and tail should be NULL as well
   // Hence, only leaf nodes will have NULL heads and tails
   if (NULL == root) {
      *head = NULL, *tail = NULL;
      return;
   }

   // Search head in the left child
   // return the head and tail of the left child
   // reserve head and use a local var to track local tail
   helper(head, &tl, root->m_pLeft);
   // Search tail in the right child
   // return the head the tail of the right child
   helper(&hd, tail, root->m_pRight);

   if (tl != NULL) {
      tl->m_pRight = root;
      root->m_pLeft = tl;
   }
   else {
      // if tl in the subtree of root is NULL, then the root should be
      // the left-most node of the whole tree
      *head = root;
   }

   if (hd != NULL) {
      hd->m_pLeft = root;
      root->m_pRight = hd;
   }
   else {
      *tail = root;
   }
}

void printList(SearchTree t) {
   SearchTree current;

   for(current = t; current != NULL; current = current->m_pRight) {
      printf("%c ", current->m_nValue);
   }
}
