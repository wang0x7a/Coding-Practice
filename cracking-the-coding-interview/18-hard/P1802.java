/* P1802
 *
 * Write a method to shuffle a deck of cards. It must be a perfect shuffle,
 * in other words, each of the 52! permutations of the deck has to be equally
 * likely. Assume that you are given a random number generator which is
 * peferct.
 */

import java.util.Random;

public class P1802 {
    private static int numCards = 52;

    public static void shuffle(int[] cards) {
        Random random = new Random();

        int last = numCards - 1;
        for (int i = numCards, j; i > 0; i--) {
            j = random.nextInt(i);

            System.out.print(cards[j] + " ");

            if (j != last)
                cards[j] = cards[last];

            last--;
        }

        System.out.println();
    }

    public static void main(String args[]) {
        int[] cards = new int[numCards];

        for (int i = 1; i <= numCards; i++) {
            cards[i - 1] = i;
            System.out.print(cards[i - 1] + " ");
        }

        System.out.println();

        shuffle(cards);
    }
}
