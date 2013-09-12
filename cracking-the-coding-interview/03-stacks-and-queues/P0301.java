/* 3.1 Describe how you could use a single array to implement three stacks
 * */

/* Pre-considerations:
 * 1. What is the type of the values in the stack.
 * 2. Can we wrap the value with an object?
 * 3. Is it a array stack or list stack?
 */

/* Post-considerations:
 * 1. There is a problem when setting the entries in stackPointer as
 *    the original indices in the buffer, i.e., it will be difficult to check 
 *    if the stack is empty, since the pointer initially points to the first
 *    element of the stack, which is not initiated whenc constructing the stack.
 *    One possible solution is to set the first element as a sentinel.
 * 2. Another approach to address #1, is to set the entries in stackPointes as
 *    the relative indices in each stack, starting from -1 when initializing,
 *    instead of the absolute indices in the buffer.
 * 3. When implementing sequential data structures with arrays, space
 *    deficiency is always a problem, and we could address this issue by 
 *    switching to linked lists.
 * */

public class P0301 {
   // Implementation with array stack, assuming the value type is int.
   public class StacksInArray {
      private int stackSize;
      private int numOfStacks;
      private int[] buffer;
      private int[] stackPointer;

      // Defult constructer, stackSize = 300, numofStacks = 3
      public StacksInArray() {
         this(300, 3);
      }

      public StacksInArray(int stackSize, int numOfStacks) {
         this.stackSize = stackSize;
         this.numOfStacks = numOfStacks;

         buffer = new int[stackSize * numOfStacks];
         //initPointers();
         initPointers2();
      }

      private void initPointers() {
         stackPointer = new int[numOfStacks];
         for (int i = 0; i < numOfStacks; i++) {
            stackPointer[i] = i * stackSize;
            // fix for post-consideration#1
            buffer[stackPointer[i]] = Integer.MIN_VALUE;
         }
      }

      // appendency for post-consideration#2
      private void initPointers2() {
         stackPointer = new int[numOfStacks];
         for (int i = 0; i < numOfStacks; i++)
            stackPointer[i] = -1;
      }

      public int getNumOfStacks() {
         return numOfStacks;
      }

      public void push(int stackNum, int value) {
         if (stackNum < 0 || stackNum >= numOfStacks) {
            throw new IndexOutOfBoundsException("The stack number should" +
               " between " + 0 + " and " + (numOfStacks - 1));
         }

         if (isFull(stackNum))
            throw new IndexOutOfBoundsException("Stack #" + stackNum + 
               " is full.");

         buffer[++stackPointer[stackNum]] = value;
      }

      public int pop(int stackNum) {
         if (stackNum < 0 || stackNum >= numOfStacks) {
            throw new IndexOutOfBoundsException("The stack number should" +
               " between " + 0 + " and " + (numOfStacks - 1));
         }

         // fix for post-consideration#1
         /*
         if (isEmpty(stackNum)) {
            throw new IndexOutOfBoundsException("Stack #" + stackNum +
               " is empty.");
         }
         */
         // appendency for post-consideration#2
         if (isEmpty2(stackNum)) {
            throw new IndexOutOfBoundsException("Stack #" + stackNum +
               " is empty.");
         }

         int index = stackPointer[stackNum]--;
         int value = buffer[index];
         buffer[index] = 0;
         return value;
      }

      public boolean isEmpty(int stackNum) {
         return stackPointer[stackNum] == stackNum * stackSize;
      }

      // appendency for post-consideration#2
      public boolean isEmpty2(int stackNum) {
         return stackPointer[stackNum] == -1;
      }

      public boolean isFull(int stackNum) {
         return stackPointer[stackNum] == (stackNum + 1) * stackSize - 1;
      }
   }

   /* 2nd solution to post-consideration#3
    * One problem would be that, we need to use 
    */
   public class StacksInList {
      private class Node {
         private int data;
         private Node previous;

         public Node(int data, Node previous) {
            this.data = data;
            this.previous = previous;
         }
      }

      private final int numOfStacks;
      private final int capacity;
      private Node[] top;
      private Node[] buffer;
      private int size;

      public StacksInList() {
         this(3, 300);
      }

      public StacksInList(int numOfStacks, int capacity) {
         this.numOfStacks = numOfStacks;
         // the following line is a bad implementation, since sizeOfStacks
         // can be modified outside of the object
         // this.sizeOfStacks = sizeOfStacks;
         /*
         this.sizeOfStacks = new int[numOfStacks];
         int total = 0;
         for (int i = 0; i < sizeOfStacks.length; i++) {
            this.sizeOfStacks[i] = sizeOfStacks[i];
            total += sizeOfStacks[i];
         }
         */
         this.capacity = capacity;
         top = new Node[numOfStacks];
         buffer = new Node[capacity];
         size = 0;
      }

      public void push(int stackNum, int value) {
         if (!isValidStack(stackNum))
            throw new IndexOutOfBoundsException("Stack number should be " +
               "between " + 0 + " and " + (numOfStacks - 1));

         if (isFull())
            throw new IndexOutOfBoundsException("Out of space!");

         Node node = new Node(value, top[stackNum]);
         buffer[++size] = node;
         top[stackNum] = node;
      }

      public int pop(int stackNum) {
         if (!isValidStack(stackNum))
            throw new IndexOutOfBoundsException("Stack number should be " +
               "between " + 0 + " and " + (numOfStacks - 1));

         if (isEmpty(stackNum))
            throw new IndexOutOfBoundsException("Stack #" + stackNum
               " is empty.");

         Node node = top[stackNum];
         top[stackNum] = node.previous;
         
      }

      // Since all the stacks are sharing the space of a given array,
      // we can only check the size of the array.
      public boolean isFull() {
         return size == capacity;
      }

      // No need to check if the whole buffer is empty.
      public boolean isEmpty(int stackNum) {
         return top[stackNum] != null;
      }

      private boolean isValidStack(int stackNum) {
         return stackNum >= 0 && stackNum < numOfStacks;
      }

      
   }

   public static void main(String[] args) {
      P0301 p0301 = new P0301();
      StacksInArray stacks = p0301.new StacksInArray();
      System.out.println(stacks.getNumOfStacks());
      System.out.println(stacks.isEmpty(1));

      stacks.push(1, 5);
      stacks.push(0, 4);
      System.out.println(stacks.isEmpty2(0));
      System.out.println(stacks.pop(0));
      System.out.println(stacks.isEmpty2(0));

      StacksInList stacks2 = p0301.new StacksInList();
   }
}
