/* P1704
 *
 * Write a method which finds the maximum of two numbers. You should not use
 * if-else or any other comparison operators.
 */

public class P1704 {
    public static int max(int a, int b) {
        int c = a - b;

        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);

        int m = sa ^ sb;

        int p = (m & sa) | ((1 - m) & sc);
        int q = (m & sb) | ((1 - m) & flip(sc));

        return a * p + b * q;
    }

    private static int flip(int bit) {
        return 1 ^ bit;
    }

    private static int sign(int num) {
        return flip((num >> 31) & 0x1);
    }

    public static void main(String args[]) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        int c = max(a, b);
        System.out.println(c);
    }
}
