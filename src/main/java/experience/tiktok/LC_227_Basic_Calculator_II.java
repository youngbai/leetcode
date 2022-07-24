package experience.tiktok;

import java.util.Deque;
import java.util.LinkedList;

public class LC_227_Basic_Calculator_II {

    /*
    Thinking:
    - stack
    - 4 cases: https://leetcode.com/problems/basic-calculator-ii/Figures/227/calculator_overview.png
      - 4 + 3 * 5
      - 4 + 3 - 5
      - 4 * 3 / 5
      - 4 * 3 - 5

    e.g 22 - 3 * 5
    operand   operator   stack
    0           +        []         INIT
    22          +        []         read 22
    22          -        [20]       read -, push +20 to stack
    3           -        [20]       read 3
    3           *        [20,-3]    read *, push -3 to stack
    5           *        [20,-3]    read 5, end of expression, pop -3, then -3 * 5, push result -15 into stack
                         [20,-15]
    final result: sum(all element in stack) = 20 + (-15)

    Time: O(N)
    Space: O(N)
    */

    class Solution1_Stack {
        public int calculate(String s) {
            if (s == null || s.length() == 0) return 0;

            Deque<Integer> stack = new LinkedList<>();
            int operand = 0;
            char operator = '+';
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    operand = operand * 10 + (c - '0');
                }
                if ((!Character.isDigit(c) && !Character.isWhitespace(c)) || i == s.length() - 1) {
                    if (operator == '+') {
                        stack.push(operand);
                    } else if (operator == '-') {
                        stack.push(-operand);
                    } else if (operator == '*') {
                        stack.push(stack.pop() * operand);
                    } else if (operator == '/') {
                        stack.push(stack.pop() / operand);
                    }
                    operator = c;
                    operand = 0;
                }
            }

            int res = 0;
            while (!stack.isEmpty()) {
                res += stack.pop();
            }
            return res;
        }
    }
}
