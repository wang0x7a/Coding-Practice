/* 1.7 Write an algorithm such that if an element in an M*N matrix is 0,
 * its entire row and column is set to 0.
 * */

public class P0107 {
   public static int[][] setZeros(int[][] matrix) {
      if (matrix == null) return null;
      int m = matrix.length;
      if (matrix[0] == null) matrix = new int[m][1];
      int n = matrix[0].length;

      int[] rows = new int[m];
      int[] cols = new int[n];

      for (int i = 0; i < m; i++) {
         for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 0) {
               rows[i] = 1;
               cols[j] = 1;
            }
         }
      }

      for (int i = 0; i < m; i++) {
         for (int j = 0; j < m; j++) {
            if (rows[i] == 1 || cols[j] == 1)
               matrix[i][j] = 0;
         }
      }

      return matrix;
   }

   public static void main(String[] args) {
      int[][] matrix = {{1, 0, 3}, {3, 4, 5}, {1, 0, 7}};
      matrix = P0107.setZeros(matrix);
      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j < matrix[0].length; j++)
            System.out.print(matrix[i][j] + " ");
         System.out.println();
      }
   }
}
