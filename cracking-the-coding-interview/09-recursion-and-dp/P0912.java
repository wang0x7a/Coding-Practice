/* P0912:
 * Edit distance between two strings
 *
 *        +-----+-----+
 *      i |  -- |  #  |
 * s2     +-----+-----+
 *    i-1 |  /  |  |  |
 *        +-----+-----+
 *          j-1    j
 *              s1
 *
 *  We are assumed to process only one of the strings with the following 
 *  operations, and fix the other.
 *  ??By symmetry, inserting in s1 is equivalent to deleting in s2,
 *  vice versa.??
 *
 *  If we fix s1, and process s2:
 *
 *  substitution (/) : r[i - 1][j - 1] -> r[i][j]
 *  insertion (--)   : r[i][j - 1] -> r[i][j]  (keep s2[i], insert one char
 *                                              before s2[i])
 *  deletion (|)     : r[i - 1][j] -> r[i][j]  (keep s1[j], move s2[i] forward)
 * */

public class P0912 {
  public static int editDist(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();

    // by default, r[0][] = 0, r[][0] = 0
    int[][] r = new int[n + 1][m + 1];
    int[][] s = new int[n + 1][m + 1];
    
    editDistHelper(s1, s2, r, s);

    return r[n][m];
  }

  private static void editDistHelper(String s1, String s2, int[][] r,
      int[][] s) {

    int n = r.length;
    System.out.println("n: " + n);
    int m = r[0].length;
    System.out.println("m: " + m);

    for (int i = 1; i < n; i++) {    // index of s1
      for (int j = 1; j < m; j++) {  // index of s2
        if (s2.charAt(j - 1) == s1.charAt(i - 1))
          r[i][j] = r[i - 1][j - 1];
        else {
          int sub = r[i - 1][j - 1] + 1;
          int ins = r[i][j - 1] + 1;
          int del = r[i - 1][j] + 1;

          r[i][j] = min3(sub, ins, del);
        }
      }
    }
  }

  private static int min3(int a, int b, int c) {
    int res = a;

    if (res > b)
      res = b;
    if (res > c)
      res = c;

    return res;
  }

  public static void main(String[] args) {
    String s1 = "saturday";
    String s2 = "sunday";

    int res = P0912.editDist(s1, s2);
    System.out.println(res);
  }
}
