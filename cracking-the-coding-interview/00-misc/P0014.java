/* P0014
 *
 * Valid Parentheses
 * Given a string containing just the characters '(', ')', '[', ']', '{', '}',
 * determine if the input string is valid. The brackets must close in the 
 * correct order, "()", "()[]" and "[()]" are valid but "(]" and "([)]" are not.
 */

import java.util.Stack;

public class P0014 {
    public static boolean isValid(String str) {
        Stack<Character> stack = new Stack<Character>();

        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);

            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else {
                char tmp = stack.pop();
                switch (c) {
                    case ')':
                        if (tmp != '(')
                            return false;
                        break;
                    case ']':
                        if (tmp != '[')
                            return false;
                        break;
                    case '{':
                        if (tmp != '{')
                            return false;
                        break;
                    default:
                        break;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String args[]) {
        String[] cases = new String[]{"()", "()[]", "[()]", "{[]}", "(]", "([)]"};

        for (String s : cases) {
            System.out.println(s + ":\t" + P0014.isValid(s));
        }
    }
}
