/* P1706
 *
 * Given an array of intergers, write a method to find indices m and n such
 * that if you sorted elements m through n, the entire array would be sorted.
 * Minimize n - m (that is, find the smallest such sequence)
 */

import java.util.Arrays;

public class P1706 {
    public static void subseq(int[] seq) {
        int[] copy = seq.clone();

        Arrys.sort(copy);

        int len = seq.length;

        int m = 0, n = len - 1;

        for (int i = 0; i < len; i++) {
            if (copy[i] != seq[i]) {
                m = i;
                break;
            }
        }

        for (int i = len - 1; i > -1; i--) {
            if (copy[i] != seq[i]) {
                n = i;
                break;
            }
        }

        System.out.println("m: " + m + "; " + "n: " + n);
    }

    public static void main(String args[]) {
        int[] seq = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};

        subseq(seq);
    }

}
