/* P1808
 *
 * Given a string s and an array of smaller strings T, design a method to
 * search s for each small string in T.
 */

public class P1808 {
    // KMP string search
    public static boolean[] searchPats(String txt, String[] pats) {
        if (txt == null || pats == null)
            return null;

        int len = pats.length;
        boolean[] res = new boolean[len];

        for (int i = 0; i < len; i++)
            res[i] = kmpSearch(txt, pats[i]);

        return res;
    }

    private static boolean kmpSearch(String txt, String pat) {
        if (txt == null || pat == null)
            return false;

        int txtLen = txt.length();
        int patLen = pat.length();
        int[] lps = new int[patLen];
        
        preKmp(pat, lps);

        if (txtLen < patLen)
            return false;

        int k = 0;
        for (int i = 0; i < txtLen; i++) {
            while (txt.charAt(i) != pat.charAt(k) && k > 0)
                k = lps[k];

            if (txt.charAt(i) == pat.charAt(k))
                k++;

            if (k == patLen)
                return true;
        }

        return false;
    }

    private static void preKmp(String pat, int[] lps) {
        lps[0] = 0;

        int k = 0;

        int len = pat.length();
        for (int i = 1; i < len; i++) {
            while (pat.charAt(i) != pat.charAt(k) && k > 0)
                k = lps[k];

            if (pat.charAt(i) == pat.charAt(k))
                k++;

            lps[i] = k;
        }
    }

    public static void main(String args[]) {
        String txt = "ilikedogbutcat";
        String[] pats = {"i", "like", "dog", "bug", "but", "cat", "ked", "kid"};

        boolean[] res = searchPats(txt, pats);

        for (boolean b : res)
            System.out.print(b + " ");

        System.out.println();
    }
}
