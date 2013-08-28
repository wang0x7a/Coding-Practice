/* Lessons:
 * 1. Pay attention to the conversion from char to int
 * 2. Difference between pointers and arrays. How to start from the begining
 * 3. Be careful with the ++ and -- in pointers.
 * 4. Insertion in binary trees.
 *    tree->m_pRight = insert(x, tree->m_pRight);
 *    NOT tree = insert(x, tree->m_pRight);
 *
 * */

#include <stdio.h>
#include <stdlib.h>

#define MAX_HEIGHT 10

struct Node;
typedef struct Node *SearchTree;

void printPaths(SearchTree root, int sum);
SearchTree insert(int x, SearchTree tree);
void helper(SearchTree root, int sum, int *path);
void printPath(int *path);

struct Node {
   int m_nValue;
   SearchTree m_pLeft;
   SearchTree m_pRight;
};

int main(void) {
   char c;
   SearchTree tree;
   printf("Please insert nodes of a BST:\n");
   while((c = getchar()) != '\n') {
      if (c == ' ') continue;
      else tree = insert(c - '0', tree);
   }

   printPaths(tree, 15);
}

SearchTree insert(int x, SearchTree tree) {
   if (tree == NULL) {
      tree = malloc(sizeof(struct Node));
      if (tree == NULL)
         printf("Out of space!\n");
      else {
         tree->m_nValue = x;
         tree->m_pLeft = NULL;
         tree->m_pRight = NULL;
      }
   }
   else if (x > tree->m_nValue)
      tree->m_pRight = insert(x, tree->m_pRight);
   else if (x < tree->m_nValue)
      tree->m_pLeft = insert(x, tree->m_pLeft);
   else
      printf("Duplicated node!\n");

   return tree;
}

void printPaths(SearchTree root, int sum) {
   int *path;
   path = malloc(MAX_HEIGHT * sizeof(int));
   if (path == NULL) {
      printf("Out of space!\n");
      return;
   }
   else {
      *path++ = '\0';
      helper(root, sum, path);
   }
}

void helper(SearchTree root, int sum, int *path) {
   sum -= root->m_nValue;
   *path++ = root->m_nValue;
   /* if leaf, then return */
   if (root->m_pLeft == NULL && root->m_pRight == NULL) {
      if (sum == 0) {
         printf("Correct path: ");
         printPath(--path);
      }
      else {
         printf("Wrong path: ");
         //*path = '\0';
         printPath(--path);
      }
      return;
   }
   else {
      helper(root->m_pLeft, sum, path);
      helper(root->m_pRight, sum, path);
   }
}

void printPath(int *path) {
   if (path == NULL) {
      printf("Invalid path!");
   }
   else {
      //while (*path++ != '\0')
      while (*path != '\0')
         printf("%d ", *path--);
   }
   printf("\n");
}
