/* 3.5 Implement a MyQueue class which implements a queue using two stacks.
 * */

import java.util.Stack;

public class P0305 {
   public class Queue<T> {
      private Stack<T> in, out;
      private int capacity;
      private int size;

      public Queue() {
         this(4);
      }

      public Queue(int capacity) {
         this.capacity = capacity;
         // we could randomly allocate the capacity to the two stacks,
         // for convenience, we constrict the capacity of each stack as
         // half of the queue.
         in = new Stack<T>();
         out = new Stack<T>();
         size = 0;
      }

      public void enqueue(T value) {
         if (isStackFull(in)) {
            if (isStackEmpty(out))
               transport();
            else
               throw new IllegalStateException("The queue is full!");
         }

         in.push(value);
         size++;
      }

      public T dequeue() {
         if (isStackEmpty(out)) {
            if (!isStackEmpty(in))
               transport();
            else throw new IllegalStateException("The queue is empty");
         }

         size--;
         return out.pop();
      }

      public int getSize() {
         return size;
      }

      public int getCapacity() {
         return capacity;
      }

      public boolean isFull() {
         return size == capacity;
      }

      public boolean isEmpty() {
         return size == 0;
      }

      // transport elements from the in stack to the out stack
      private void transport() {
         if (!isStackEmpty(out))
            throw new IllegalStateException("The out stack is not empty, " +
               "CANNOT tranport!");

         while (!isStackFull(out)) {
            out.push(in.pop());
         }
      }

      private boolean isStackFull(Stack<T> stack) {
         return stack.size() == capacity / 2;
      }

      private boolean isStackEmpty(Stack<T> stack) {
         return stack.size() == 0;
      }
   }

   public static void main(String[] args) {
      P0305 p0305 = new P0305();
      Queue<Integer> queue = p0305.new Queue<Integer>();
      //queue.dequeue();
      for (int i = 0; i < 4; i++)
         queue.enqueue(i);
      for (int i = 0; i < 4; i++)
         System.out.println(queue.dequeue());
   }
}
