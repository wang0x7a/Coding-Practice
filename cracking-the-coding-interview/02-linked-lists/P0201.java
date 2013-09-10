/** 2.1 Write code to remove duplicates from an unsorted linked list.
 *  FOLLOW UP:
 *  How would you solve this problem if a temporary buffer is not allowed?
 * */
import java.util.Hashtable;
import java.util.NoSuchElementException;

public class P0201 {
   private class Node {
      int data;
      Node next;
   }

   public static Node removeDupsWithBuffer(Node head) {
      if (head == null) {
         throw new NoSuchElementException("Empty list");
      }

      Hashtable<Integer, Boolean> table = new Hashtable<Integer, Boolean>();
      Node current = head.next;
      Node previous = head.next;
      while (current != null) {
         /* if the element exists, skip the current node */
         if (table.containsKey(current.data)) previous.next = current.next;
         else {
            table.put(current.data, true);
            previous = current;
            //current = current.next;
         }
         /* Lessons: We should always modify the pointer to the current node,
          * otherwise, we would fall into a infinite loop.
          * */
         current = current.next;
      }
      return head;
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
      int[] data = {1, 2, 3, 4, 5, 5, 6, 7, 9, 9, 10, 11 ,12};
      P0201 p0201 = new P0201(); 
      Node list = p0201.initialize(data);

      System.out.println(p0201.toString(removeDupsWithBuffer(list)));
   }
}
