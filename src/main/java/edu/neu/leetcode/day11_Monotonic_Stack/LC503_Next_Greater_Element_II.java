package edu.neu.leetcode.day11_Monotonic_Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LC503_Next_Greater_Element_II {

    /*
    Thinking:
    - monotonic stack
    - if we double the nums array logically, the problem becomes the classical monotonic stack problem

    Example:
    - nums = [1,2,3,4,3]
    - double nums
         original   | repeated
        [1,2,3,4,3] | [1,2,3,4,3]
       i=0 1 ................. n-1
    - go through all elements from n-1 to 0, maintain the monotonic stack
    - process:
        Note: from the bottom(end) to top(head)
        [original]  stack
end     1           1234        [2,3,4,-1,4]
        2           234         [2,3,4,-1,4]
        3           34          [2,3,4,-1,4]
        4           4           [2,3,4,-1,4]
begin   3           34          [2,3,4,-1,4]

        [repeated]  stack       res
end     1           1234        [2,3,4,-1,-1]
        2           234         [ ,3,4,-1,-1]
        3           34          [ , ,4,-1,-1]
        4           4           [ , , ,-1,-1]
begin   3           3           [ , , ,  ,-1]

    Time:  O(n), walk through all elements
    Space: O(n), monotonic stack
     */
    class Solution1_monotonic_stack {
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];

            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 2 * n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[i % n]>= stack.peek()) stack.pop();
                res[i % n] = stack.isEmpty()? -1 : stack.peek();
                stack.push(nums[i % n]);
            }

            return res;
        }
    }
}
