/* P1711
 *
 * Implement a method rand7() given rand5(). That is, given a method that
 * generates a random number between 0 and 4 (inclusive), write a method
 * that generates a random number between 0 and 6 (inclusive).
 */

import java.util.Random;

public class P1711 {
    public static int rand7() {
        while (true) {
            int a = rand5();
            int b = rand5();

            int c = 5 * a + b;

            /*
            int c = 5 * rand5() + rand5();
            */

            if (c < 21) {
                return c % 7;
            }
        }
    }

    private static int rand5() {
        Random random = new Random();
        
        return random.nextInt(5);
    }

    public static void main(String args[]) {
        System.out.println(rand7());
    }
}
