/* 3.6 Write a program to sort a stack in ascending order. YOu should not make
 * any assumptions about how the stack is implemented. The following are the 
 * only functions that should be used to write this program:
 * push | pop | peek | isEmpty
 * */

import java.util.Stack;

public class P0306 {
   public static Stack<Integer> sort(Stack<Integer> stack) {
      Stack<Integer> buffer = new Stack<Integer>();
      int tmp;

      while (!stack.isEmpty()) {
         tmp = stack.pop();
         while (!buffer.isEmpty() && tmp >= buffer.peek()) {
            stack.push(buffer.pop());
         }
         buffer.push(tmp);
      }

      return buffer;
   }

   public static void main(String[] args) {
      Stack<Integer> stack = new Stack<Integer>();
      int[] data = {5, 1, 4, 3, 6};
      for (int i = 0; i < data.length; i++) {
         stack.push(data[i]);
      }
      stack = sort(stack);

      while (!stack.isEmpty()) {
         System.out.print(stack.pop() + " ");
      }
   }
}
