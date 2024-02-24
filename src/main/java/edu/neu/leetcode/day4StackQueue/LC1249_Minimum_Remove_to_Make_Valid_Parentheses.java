package edu.neu.leetcode.day4StackQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class LC1249_Minimum_Remove_to_Make_Valid_Parentheses {

    /*
    Thinking:
    - forward, backward

    e.g.
    ())
    )()

    Time: O(N), for loop
    Space: O(N), StringBuilder
     */
    class Solution1_Forward_Backward {
        public String minRemoveToMakeValid(String s) {
            // forward pass: Remove all invalid ")"
            StringBuilder sb = new StringBuilder();
            int open = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    open++;
                } else if (c == ')') {
                    if (open == 0) continue;
                    open--;;
                }
                sb.append(c);
            }

            if (open == 0) return sb.toString();

            // backward pass: Remove the rightmost "("
            StringBuilder res = new StringBuilder();
            for (int i = sb.length() - 1; i >= 0; i--) {
                char c = sb.charAt(i);
                if (c == '(' && open > 0) {
                    open--;
                    continue;
                }
                res.append(c);
            }


            return res.reverse().toString();
        }
    }


    /*
    Thinking:
    - Stack, Set
    - memorize the index to be removed

    Time: O(N), for loop
    Space: O(N), Stack, Set
     */
    class Solution2_Stack {
        public String minRemoveToMakeValid(String s) {
            Set<Integer> indexesToRemove = new HashSet<>();
            Deque<Integer> stack = new ArrayDeque<>();

            // memorize the index to remove
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    stack.push(i);
                } else if (c == ')') {
                    if (stack.isEmpty()) {
                        indexesToRemove.add(i);
                    } else {
                        stack.pop();
                    }
                }
            }

            // Put any indexes remaining on stack into the set, because they should be removed
            while (!stack.isEmpty()) indexesToRemove.add(stack.pop());

            // go through s, remove the char in indexesToRemove
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (!indexesToRemove.contains(i)) {
                    sb.append(s.charAt(i));
                }
            }

            return sb.toString();
        }
    }
}
