/* 2.2 Implement an algoritym to find the nth to last element of a singly
 * linked list.
 */
import java.util.NoSuchElementException;

public class P0202 {
   private class Node {
      int data;
      Node next;
   }

   public static Node nth2Last(Node head, int n) {
      Node slow = head.next;
      Node fast = head.next;

      for (int i = n; i > 0; i--, fast = fast.next) {
         if (fast == null) {
            throw new NoSuchElementException("n exceeds the length of the list");
         }
      }

     while (fast != null) {
        slow = slow.next;
        fast = fast.next;
     }

     return slow;
   }

   public static int nth2LastRec(Node head, int n) {
      if (head == null) {
         return 0;
      }

      int i = nth2LastRec(head.next, n) + 1;
      if (i == n) {
         System.out.println(head.data);
      }

      return i;
   }

   private Node initialize(int[] data) {
      Node head = new Node();
      head.data = Integer.MIN_VALUE;
      head.next = null;

      if (data == null) {
         throw new NoSuchElementException("Empty inputs");
      }

      Node current = head;
      int len = data.length;
      for (int i = 0; i < len; i++) {
         Node tmp = new Node();
         tmp.data = data[i];
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
         s.append(current.data + " ");
         current = current.next;
      }

      return s.toString();
   }

   public static void main(String[] args) {
      int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12};
      P0202 p0202 = new P0202();
      Node list = p0202.initialize(data);

      //Node node = nth2Last(list, 13);
      Node node = nth2Last(list, 3);
      System.out.println(node.data);

      nth2LastRec(list, 3);
   }
}
