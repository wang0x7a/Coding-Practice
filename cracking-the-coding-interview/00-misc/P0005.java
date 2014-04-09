/* P0005
 *
 * Word Break
 * Given a string s and a dictionary of words dict, determine if s can be
 * segmented into space-separated sequence of one or more dictionary words.
 * For example, given s = "leetcode", dict = ["leet", "code"], return true
 * because "leetcode" can be segmented as "leet code".
 * */

public class P0005 {
  public static boolean wordBreak(String s, String[] dict) {
    boolean res = false;

    int len = s.length();
    boolean[] r = new boolean[len + 1];
    // set a sentinel to avoid comparing start to 0.
    r[0] = true;

    int start, end;
    for (int i = 1; i <= len; i++) {
      end = i;

      boolean isMatch = false;
      for (String word : dict) {
        // if we only need to find one optimal solution, we don't have to
        // iterate the whole dict.
        if (isMatch)
          break;

        int wordLen = word.length();
        start = end - wordLen + 1;

        if (start <= 0) {
          isMatch = isMatch || false;
          continue;
        }

        String sub = s.substring(start - 1, end);
        boolean cmp = sub.equals(word);
        isMatch = isMatch || (r[start - 1] && cmp);
      }

      r[i] = isMatch;

    }

    res = r[len];

    return res;
  }

  public static void main(String[] args) {
    String s = "leetcode";
    //String[] dict = {"leet", "code"};
    String[] dict = {"code", "leet"};
    //String[] dict = {"cod", "leet"};

    boolean res = P0005.wordBreak(s, dict);
    System.out.println(res);
  }
}
