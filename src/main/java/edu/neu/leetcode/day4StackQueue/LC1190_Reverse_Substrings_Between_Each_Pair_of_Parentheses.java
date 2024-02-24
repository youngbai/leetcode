package edu.neu.leetcode.day4StackQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LC1190_Reverse_Substrings_Between_Each_Pair_of_Parentheses {

    /*
    Thinking:
    - Stack

    Time: O(N^2)
    - Given k pairs of parenthesis, would it be O(n*k)? Since k<=n/2, so it is O(n^2).
    Space: O(N)
     */
    class Solution1_Stack {
        public String reverseParentheses(String s) {
            Deque<Character> stack = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                if (c == ')') {
                    List<Character> list = new ArrayList<>();
                    while (!stack.isEmpty() && stack.peek() != '(') list.add(stack.pop());
                    if (!stack.isEmpty()) stack.pop();
                    for (char ch : list) stack.push(ch);
                } else {
                    stack.push(c);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (char c : stack) sb.append(c);

            return sb.reverse().toString();
        }
    }
}
