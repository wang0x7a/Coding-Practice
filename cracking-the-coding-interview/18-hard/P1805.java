/* P1805
 *
 * You have a large text file containing words. Given any two words, find the
 * shortest distance (in termsof number of words) between them in file. Can
 * you make the searching operation in O(1) time? What about the space complexity
 * for your solution?
 */

public class P1805 {
    public static int calcDist(String[] file, String word1, String word2) {
        if (file == null)
            return -1;

        int len = file.length;
        int min = len;

        int lastWord1 = Integer.MAX_VALUE;
        int lastWord2 = Integer.MAX_VALUE;

        String curr;
        int tmp;
        for (int i = 0; i < len; i++) {
            curr = file[i];

            if (curr.equals(word1)) {
                lastWord1 = i;
                tmp = (int)Math.abs(lastWord1 - lastWord2);

                if (tmp < min)
                    min = tmp;
            }

            if (curr.equals(word2)) {
                lastWord2 = i;
                tmp = (int)Math.abs(lastWord1 - lastWord2);

                if (tmp < min)
                    min = tmp;
            }

        }

        return min;
    }

    public static void main(String args[]) {
        String[] file = {"1a", "2a", "4b", "9a", "10b", "15a", "19b", "25a"};
        String word1 = "2a";
        String word2 = "15a";
        
        int res = calcDist(file, word1, word2);
        
        System.out.println(res);
    }
}
