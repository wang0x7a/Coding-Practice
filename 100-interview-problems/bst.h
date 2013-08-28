#ifndef _BST_H_
#define _BST_H_

struct TreeNode;
typedef struct TreeNode *Position;
typedef struct TreeNode *SearchTree;
typedef SearchTree *PtrToSearchTree;

SearchTree insert(int x, SearchTree t);
SearchTree toDoubleList(SearchTree tree);

#endif /* _BST_H */
