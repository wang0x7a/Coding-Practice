/* P1701
 *
 * Write a functon to swap a number in place (that is, without temperary
 * varaibles)
 */

public class P1701 {
    public static void swap(int a, int b) {
        System.out.println("a: " + a + "; b: " + b);

        a = b - a;
        b = b - a;
        a = b + a;

        System.out.println("a: " + a + "; b: " + b);
    }

    public static void swap2(int a, int b) {
        System.out.println("a: " + a + "; b: " + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a: " + a + "; b: " + b);
    }

    public static void main(String args[]) {
        swap(1, 2);
        swap2(1, 2);
    }
}
