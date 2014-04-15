/* P0016
 *
 * Set Matrix Zeros
 * Given a m * n matrix, if an element is 0, set its entire row and column
 * to 0. Do it in place.
 */

public class P0016 {
    public static void setZeros(int[][] mat) {
        int m = mat.length;
        
        if (m <= 0)
            return;

        int n = mat[0].length;

        boolean isFirstRowZero = false;
        boolean isFirstColZero = false;

        for (int i = 0; i < m; i++) {
            if (mat[i][0] == 0) {
                isFirstRowZero = true;
                break;
            }
        }

        for (int j = 0; j < n; j++) {
            if (mat[0][j] == 0) {
                isFirstColZero = true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (mat[i][j] == 0) {
                    mat[i][0] = 0;
                    mat[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (mat[i][0] == 0 || mat[0][j] == 0)
                    mat[i][j] = 0;
            }
        }

        if (isFirstRowZero) {
            for (int i = 0; i < m; i++)
                mat[i][0] = 0;
        }

        if (isFirstColZero) {
            for (int j = 0; j < n; j++)
                mat[0][j] = 0;
        }
    }

    public static void print(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(mat[i][j] + " ");

            System.out.println();
        }
    }

    public static void main(String args[]) {
        int[][] mat = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};

        P0016.print(mat);

        P0016.setZeros(mat);
        P0016.print(mat);
    }
}
