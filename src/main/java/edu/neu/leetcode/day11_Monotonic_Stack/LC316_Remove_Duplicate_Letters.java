package edu.neu.leetcode.day11_Monotonic_Stack;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;


public class LC316_Remove_Duplicate_Letters {

    /*
    Thinking:
    - monotonic stack, to store the char
    - Algo:
      if next char is smaller, and previous char is not last occurrence,
      then pop the previous char
    - int[] last stores the index of the last occurrence of the letter
    - Set<Integer> visited stores the visited char
      - ignore visited item
    - synchronize stack and visited
      - if stack pop an item, visited should remove it
      - if stack push an item, visited should add it

    Example:
    String = C B A C D C B C
    stack:   C      # C is not visited, and stack is empty
             B      # B < C && C is not last occurrence
             A      # A < B && B is not last occurrence
             AC     # C > A, C is not visited
             ACD    # D > C, D is not visited
             ACD    # C is visited, ignore
             ACDB   # B < D, D is last occurrence, do not pop
             ACDB   # C is visited, ignore

    Time:  O(n), walk through all elements
    Space: O(n), monotonic stack
     */
    class Solution {
        public String removeDuplicateLetters(String s) {
            Deque<Integer> stack = new ArrayDeque<>();

            int[] last = new int[128];  // The index of the last occurrence of the letter
            for (int i = 0; i < s.length(); i++) last[s.charAt(i)] = i;

            Set<Integer> visited = new HashSet<>(); // store visited char
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i);
                if (!visited.add(c)) continue;  // already visited
                while (!stack.isEmpty() && c < stack.peek() && i < last[stack.peek()]) visited.remove(stack.pop());
                stack.push(c);
            }
            StringBuilder sb = new StringBuilder();
            for (int i : stack) sb.append((char)i); // for-comprehension follows stack pop order
            return sb.reverse().toString();
        }
    }
}
