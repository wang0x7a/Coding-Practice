/* P1703
 *
 * Write an algorithm which computes the number of trailing zeros in
 * n factorial.
 */

public class P1703 {
    public static int trailingZeros(int n) {
        int num = calcFac(n);

        return countZeros(num);
    }

    private static int calcFac(int n) {
        int res = 1;

        for (int i = 1; i <= n; i++)
            res *= i;

        return res;
    }

    private static int countZeros(int n) {
        int count = 0;

        while (n % 10 == 0) {
            count++;
            n /= 10;
        }

        return count;
    }

    public static int trailingZerosX(int n) {
        int count = 0;

        for (int i = 1; i <= n; i++)
            count += factorsOf5(i);

        return count;
    }

    private static int factorsOf5(int n) {
        int count = 0;

        while (n % 5 == 0) {
            count++;
            n /= 5;
        }

        return count;
    }

    public static void main(String args[]) {
        int n = Integer.parseInt(args[0]);

        int res = trailingZerosX(n);

        System.out.println(res);
    }
}
