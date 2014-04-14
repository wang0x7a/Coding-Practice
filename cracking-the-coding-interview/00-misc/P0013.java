/* P0013
 *
 * Merge Sorted Array
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 *
 * Notes:
 * 1) Allow to use an auxilary array to store the restul? (O(M+N) space)
 * 2) What if the space complexity is O(1) (without additional storage)?
 */

public class P0013 {
    public static void merge(int[] a, int m, int[] b, int n) {
        // a.length = m + n
        // b.length = n
        int len = a.length;

        int i = m - 1, j = n - 1;
        for (int k = len - 1; k >= 0; k--) {
            if (i > 0 && j < 0)
                a[k] = a[i--];
            else if (i < 0 && j > 0)
                a[k] = b[j--];
            else {
                if (a[i] > b[j]) {
                    a[k] = a[i--];
                }
                else {
                    a[k] = b[j--];
                }
            }
        }
    }

    public static void print(int[] a, int len) {
        for (int i = 0; i < len; i++)
            System.out.print(a[i] + " ");

        System.out.println();
    }

    public static void main(String args[]) {
        int m = 5, n = 5;
    }
}
