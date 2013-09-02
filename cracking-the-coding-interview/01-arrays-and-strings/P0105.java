/* 1.5 Write a method to replace all spaces in a string with '%20' */

public class P0105 {
   public static String replaceWithBuffer(String str) {
      if (str == null) return str;
      int len = str.length();
      int numOfSpaces = 0;
      for (int i = 0; i < len; i++) {
         if (str.charAt(i) == ' ') numOfSpaces++;
      }

      if (numOfSpaces == 0) return str;

      /* reserve the last one for '\0' */
      int newLen = len + 2 * numOfSpaces + 1;
      char[] newStr = new char[newLen];
      newStr[newLen - 1] = '\0';

      for (int i = 0, spaceCount = 0; i < len; i++) {
         char c = str.charAt(i);
         if (c == ' ') {
            newStr[i + spaceCount * 2] = '%';
            newStr[i + spaceCount * 2 + 1] = '2';
            newStr[i + spaceCount * 2 + 2] = '0';

            spaceCount++;
         }
         else
            newStr[i + spaceCount * 2] = str.charAt(i);
      }
      return new String(newStr);
   }

   /* Assume that the length of the original space is long enough */
   public static String replaceWithoutBuffer(char[] str, int n) {
      if (str == null) return null;
      //int len = str.length;
      int len = n;
      int spaceCount = 0;
      for (int i = 0; i < len; i++)
         if (str[i] == ' ') spaceCount++;

      int newLen = len + spaceCount * 2;
      str[newLen] = '\0';
      for (int i = len - 1; i > 0; i--) {
         if (str[i] == ' ') {
            str[newLen - 1] = '0';
            str[newLen - 2] = '2';
            str[newLen - 3] = '%';

            newLen -= 3;
         }
         else {
            str[newLen - 1] = str[i];
            newLen--;
         }
      }
      return new String(str);
   }

   public static void main(String[] args) {
      System.out.println(P0105.replaceWithBuffer("I am Zhe Wang."));
      char[] test = new char[256];
      String str = "I am Zhe Wang.";
      for (int i = 0; i < str.length(); i++) {
         test[i] = str.charAt(i);
      }
      System.out.println(P0105.replaceWithoutBuffer(test, str.length()));
   }
}
