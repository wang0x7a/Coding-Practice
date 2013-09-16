/* 3.2 How would you design a stack which, in addition to push and pop, also
 * has a function min which returns the minimum elements? Push, pop and min
 * should all operate in O(1) time.
 * */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class P0302 {

/* For the following solution, we need to allocate one extra byte for each node
 * to store the min value. This would be a problem when the number of nodes 
 * increases. An extreme case would be that, the min value of a 1M-node stack 
 * is always the first one.
 *
 * If we assume there are NO DUPs in a given stack, we could use an extra smaller
 * stack to track the min values.
 * */

   public class StackWithMin {
      private class Node {
         private int data;
         private Node previous;
         // each node in the stack will record the min valude upon
         // pushing it in.
         private int min;

         public Node() {
            this.min = 0;
            this.data = 0;
            this.previous = null;
         }

         public Node(int data, int min) {
            this.data = data;
            this.min = min;
            previous = null;
         }
      }

      private int size;
      private Node top;

      public int getSize() {
         return size;
      }

      public void push(int value) {
         Node node = new Node();
         node.data = value;

         if (top == null) {
            top = node;
            top.min = value;
            return;
         }

         if (value < top.min) node.min = value;
         else node.min = top.min;

         node.previous = top;
         top = node;
         size++;
      }

      public int pop() {
         int result = top.data;
         top = top.previous;
         size--;
         return result;
      }

      public int min() {
         return top.min;
      }

      public int peek() {
         return top.data;
      }

   }

   public static void main(String[] args) {
      P0302 p0302 = new P0302();
      StackWithMin stack = p0302.new StackWithMin();
      stack.push(2);
      System.out.println(stack.min());
      stack.push(1);
      System.out.println(stack.min());
      stack.pop();
      System.out.println(stack.min());
   }
}
