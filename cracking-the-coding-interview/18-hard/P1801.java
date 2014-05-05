/* P1801
 *
 * Write a functin that adds two numbers. You should not use + or any
 * arithmetic operators.
 */

public class P1801 {
    public static int add(int a, int b) {
        int c;
        while (b != 0) {
            c = a ^ b;
            b = (a & b) << 1;
            a = c;
        }

        return a;
    }

    public static void main(String args[]) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        int r = add(a, b);
        System.out.println(r);
    }
}
