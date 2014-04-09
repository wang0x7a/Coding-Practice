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
    boolean[] r = new boolean[len];

    int start, end;
    for (int i = 0; i < len; i++) {
      end = i;

      boolean isMatch = false;
      for (String word : dict) {
        int wordLen = word.length();
        start = end - wordLen + 1;

        if (start < 0) {
          isMatch = isMatch || false;
          continue;
        }

        String sub = s.substring(start, end + 1);
        boolean cmp = sub.equals(word);
        if (start == 0)
          isMatch = isMatch || cmp; 
        else
          isMatch = isMatch || (r[start - 1] && cmp);
      }

      r[i] = isMatch;

    }

    res = r[len - 1];

    return res;
  }

  public static void main(String[] args) {
    String s = "leetcode";
    //String[] dict = {"leet", "code"};
    String[] dict = {"code", "leet"};

    boolean res = P0005.wordBreak(s, dict);
    System.out.println(res);
  }
}
