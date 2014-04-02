/* P0003:
 * Find the longest common sequence between two strings.
 * */

public class P0003 {
  public static int lcs(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();

    int[][] r = new int[n + 1][m + 1];
    int[][] s = new int[n + 1][m + 1];

    int res = lcsHelper(s1, s2, r, s);
    return res;
  }

  private static int lcsHelper(String s1, String s2, int[][] r, int[][] s) {
    int res = 0;

    int n = r.length;
    int m = r[0].length;

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          r[i][j] = r[i - 1][j - 1] + 1;

          if (r[i][j] > res)
            res = r[i][j];
        }
        else {
          r[i][j] = 0;
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    String s1 = "satrday";
    String s2 = "sunday";

    int res = P0003.lcs(s1, s2);
    System.out.println(res);
  }
}
