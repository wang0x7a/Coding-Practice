/* 4.5 Write an algorithm to find the "next" node (i.e., in-order successor)
 * of a given node in a binary search tree where each node has a link to its
 * parent.
 * */

public class P0405 {
   private class Node {
      private int value;
      private Node left, right;
      private Node parent;

      public Node(int value) {
         this.value = value;
      }
   }

   public int getSuccessor(Node x, int value) {
      if (x == null) {
         System.out.println("Fail to find the element");
         return Integer.MIN_VALUE;
      } 

      if (value < x.value)
         return getSuccessor(x.left, value);
      else if (value > x.value)
         return getSuccessor(x.right, value);
      else {
         if (x.right != null)
            return x.right.value;
         else {
            if (x.parent.left == x)
               return x.parent.value;
            else
               return x.parent.parent.value;
         } 
      }
   }

   public Node buildBST(Node root, int value) {
      if (root == null) return new Node(value);

      if (value < root.value) {
         root.left = buildBST(root.left, value);
         root.left.parent = root;
      }
      else if (value > root.value) {
         root.right = buildBST(root.right, value);
         root.right.parent = root;
      }
      else {
         System.out.println("The element exists!");
      }

      return root;
   }

   public static void main(String[] args) {
      P0405 p0405 = new P0405();
      int[] data = {8, 5, 15, 3, 6, 10, 19, 1, 4, 22, 21};
      Node root = p0405.new Node(8);
      for (int i = 1; i < data.length; i++) {
         root = p0405.buildBST(root, data[i]);
      }

      System.out.println(p0405.getSuccessor(root, 4));
      System.out.println(p0405.getSuccessor(root, 8));
      System.out.println(p0405.getSuccessor(root, 15));
   }
}
