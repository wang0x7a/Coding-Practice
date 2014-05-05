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
        int j;
        for (int i = numCards; i > 0; i--) {
            j = random.nextInt(i);

            System.out.print(cards[j] + " ");

            if (j != last)
                cards[j] = cards[last];

            last--;
        }

        System.out.println();
    }

    // shuffle in place
    public static void shuffleX(int[] cards) {
        Random random = new Random();

        int tmp;
        int index;
        int last = numCards - 1;
        for (int i = numCards; i > 0; i--) {
            index = random.nextInt(i);

            tmp = cards[last];
            cards[last] = cards[index];
            cards[index] = tmp;

            last--;
        }
    }

    public static void main(String args[]) {
        int[] cards = new int[numCards];

        for (int i = 1; i <= numCards; i++) {
            cards[i - 1] = i;
            System.out.print(cards[i - 1] + " ");
        }

        int[] copy = cards.clone();

        System.out.println();

        shuffle(cards);
        shuffleX(copy);

        for (int i = 0; i < numCards; i++)
            System.out.print(copy[i] + " ");

        System.out.println();
    }
}
