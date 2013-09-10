import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
   private class Node {
      private Item item;
      private Node next;
   }

   private int N;
   private Node first;

   public Stack() {
      first = null;
      N = 0;
      assert check();
   }

   public boolean isEmpty() {
      return first == null;
   }

   public int size() {
      return N;
   }

   public void push(Item item) {
      Node oldFirst = first;
      first = new Node();
      first.item = item;
      first.next = oldFirst;
      N++;
      assert check();
   }

   public Item pop() {
      if (isEmpty()) throw new NoSuchElementException("Stack underflow");
      Item item = first.item;
      first = first.next;
      N--;
      assert check();
      return item;
   }

   public Item peek() {
      if (isEmpty()) throw new NoSuchElementException("Stack underflow");
      return first.item;
   }

   public String toString() {
      StringBuilder s = new StringBuilder();
      for (Item item : this)
         s.append(item + " ");

      return s.toString();
   }

   private boolean check() {
      if (N == 0) {
         if (first != null) return false;
      }
      else if (N == 1) {
         if (first == null) return false;
         if (first.next != null) return false;
      }
      else {
         if (first.next == null) return false;
      }

      int numOfNodes = 0;
      for (Node x = first; x != null; x = x.next) {
         numOfNodes++;
      }
      if (numOfNodes != N) return false;

      return true;
   }
   
   public Iterator<Item> iterator() {
      return new ListIterator();
   }

   private class ListIterator implements Iterator<Item> {
      private Node current = first;
      public boolean hasNext() {
         return current != null;
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }

      public Item next() {
         if (!hasNext()) throw new NoSuchElementException();
         Item item = current.item;
         current = current.next;
         return item;
      }
   }

   public static void main(String[] args) {
      Stack<String> s = new Stack<String>();
      s.push("a");
      System.out.println(s.toString());
   }
}
