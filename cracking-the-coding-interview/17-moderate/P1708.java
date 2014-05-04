/* P1708
 *
 * You are given an array of integers (both positive and negative). Find the
 * contiguous sequence with the largest sum. Return the sum.
 *
 * Example:
 * Input : 2, -8, 3, -2, 4, 10
 * Output: 3, -2, 4
 */

public class P1708 {
    public static void maxSubseq(int[] seq) {
        int max = 0;
        int end = 0;
        int start = 0, startTmp = 0;

        int len = seq.length;
        int tmp = 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            tmp += seq[i];

            if (tmp > max) {
                max = tmp;
                end = i;
                start = startTmp;
            }

            if (tmp < 0) {
                tmp = 0;
                startTmp = i + 1;
            }
        }

        print(seq, start, end);

        System.out.println(max);
    }

    private static void print(int[] seq, int start, int end) {
        for (int i = start; i <= end; i++)
            System.out.print(seq[i] + " ");

        System.out.println();
    }

    public static void main(String args[]) {
        int[] seq = {2, -8, 3, -2, 4, -10};

        maxSubseq(seq);
    }
}
