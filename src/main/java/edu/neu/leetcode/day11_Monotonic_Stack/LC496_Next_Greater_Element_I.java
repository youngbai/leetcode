package edu.neu.leetcode.day11_Monotonic_Stack;

import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;

public class LC496_Next_Greater_Element_I {

    /*
    Thinking:
    - monotonic stack

    Time:  O(n), walk through all elements
    Space: O(n), monotonic stack
     */
    class Solution1_monotonic_stack {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            //  element --> next_greater_element
            Map<Integer, Integer> map = new HashMap<>();
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = nums2.length - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums2[i] >= stack.peek()) stack.pop();
                int nextGreaterNum = stack.isEmpty() ? -1 : stack.peek();
                map.put(nums2[i], nextGreaterNum);
                stack.push(nums2[i]);
            }

            // built the result
            int[] res = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) res[i] = map.get(nums1[i]);
            return res;
        }

    }
}
