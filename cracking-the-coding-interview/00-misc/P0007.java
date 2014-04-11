/* P0007
 *
 * Regular Expression Matching
 * Implement regular expression matching with support for '.' and '*'.
 *
 * The matching should cover the entire input string (not partial), which
 * means, two strings should match exactly.
 *
 * '.' matches any single characters.
 * '*' matches zero or more of the preceding element.
 * */

public class P0007 {
  public static boolean isMatch(String txt, String pat) {
    if (txt == null || pat == null)
      return false;

    int patLen = pat.length();
    int txtLen = txt.length();

    if (patLen == 0)
      return txtLen == 0;

    if (txtLen == 0)
      return false;

    // if the next char of p is NOT '*', then it must match the current char
    // of s. Continue pattern matching with the next character of both s and
    // p. Specially, when the length of pat is 1, its next char is null,i
    // clearly not '*'.
    // Moreover, if the input string and the pattern are allowed to be
    // partially matched, it will be more complicated.
    if (patLen == 1 || pat.charAt(1) != '*') {
      if (txtLen == 0)
        return false;

      if (pat.charAt(0) != txt.charAt(0) && pat.charAt(0) != '.')
        return false;

      return isMatch(txt.substring(1), pat.substring(1));
    }

    // if the next char of pat is '*', then we do a brute force exhausitive 
    // matching of 0, 1, or more repeats of current char of pat, until we
    // could not match any more chars.

    // here, we need to start from -1 instead of 0, because the brute force
    // matching includes 0 repeat of current char of pat.
    // if i < 0, we don't compare the current char of pat and that of txt.
    int i = -1;
    while (i < txtLen && 
        (i < 0 || pat.charAt(0) == txt.charAt(i) || pat.charAt(0) == '.')) {
      if (isMatch(txt.substring(i + 1), pat.substring(2)))
        return true;
      i++;
    }
    return false;
  }

  public static void main(String[] args) {
    String[] pairs = {"aa-a", "aa-aa", "aaa-aa", "aa-a*", "aa-.*", "ab-.*",
                    "aab-c*a*b"};

    for (String p : pairs) {
      String[] a = p.split("-");
      String txt = a[0], pat = a[1];

      System.out.println(a[0] + "\t" + a[1] + "\t" + isMatch(txt, pat));
    }
  }
}
