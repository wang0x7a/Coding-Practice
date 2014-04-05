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

  // Finding the longest palindromic substring in a string is equivalent to
  // retrieving the longest common sequence between this string and its reverse.
  public static int lpsDP(String str) {
    int res = 0;
    
    int len = str.length();
    int[][] r = new int[len + 1][len + 1];
    
    String rev = new StringBuilder(str).reverse().toString();

    res = lpsDPHelper(str, rev, r);

    return res;
  }

  public static int lpsDPHelper(String str, String rev, int[][] r) {
    int max = 0;

    int n = r.length;
    int len = n - 1;
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < n; j++) {
        if (str.charAt(i - 1) == rev.charAt(j - 1)) {
          r[i][j] = r[i - 1][j - 1] + 1;

          if ((r[i][j] > max) && (i + j > len))
            max = r[i][j];
        }
        else {
          r[i][j] = 0;
        }
      }
    }


    return max;
  }

  public static String lpsDP2(String str) {
    String res = null;
    int start = 0, end = 0;

    int len = str.length();
    int[][] r = new int[len][len];
    for (int i = 0, j = 1; i < len - 1; i++, j++) {
      r[i][i] = 1;

      if (str.charAt(i) == str.charAt(j)) {
        r[i][j] = 1;
        start = i;
        end = j;
      }
    }
    r[len - 1][len - 1] = 1;

    for (int i = 2; i < len; i++) {
      for (int j = 0; j < i - 1; j++) {
        if (str.charAt(i) == str.charAt(j) && r[j + 1][i - 1] == 1) {
          r[j][i] = 1;

          if (i - j > end - start) {
            start = j;
            end = i;
          }
        }
      }
    }

    if (start != end)
      res = str.substring(start, end + 1);

    return res;
  }

  public static void main(String[] args) {
    String[] strs = {"abccba", "a", "abcdba", "abdb", "abababa", "aa"};

    for (String s : strs) {
      int len = s.length();

      //System.out.println(s + ": " + isPalindromicX(0, len - 1, s));
      System.out.println(s + ": " + lpsRec(s));
      System.out.println(s + ": " + lpsDP2(s));
    }
  }
}
