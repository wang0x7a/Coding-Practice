/* 4.6 Design an algorithm and write code to find the first common ancestor
 * of two nodes in a binary tree. Avoid storing additional nodes in a data
 * structure. NOTE: this is not necessarily a binary search tree.
 * */
import java.util.NoSuchElementException;

public class P0406 {
   private class Node {
      int value;
      Node left, right;
      Node parent;

      public Node(int value) {
         this.value = value;
      }
   }

   public Node commonAncestor(Node n, Node m) {
      while (n != null) {
         Node tmp = m;
         while (tmp != null) {
            if (n == tmp) return n;
            tmp = tmp.parent;
         }
         n = n.parent;
      }

      return null;
   }

   public Node commonAncestor(Node root, Node n, Node m) {
      
   }

   public Node buildTree() {
      Node root = new Node(1);
      root.left = new Node(2);
      root.left.parent = root;
      root.right = new Node(3);
      root.right.parent = root;
      root.left.left = new Node(4);
      root.left.left.parent = root.left;
      root.left.right = new Node(5);
      root.left.right.parent = root.left;
      root.right.left = new Node(6);
      root.right.left.parent = root.right;
      root.left.left.left = new Node(7);
      root.left.left.left.parent = root.left.left;
      root.left.left.left.left = new Node(8);
      root.left.left.left.left.parent = root.left.left.left;
      //
      return root;
   }

   public static void main(String[] args) {
      P0406 p0406 = new P0406();
      Node root = p0406.buildTree();
      Node n = root.left.left;
      // Node m = root.left.right;
      Node m = root.left.left.left;
      Node common = p0406.commonAncestor(n, m);
      System.out.println(common.value);
   }
}
