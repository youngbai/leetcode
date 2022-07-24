package edu.neu.leetcode.day11_Monotonic_Stack;


import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Iterator;

public class LC402_Remove_K_Digits {

    /*
    Thinking:
    - monotonic stack

    Intuition:
    - xyz, k >0
    - if z < y, then let z replace y to make the whole number smaller;
      keep doing this, like let z replace x if z < x
    - then go through next digit

    Example:
    String = 1 3 4 2 2 1 0
        stack       operations                          k
    1   1           stack is empty, push 1              3
    3   13          3>1, push 3                         3
    4   134         4>3, push 4                         3
    2   132         2<4, let 2 replace 4, k--           2
        12          2<3, let 2 replace 3, k--           1
    2   122         2=2, no action, push 2              1
    1   121         1<2, let 1 replace 2, k--           0
    0   1210        k=0, no action, push 0              0

    Corner cases:
    - after go through all digits,
      - if k > 0, stack pop
      - if k = 0, no action
    - result starts with several 0

    Time:  O(n), walk through all elements
    Space: O(n), monotonic stack
     */
    class Solution1_monotonic_stack {
        public String removeKdigits(String num, int k) {
            Deque<Character> stack = new ArrayDeque<>();
            for (char c : num.toCharArray()) {  // go through each digit, build the monotonic stack
                while (!stack.isEmpty() && k > 0 && c < stack.peek()) {
                    stack.pop();
                    k--;
                }
                stack.push(c);
            }

            // keep popping if k > 0
            while (k-- > 0) stack.pop();

            // remove the prefix 0
            StringBuilder sb = new StringBuilder();
            boolean zero = true;
            Iterator<Character> iter = stack.descendingIterator();  // NOTE: stack.descendingIterator()  tail->head
            while (iter.hasNext()) {
                char n = iter.next();
                if (zero && n == '0') continue;
                zero = false;
                sb.append(n);
            }
            return sb.length() == 0? "0" : sb.toString();
        }
    }
}
