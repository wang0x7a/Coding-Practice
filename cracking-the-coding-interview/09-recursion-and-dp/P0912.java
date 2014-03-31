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
 *  insertion: r[i - 1][j] -> r[i][j]
 *  keep s1[j], but insert one char before s2[i], so for s2, the next
 *  comparison still in s2[i].
 *  deletion () :
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

  }

  public static void main(String[] args) {}
}
