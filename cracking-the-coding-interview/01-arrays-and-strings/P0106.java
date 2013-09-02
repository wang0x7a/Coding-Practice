/* 1.6 Given an image represented by an N*N matrix, where each pixel in the 
 * image is 4 bytes, write a method to rotate the image by 90 degrees.
 * Can you do this in place?
 * */

public class P0106 {
   /* 4 bytes = 32 bits = 1 int */
   /* Solution 1: touch each element once */
   public static int[][] rotate(int[][] matrix) {
      int n = matrix.length;
      for (int layer = 0; layer < n / 2; layer++, n--) {
         start = layer;
         end = n - start;
      }
   }
}
