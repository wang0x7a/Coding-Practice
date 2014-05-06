/* P1804
 *
 * Write a method to count the number of 2s that appear in all the numbers
 * between 0 and n (inclusive).
 *
 * Example:
 * Input : 25
 * Output: 9
 */

public class P1804 {
    public static int numOf2(int n) {
        int count = 0;

        int i;
        for (i = 10; n / i > 0; i *= 10) {
            if (n % i >= 2)
                count = count + (n / i + 1) * (i / 10);
            else
                count = count + (n / i) * (i / 10);
        }

        i /= 10;

        int m = n / i;
        if (m < 2)
            count = count;
        else if (m > 2)
            count = count + i;
        else
            count = count + (n % i) + 1;

        return count;
    }

    public static void main(String args[]) {
        int n = Integer.parseInt(args[0]);

        int res = numOf2(n);

        System.out.println(res);
    }
}
