/* P0901
 * A child is running up a staircase with n steps, and can hop either 1 step,
 * 2 steps, or 3 steps at a time. Implement a method to count how many possible
 * ways the child can run up the stairs.
 * */

public class P0901 {
  public static int countWays(int n) {
    if (n == 0)
      return 1;
    if (n < 0)
      return 0;

    return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);

  }

  public static void main(String[] args) {
    int res = P0901.countWays(3);

    System.out.println(res);
  }
}
