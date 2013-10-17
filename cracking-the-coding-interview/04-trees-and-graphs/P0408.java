/* 4.8 You are given a binary tree in which each node contains a value.
 * Design an algorithm to print all paths which sum up to that value.
 * Note that it can be any path in the tree - it does not have to start
 * at the root.
 * */

import java.util.ArrayList;

public class P0408 {
   private class Node {
      int value;
      Node left, right;

      public Node(int value) {
         this.value = value;
      }
   }

   public void findPath(Node root, int sum) {
      ArrayList<Integer> buffer = new ArrayList<Integer> ();
      findPathHelper(root, sum, buffer, 0);
   }

   @SuppressWarnings("unchecked")
   private void findPathHelper(Node node, int sum, ArrayList<Integer> buffer, 
   int currentLevel) {
      if (node == null) return;

      // record the value of the current node
      buffer.add(node.value);

      int tmp = sum;
      // levels are counted from 0
      for (int i = currentLevel; i > -1; i--) {
         tmp -= buffer.get(i);

         if (tmp == 0) {
            System.out.print("#: ");
            printPath(buffer, i, currentLevel);
         }
      }

      // create a buffer for each subpath of the current node
      ArrayList<Integer> leftPath = (ArrayList<Integer>) buffer.clone();
      findPathHelper(node.left, sum, leftPath, currentLevel + 1);

      ArrayList<Integer> rightPath = (ArrayList<Integer>) buffer.clone();
      findPathHelper(node.right, sum, rightPath, currentLevel + 1);
   }

   private void printPath(ArrayList<Integer> buffer, int from, int to) {
      for (int i = from; i < to + 1; i++) {
         System.out.print(buffer.get(i) + " ");
      }

      System.out.println();
   }

   public Node buildTree() {
      Node root = new Node(2);
      root.left = new Node(-2);
      root.right = new Node(1);
      root.left.left = new Node(3);
      root.left.right = new Node(6);
      root.left.right.right = new Node(2);
      root.right.left = new Node(3);
      root.right.right = new Node(5);
      root.left.left.left = new Node(2);
      root.left.left.left.left = new Node(1);
      return root;
   }

   public static void main(String[] args) {
      P0408 p0408 = new P0408();

      Node root = p0408.buildTree();
      p0408.findPath(root, 6);
   }
}
