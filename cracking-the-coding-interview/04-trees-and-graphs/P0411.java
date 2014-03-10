/* Binary tree traversal without recursion.
 * */

import java.util.Stack;

public class P0411 {

  public class Node {
    int value;
    Node left, right;

    public Node(int value) {
      this.value = value;
    }
  }

  public void InOrder(Node root) {
    if (root == null)
      return;

    Stack<Node> stack = new Stack<Node>();
    Node current = root;
    stack.push(root);
    // we duplicate the root when initializing
    // stack.pop() => the top node on the stack
    // current => the current node, pointing to the node we want to process

    Node tmp;

    int stackSize = 1;
    while (stackSize > 0) {
      if (current.left == null) {
        System.out.println(current.value);
        //current = current.right;
        tmp = stack.pop();
        tmp.left = null;
        stack.push(tmp);
        if (current.right == null) {
          current = stack.pop();
          stackSize--;
        }
        else {
          current = current.right;
          // discard the right child once we use it, not pushing it onto the stack
          //stack.push(current);
          //stackSize++;
        }
      }
      else {
        stack.push(current);
        stackSize++;
        current = current.left;
      }
      /*
      else if (current.right == null) {
        current = stack.pop();
        stackSize--;
        System.out.println(current.value);
        current = current.right;
      }
      */
    }
  }
  
  public Node buildTree() {
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.left.right.right = new Node(9);
    root.right.left = new Node(6);
    root.right.right= new Node(10);
    root.left.left.left = new Node(7);
    root.left.left.left.left = new Node(8);
    //
    return root;
   }

  public static void main(String[] args) {
    P0411 p0411 = new P0411();
    Node root = p0411.buildTree();

    p0411.InOrder(root);
  }
}
