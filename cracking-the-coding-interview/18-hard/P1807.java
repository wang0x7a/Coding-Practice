/* P1807
 *
 * Given a list of words, write a program to find the longest word made of
 * other words in the list.
 *
 * Example
 * Input : cat, banana, dog, nana, walk, walker, dogwalker
 * Output: dogwalker
 */

public class P1807 {
    public static String longestWord(String[] list) {
        if (list == null)
            return null;

        int len = list.length;
        if (len == 0)
            return null;

        String res = null;
        int resLen = 0;
        for (int i = 0; i < len; i++) {
            if (resLen >= list[i].length())
                continue;

            boolean isCandidate = isCandidateX(list, list[i]); 
            //boolean isCandidate = isCandidate(list, list[i]); 
            if (isCandidate) {
                res = list[i];
                resLen = res.length();
            }
        }

        return res;
    }

    private static boolean isCandidateX(String[] list, String word) {
        int wordLen = word.length();
        boolean[] r = new boolean[wordLen + 1];
        r[0] = true;

        for (int i = 0; i < wordLen; i++) {
            // start from the match position
            if (!r[i])
                continue;

            for (String curr : list) {
                int len = curr.length();
                int end = i + len;
                if (curr.equals(word) || end > wordLen)
                    continue;

                if (word.substring(i, end).equals(curr))
                    r[end] = true;
            }
        }

        return r[wordLen];
    }

    private static boolean isCandidate(String[] list, String word) {
        int wordLen = word.length();
        boolean[] record = new boolean[wordLen + 1];
        record[0] = true;

        String curr;
        int currLen;
        for (int i = 0; i < list.length; i++) {
            curr = list[i];
            currLen = curr.length();

            // we don't count same strings
            if (curr.equals(word) || currLen > wordLen)
                continue;

            kmpSearch(word, curr, record);
        }

        return record[wordLen];
    }

    private static void kmpSearch(String txt, String pat, boolean[] record) {
        if (pat == null || txt == null)
            return;

        int patLen = pat.length();
        int txtLen = txt.length();

        if (patLen > txtLen)
            return;

        int[] lps = new int[patLen];
        preKmp(pat, lps);

        int k = 1;
        for (int i = 0; i < txtLen; i++) {
            /*
            if (record[i + 1])
                continue;
            */

            while (txt.charAt(i) != pat.charAt(k - 1) && k > 1)
                k = lps[k];

            System.out.println(txt + " " + pat);

            if (txt.charAt(i) == pat.charAt(k - 1))
                k = k + 1;

            if (k == patLen + 1) {
                if (record[i + 1] == false)
                    record[i + 1] = true && record[i + 1 - patLen];
                //record[i + 1] = true;
                k = 1;
            }
        }

        return;

    }

    private static void preKmp(String pat, int[] lps) {
        int k = 0;
        lps[0] = 0;

        int len = pat.length();
        for (int i = 1; i < len; i++) {
            while (pat.charAt(i) != pat.charAt(k) && k > 0)
                k = lps[k];

            if (pat.charAt(i) == pat.charAt(k))
                k = k + 1;

            lps[i] = k;
        }

        return;
    }

    public static void main(String args[]) {
        /*
        String str = "abcabda";
        int len = str.length();

        int[] lps = new int[len];
        preKmp(str, lps);

        System.out.println(str);
        for (int i = 0; i < lps.length; i++)
            System.out.print(lps[i]);

        System.out.println();
        */

        /*
        String txt = "abcabda";
        String pat = "cab";

        boolean[] record = new boolean[txt.length() + 1];
        record[0] = true;

        kmpSearch(txt, pat, record);

        for (int i = 0; i < record.length; i++)
            System.out.print(record[i] + " ");

        System.out.println();
        */

        String[] list = {"cat", "bananacat", "banana", "dog", "walk", "walker", "dogwalker"};

        String res = longestWord(list);

        System.out.println(res);

    }
}
