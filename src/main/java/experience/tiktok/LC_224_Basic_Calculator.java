package experience.tiktok;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC_224_Basic_Calculator {

    /*
    Thinking:
    - reverse order
    - 123 -> 100+20+3
    - Stack: Top (7-8+9) Bottom  ->  calculate 7-8 first, then -1+9 second
    - corner case:
        - original: -1 + 2
        - problem: cannot calculate -1
        - solution: add 0 if stack peek() is a sign

    Time:  O(N)
    Space: O(N)
    */
    class Solution1_Stack_Reverse_Order {

        public int calculate(String s) {

            Deque<Object> stack = new ArrayDeque<>();

            int operand = 0;
            int n = 0;

            // reversal order
            for (int i = s.length() - 1; i >= 0; i--) {

                char ch = s.charAt(i);

                if (Character.isDigit(ch)) {		// NOTE: Character.isDigit(char)

                    // Forming the operand - in reverse order.
                    operand = (int) Math.pow(10, n) * (int) (ch - '0') + operand;
                    n += 1;

                } else if (ch != ' ') {
                    if (n != 0) {

                        // Save the operand on the stack
                        // As we encounter some non-digit.
                        stack.push(operand);
                        n = 0;
                        operand = 0;

                    }
                    if (ch == '(') {

                        int res = evaluateExpr(stack);
                        stack.pop();	// pop out the `)`

                        // Append the evaluated result to the stack.
                        // This result could be of a sub-expression within the parenthesis.
                        stack.push(res);

                    } else {
                        // For other non-digits (-+operators or ) closing parenthesis) just push onto the stack.
                        stack.push(ch);
                    }
                }
            }

            // Push the last operand to stack, if any.
            if (n != 0) {
                stack.push(operand);
            }

            // Evaluate any left overs in the stack.
            return evaluateExpr(stack);
        }


        public int evaluateExpr(Deque<Object> stack) {

            // If stack is empty or the expression starts with
            // a symbol, then append 0 to the stack.
            // i.e. -2+1, in stack, [1, '-', 2, '-'] becomes [1, '-', 2, '-', 0]
            if (stack.isEmpty() || !(stack.peek() instanceof Integer)) {
                stack.push(0);
            }

            int res = (int) stack.pop();

            // Evaluate the expression till we get corresponding ')'
            while (!stack.isEmpty() && !((char) stack.peek() == ')')) {

                char sign = (char) stack.pop();

                if (sign == '+') {
                    res += (int) stack.pop();
                } else {
                    res -= (int) stack.pop();
                }
            }
            return res;
        }

    }


    /*
    Thinking:
    - 123 -> 100+20+3
    - Stack: previous_result, previous_sign
    - 1 - 2  -->  1 + (-2)

    Time:  O(N)
    Space: O(N)
    */

    class Solution2_Stack {
        public int calculate(String s) {

            Deque<Integer> stack = new ArrayDeque<>();

            int operand = 0;
            int result = 0; // For the on-going result
            int sign = 1;  // 1 means positive, -1 means negative

            for (int i = 0; i < s.length(); i++) {

                char ch = s.charAt(i);
                if (Character.isDigit(ch)) {

                    // Forming operand, since it could be more than one digit
                    operand = 10 * operand + (int) (ch - '0');

                } else if (ch == '+') {

                    // Evaluate the expression to the left, with result, sign, operand
                    result += sign * operand;

                    // Save the recently encountered '+' sign
                    sign = 1;

                    // Reset operand
                    operand = 0;

                } else if (ch == '-') {

                    result += sign * operand;
                    sign = -1;
                    operand = 0;

                } else if (ch == '(') {

                    // Push the result and sign on to the stack, for later
                    // We push the result first, then sign
                    stack.push(result);
                    stack.push(sign);

                    // Reset operand and result, as if new evaluation begins for the new sub-expression
                    sign = 1;
                    result = 0;

                } else if (ch == ')') {

                    // Evaluate the expression to the left with result, sign and operand
                    result += sign * operand;

                    // ')' marks end of expression within a set of parenthesis
                    // Its result is multiplied with sign on top of stack
                    // as stack.pop() is the sign before the parenthesis
                    result *= stack.pop();

                    // Then add to the next operand on the top.
                    // as stack.pop() is the result calculated before this parenthesis
                    // (operand on stack) + (sign on stack * (result from parenthesis))
                    result += stack.pop();

                    // Reset the operand
                    operand = 0;
                }
            }
            return result + (sign * operand);
        }
    }


}
