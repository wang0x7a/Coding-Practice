/* 4.2 Given a sorted (increasing order) array, write an algorithm to create
 * a binary search tree with minimal height.
 * */

public class P0403 {
   private class Node {
      int value;
      Node left, right;

      public Node(int value) {
         this.value = value;
      }
   }

   public Node toBST(int[] data) {
      return toBST(data, 0, data.length - 1);
   }

   private Node toBST(int[] data, int start, int end) {
      if (start == end)
         return new Node(data[start]);

      if (end - start == 1) {
         Node root = new Node(data[start]);
         root.right = new Node(data[end]);
         return root;
      }

      int mid = (start + end) / 2;
      Node root = new Node(data[mid]);
      root.left = toBST(data, start, mid - 1);
      root.right = toBST(data, mid + 1, end);

      return root;
   }

   public void inorder(Node root) {
      if (root == null)
         return;

      inorder(root.left);
      System.out.print(root.value + " ");
      inorder(root.right);
   }

   public static void main(String[] args) {
      int[] data = {1, 2, 3, 4, 5, 6, 7, 8};

      P0403 p0403 = new P0403();
      Node root = p0403.toBST(data);
      p0403.inorder(root);
      System.out.println();
   }
}
