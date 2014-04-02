/* P0003:
 * Find the longest common sequence between two strings.
 * */

public class P0003 {
  private class Result {
    int maxLen;
    int i;
    int j;
  }

  public int lcs(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();

    int[][] r = new int[n + 1][m + 1];

    Result res = lcsHelper(s1, s2, r);
    for (int i = 0; i < res.maxLen; i++)
      System.out.print(s1.charAt(res.i + i));
    System.out.println();

    return res.maxLen;
  }

  private Result lcsHelper(String s1, String s2, int[][] r) {
    Result res = new Result();

    int n = r.length;
    int m = r[0].length;

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          r[i][j] = r[i - 1][j - 1] + 1;

          if (r[i][j] > res.maxLen) {
            res.maxLen = r[i][j];
            // record the start of the LCS
            res.i = i - r[i][j];
            res.j = j - r[i][j];
          }
        }
        else {
          r[i][j] = 0;
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    P0003 p0003 = new P0003();

    String s1 = "satrday";
    String s2 = "sunday";

    int res = p0003.lcs(s1, s2);
    System.out.println(res);
  }
}
