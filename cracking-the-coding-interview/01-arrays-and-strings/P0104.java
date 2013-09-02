/* 1.4 Design an algorithm and write code to remove the duplicate characters 
 * in a string without using any additional buffer. NOTE: One or two additional
 * variables are fine. An extra copy of the array is not.
 * */

public class P0104 {
   public static String removeDuplicate(String str) {
      if (str == null) return str;
      int len = str.length();
      if (len < 2) return str;

      char[] s_array = str.toCharArray();

      /* The tail of the processed string */
      int tail = 1;

      for (int i = 1; i < len; i++) {
         int j = 0;
         /* check the former [0, tail - 1] chars */
         for (j = 0; j < tail; j++) {
            /* reserve the current tail, and skip the duplicate char */
            if (s_array[i] == s_array[j]) {
               s_array[i] = 0;
               break;
            }
         }
         /* if there is no dup in the [0, tail - 1] char,
          * then move tail forward
          * */
         if (j == tail) {
            s_array[tail] = s_array[i];
            /* if i > tail, then [tail + 1, i] should be duplicates.*/
            if (i > tail) s_array[i] = 0;
            tail++;
         }
      }
      
      return new String(s_array);
   }

   public static void main(String[] args) {
      System.out.println(P0104.removeDuplicate("abcdedabfg"));
      System.out.println(P0104.removeDuplicate("abcdefg"));
   }
}
