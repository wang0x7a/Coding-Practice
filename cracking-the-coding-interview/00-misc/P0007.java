/* P0007
 *
 * Regular Expression Matching
 * Implement regular expression matching with support for '.' and '*'.
 *
 * The matching should cover the entire input string (not partial).
 *
 * '.' matches any single characters.
 * '*' matches zero or more of the preceding element.
 * */

public class P0007 {
  public static boolean isMatch(String txt, String pat) {
    boolean res = false;

    int txtLen = txt.length();
    int patLen = pat.length();

    int i = 0, j = 0;
    char currentPatChar, nextPatChar;
    while (j < patLen - 1) {
      currentPatChar = pat.charAt(j);
      if (currentPatChar != '*') {
        nextPatChar = pat.charAt(j + 1);
      }
    }


    return res;
  }

  public static boolean isMatch2(String txt, String pat) {
    boolean res = false;

    int txtLen = txt.length();
    int patLen = pat.length(); 

    int i = 0;
    while (i < txtLen) {
      for (int j = 0; j < patLen; j++) {
        if (i + j > patLen)
          return false;

        if (txt.charAt(i + j) != pat.charAt(j))
          break;
      }
      i++;
    }

    return res;
  }

  public static void main(String[] args) {
  }
}
