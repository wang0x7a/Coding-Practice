/* Print a binary tree by level.
 * */

import java.util.Queue;
import java.util.List;
import java.util.LinkedList;

public class P0410 {
  private class Node {
    int value;
    Node left, right;

    public Node(int value) {
      this.value = value;
    }
  }

  public void printByLevel(Node root) {
    if (root == null)
      return;

    Node tmp = null;
    Queue<Node> queue = new LinkedList<Node>();
    queue.offer(root);

    while (queue.size() > 0) {
      tmp = queue.poll();
      System.out.print(tmp.value + " ");

      if (tmp.left != null)
        queue.offer(tmp.left);
      if (tmp.right != null)
        queue.offer(tmp.right);
    }

    System.out.println();
  }

  public Node buildTree() {
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.left = new Node(6);
    root.left.left.left = new Node(7);
    root.left.left.left.left = new Node(8);
    //
    return root;
  }

  public static void main(String[] args) {
    P0410 p0410 = new P0410();
    Node root = p0410.buildTree();

    p0410.printByLevel(root);
  }
}
