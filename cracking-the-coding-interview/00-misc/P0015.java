/* P0015
 *
 * Implement strStr()
 * Implement strStr(). Returns a pointer to the first occurrence of pat in
 * haystack, or null if pat is not part of haystack.
 */

public class P0015 {
    public static String strStr(String txt, String pat) {
        String res = null;

        if (txt == null || pat == null)
            return null;

        int patLen = pat.length(); 
        if (patLen == 0)
            return null;

        int[] lps = new int[patLen];
        preKMP(pat, lps);

        int txtLen = txt.length();
        boolean flag = false;
        for (int i = 0, k = 0; i < txtLen && !flag; i++) {
            while (k > 0 && txt.charAt(i) != pat.charAt(k))
                k = lps[k];

            if (pat.charAt(k) == txt.charAt(i))
                k++;

            if (k == patLen) {
                flag = true;
                System.out.println(i);
                res = txt.substring(i - patLen + 1);
            }
        }

        return res;
    }

    private static void preKMP(String pat, int[] lps) {
        // k is the length of the longest proper perfix
        int k = 0;
        lps[0] = k;

        int len = pat.length();
        for (int i = 1; i < len; i++) {
            while (k > 0 && pat.charAt(i) != pat.charAt(k))
                k = lps[k];

            if (pat.charAt(i) == pat.charAt(k))
                k++;

            lps[i] = k;
        }
    }

    public static void main(String args[]) {
        String pat = "ababaca";
        String txt = "cdefababacaefd";

        String res = P0015.strStr(txt, pat);
        System.out.println(res);
    }
}
