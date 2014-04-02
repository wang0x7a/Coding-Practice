/* P0002:
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an interger or another
 * expression.
 *
 * Example:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * */

import java.util.Stack;

public class P0002 {
  public static int evalRPN(String[] tokens) {
    int res = 0;

    String operators = "+-*/";

    Stack<String> stack = new Stack<String>();

    for (String t : tokens) {
      if (!operators.contains(t))
        stack.push(t);
      else {
        int index = operators.indexOf(t);
        int a = Integer.valueOf(stack.pop());
        int b = Integer.valueOf(stack.pop());
        
        switch (index) {
          case 0:
            res = b + a;
            break;
          case 1:
            res = b - a;
            break;
          case 2:
            res = b * a;
            break;
          case 3:
            res = b / a;
            // we need to put a break after each case, otherwise, after the
            // case, it will jump to the next adjacent case (or default)
            break;
          default:
            res = Integer.MIN_VALUE;
            break;
        }
        stack.push(String.valueOf(res));
      }
    }
    res = Integer.valueOf(stack.pop());
    return res;
  }

  public static void main(String[] args) {
    //String[] exp = {"2", "1", "+", "3", "*"};
    String[] exp = {"4", "13", "5", "/", "+"};

    int res = P0002.evalRPN(exp);
    System.out.println(res);
  }
}
