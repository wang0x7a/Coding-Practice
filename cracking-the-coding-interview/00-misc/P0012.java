/* P0012
 *
 * String to Integer(atoi)
 * Implement atoi to convert a string to an integer.
 *
 * Notes:
 * 1) Question to ask:
 *    * How to deal with whitespaces? Especially, for those within a string.
 *    * Is string encoded via ASCII?
 */

public class P0012 {
    public static int str2Int(String str) {
        int res = 0;

        if (str == null || str.length() == 0)
            return 0;

        int len = str.length();

        int i;
        for (i = 0; str.charAt(i) == ' '; i++)
            ;

        int sign = (str.charAt(i) == '-') ? -1 : 1;
        if (str.charAt(i) == '-' || str.charAt(i) == '+')
            i++;

        for (; i < len && str.charAt(i) >= '0' && str.charAt(i) <= '9'; i++)
            res = 10 * res + (str.charAt(i) - '0');

        res *= sign;

        return res;
    }

    public static void main(String args[]) {
        String s = "  +123";
        int r = P0012.str2Int(s);

        System.out.println(r);
    }
}
