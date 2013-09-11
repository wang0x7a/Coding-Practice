/* 2.4 You have two numbers represented by a linked list, where each node
 * contains a single digit. The digits are stored in reserve order, such that
 * the 1's digit is at the head of the list. Write a function that adds the two
 * numbers and returns the sum as a linked list.
 *
 * EXAMPLE:
 * Input: (3->1->5) + (5->9->2)
 * Output: 8->0->8
 * */

import java.util.NoSuchElementException;

public class P0204 {
  private class Node {
    private int digit;
    private Node next;
  }

  public Node add(Node a, Node b) {
    if (a.next == null && b.next != null) return b;
    if (a.next != null && b.next == null) return a;
    if (a.next == null && b.next == null) return null;

    Node result = new Node();
    result.digit = Integer.MIN_VALUE;
    result.next = null;
    // runner for a
    Node ra = a.next;
    // runner for b
    Node rb = b.next;
    // runner for result
    Node rr = result;
 
    int carrier = 0;
    int tmp = 0;
    while (ra != null && rb != null) {
      Node node = new Node();
      tmp = ra.digit + rb.digit + carrier;
      node.digit = tmp % 10;
      carrier = tmp / 10;

      rr.next = node;
      ra = ra.next;
      rb = rb.next;
      rr = rr.next;
    }

    if (ra == null && rb != null) {
      System.out.println("ra == null && rb != null");
      Node node = new Node();
      node.digit = rb.digit + carrier;
      rr.next = node;
      node.next = rb.next;
    }
    else if (ra != null && rb == null) {
      Node node = new Node();
      node.digit = ra.digit + carrier;
      rr.next = node;
      node.next = ra.next;
    }

    return result;
  }

   private Node initialize(int[] digit) {
      Node head = new Node();
      head.digit = Integer.MIN_VALUE;
      head.next = null;

      if (digit == null) {
         throw new NoSuchElementException("Empty inputs");
      }

      Node current = head;
      int len = digit.length;
      for (int i = 0; i < len; i++) {
         Node tmp = new Node();
         tmp.digit = digit[i];
         tmp.next = null;
         current.next = tmp;
         current = tmp;
      }
      return head;
   }

   private String toString(Node list) {
      StringBuilder s = new StringBuilder();
      Node current = list;
      while (current != null) {
         s.append(current.digit + " ");
         current = current.next;
      }

      return s.toString();
   }

   public static void main(String[] args) {
      //int[] n1 = {3, 1, 5};
      int[] n1 = {3};
      int[] n2 = {5, 5, 2};
      P0204 p0204 = new P0204();
      Node a = p0204.initialize(n1);
      Node b = p0204.initialize(n2);
      Node result = p0204.add(a, b);
      System.out.println(p0204.toString(result));
   }

}
