/* 1.1 Implement an algorithm to determing if a string has all unique
 * characters. What if you cannot use additional data structures.
 */

import java.util.Map;
import java.util.HashMap;

public class P0101 {
   private static final int CHAR_SET_SIZE = 256;

   public static boolean isUniqueCharsWithBuffer(String str) {
      boolean result = true;
      Map<Character, Integer> charSet = new HashMap<Character, Integer>();

      int len = str.length();
      for (int i = 0; i < len; i++) {
         char c = str.charAt(i);
         if (charSet.containsKey(c)) {
            result = false;
            break;
         }
         else {
            charSet.put(c, 1);
         }
      }

      return result;
   }

   /* With the constrain that all the chars belong to a known set, e.g, ACSII */
   public static boolean isUniqueCharsWithACSIIBuffer(String str) {
      int len = str.length();
      if (len > CHAR_SET_SIZE) return false;

      boolean[] charSet = new boolean[CHAR_SET_SIZE];
      for (int i = 0; i < len; i++) {
         int pos = str.charAt(i);
         if (charSet[pos]) {
            return false;
         }
         charSet[pos] = true;
      }
      return true;
   }

   /* If the size of the char set is known, use a bit map to record.
    * Here, we assume all the chars are lower case letters.
    */
   public static boolean isUniqueCharsWithoutACSIIBuffer(String str) {
      int record = 0;
      int len = str.length();

      if (len > CHAR_SET_SIZE) return false;

      for (int i = 0; i < len; i++) {
         int offset = str.charAt(i) - 'a';
         if ((record & (1 << offset)) > 0) return false;
         else record |= 1 << offset;
      }

     return true;
   }

   /* test routine */
   public static void main(String[] args) {
      String a = "abcdefghijk";
      String b = "abdcafghijk";

      System.out.println("a: " + P0101.isUniqueCharsWithBuffer(a));
      System.out.println("b: " + P0101.isUniqueCharsWithBuffer(b));


      System.out.println("a: " + P0101.isUniqueCharsWithACSIIBuffer(a));
      System.out.println("b: " + P0101.isUniqueCharsWithACSIIBuffer(b));

      System.out.println("a: " + P0101.isUniqueCharsWithoutACSIIBuffer(a));
      System.out.println("b: " + P0101.isUniqueCharsWithoutACSIIBuffer(b));
   }
}
