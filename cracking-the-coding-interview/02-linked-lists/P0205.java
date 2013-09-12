/* 2.5 Given a circular linked list, implement an algorithm which returns
 * node at the beginning of the loop.
 * */

import java.util.NoSuchElementException;

public class P0205 {
   private class Node {
      private int data;
      private Node next;
   }

   public Node findBeginning(Node head) {
      Node node = isIntersected(head);

      if (node == null) return null;

      while (head != node) {
         head = head.next;
         node = node.next;
      }

      return node;
   }

   public Node isIntersected(Node head) {
      if (head == null || head.next == null) return null;

      Node slow = head.next;
      Node fast = head.next.next;

      while (fast.next != null) {
         if (slow == fast) return fast;

         slow = slow.next;
         fast = fast.next.next;
      }

      return null;
   }

   public Node makeCircularList(int[] data, int jointPnt) {
      Node head = new Node();
      head.data = Integer.MIN_VALUE;
      head.next = null;

      Node runner = head;
      int len = data.length;
      Node meet = null;
      for (int i = 0; i < len; i++) {
         Node node = new Node();
         node.data = data[i];
         node.next = null;

         runner.next = node;
         runner = node;

         if (++i == jointPnt) meet = node;
      }

      if (jointPnt != 0)
         runner.next = meet;

      return head;
   }

   public static void main(String[] args) {
      int[] data = {1, 2, 3, 4, 5, 6, 7, 9, 10};
      P0205 p0205 = new P0205();
      Node list = p0205.makeCircularList(data, 5);

      Node joint = p0205.findBeginning(list);
      System.out.println(joint.data);       
   }
}
