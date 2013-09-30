/* 4.4 Given a binary search tree, design an algorithm which creates a linked
 * list of all the nodes at each depth (i.e., if you have a tree with depth D,
 * you will have D linked lists).
 * */

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class P0404 {
   private class Node {
      int value;
      Node left, right;

      public Node(int value) {
         this.value = value;
      }
   }

   public void levelOrder(Node root, int level, int count, 
      List<Node> list) {

      if (root == null) return;

      if (count == level) {
         list.add(root);
         return;
      }

      if (count < level) {
         levelOrder(root.left, level, count + 1, list);
         levelOrder(root.right, level, count + 1, list);
      }
   }

   public Node buildTree() {
      Node root = new Node(1);
      root.left = new Node(2);
      root.right = new Node(3);
      root.left.left = new Node(4);
      root.left.right = new Node(5);
      root.right.left = new Node(6);
      root.left.left.left = new Node(7);
      //root.left.left.left.left = new Node(8);
      return root;
   }

   public static void main(String[] args) {
      P0404 p0404 = new P0404();
      Node root = p0404.buildTree();

      for (int i = 1; i <= 4; i++) {
         List<Node> list = new LinkedList<Node>();
         p0404.levelOrder(root, i, 1, list);
         Iterator<Node> iter = list.iterator();
         while (iter.hasNext()) {
            System.out.print(iter.next().value + " ");
         }
         System.out.println();
      }
   }
}
