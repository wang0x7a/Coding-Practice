/* 3.4 In the classic problem of the Tower of Hanoi, you have 3 rodss and 
 * N disks of different sizes which can slide onto any tower. The puzzle 
 * starts with disks sorted in ascending order of size from top to 
 * bottom (e.g., each disk sits on top of an even larger one). You have the
 * following constraints:
 * (A) Only one disk can be moved at a time.
 * (B) A disk is slid off the top of one rods onto the next rods.
 * (C) A disk an only be placed on top of a larger disk.
 * Write a program to move the disks from the first rods to the last using 
 * stacks.
 * */
import java.util.Stack;
import java.util.Iterator;
import java.util.ArrayList;

public class P0304 {
   public class HanoiTower {
      private ArrayList<Stack<Integer>> rods;
      private int height;

      public HanoiTower() {
         this(3);
      }

      public HanoiTower(int height) {
         this.height = height;
         rods = new ArrayList<Stack<Integer>>();
         for (int i = 0; i < 3; i++) {
            rods.add(new Stack<Integer>());
         }
         // initialize the first tower
         for (int i = height; i > 0; i--) {
            rods.get(0).push(i);
         }
      }

      // iterative approach
      public void move() {
         int move = 0;
         int posOfSmallest = 0;
         while (rods.get(1).size() != height && rods.get(2).size() != height) {
            move++;
            System.out.println("Move #" + move);
            // in an odd move, move the smallest element
            if (move % 2 == 1) {
               int currentPos = posOfSmallest;
               posOfSmallest = (posOfSmallest + 1) % 3;
               int tmp = rods.get(currentPos).pop();
               rods.get(posOfSmallest).push(tmp);
               printTower();
            }
            // in an even move, move the second smallest element
            else {
               int index = getSecond(posOfSmallest);
               //System.out.println(index);
               if (rods.get(index).peek() % 2 == 1) {
                  moveSecond(index, 1);
                  printTower();
               }
               else {
                  moveSecond(index, 2);
                  printTower();
               }
            }
         }
      }

      public void printTower() {
         for (int i = 0; i < 3; i++) {
            System.out.println("Tower #" + i + ": " + toString(i));
         }
         
      }

      private String toString(int stackNum) {
         StringBuilder s = new StringBuilder();
         Iterator<Integer> iter = rods.get(stackNum).iterator();
         while (iter.hasNext()) {
            s.append(iter.next() + " ");
         }
         return s.toString();
      }

      private void moveSecond(int start, int step) {
         int end = (start + step) % 3;
         rods.get(end).push(rods.get(start).pop());
      }

      private int getSecond(int posOfSmallest) {
         int index = 0;
         int tmp = Integer.MAX_VALUE;
         for (int i = 0; i < 3; i++) {
            if (i == posOfSmallest) continue;
            if (rods.get(i).size() == 0) continue;

            if (rods.get(i).peek() < tmp) {
               tmp = rods.get(i).peek();
               index = i;
            }

         }
         return index;
      }
   }

   public static void main(String[] args) {
      P0304 p0304 = new P0304();
      HanoiTower tower = p0304.new HanoiTower(4);
      System.out.println("===Original Tower===");
      tower.printTower();
      System.out.println("====Begin moving====");
      tower.move();
      System.out.println("=======Output=======");
      tower.printTower();
   }
}
