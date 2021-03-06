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

    public static int trailingZerosXX(int n) {
        if (n < 5)
            return 0;

        int count = 0;

        for (int i = 5; i <= n; i += 5)
            count += factorsOf5(i);

        return count;
    }

    public static int trailingZerosXXX(int n) {
        if (n < 5)
            return 0;

        int count = 0;
        for (int i = 5; n / i > 0; i *= 5)
            count += n / i;

        return count;

    }

    public static void main(String args[]) {
        int n = Integer.parseInt(args[0]);

        int res;
        res = trailingZerosXXX(n);
        System.out.println(res);
        res = trailingZerosXX(n);
        System.out.println(res);
        res = trailingZerosX(n);
        System.out.println(res);
    }
}
