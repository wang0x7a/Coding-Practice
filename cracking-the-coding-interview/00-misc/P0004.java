/* P0004:
 * Find the longest palindromic substring.
 * */

public class P0004 {
  public static String lpsRec(String str) {
    int len = str.length();

    int max = 0;
    int start = 0, end = 0;
    for (int i = 0; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        if (isPalindromicX(i, j, str)) {
          if (j - i > end - start) {
            start = i;
            end = j;
          }
        }
      }
    }

    if (end - start > 0)
      return str.substring(start, end + 1);

    return null;

  }

  private static boolean isPalindromic(int i, int j, String str) {
    if (i >= j)
      return true;

    if (str.charAt(i) != str.charAt(j))
      return false;

    return isPalindromic(i + 1, j - 1, str);
  }

  private static boolean isPalindromicX(int i, int j, String str) {
    for (; i < j; i++, j--) {
      if (str.charAt(i) != str.charAt(j))
        return false;
    }

    return true;
  }

  public static void main(String[] args) {
    String[] strs = {"abccba", "a", "abcdba", "abdb", "abababa"};

    for (String s : strs) {
      int len = s.length();

      //System.out.println(s + ": " + isPalindromicX(0, len - 1, s));
      System.out.println(s + ": " + lpsRec(s));
    }
  }
}
