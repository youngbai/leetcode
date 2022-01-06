package edu.neu.leetcode.day11_Monotonic_Stack;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayDeque;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Monotonic_Stack_Tpl {

    /*
    Thinking:
    - monotonic stack
        - all elements are in order
        - when you push a new element into stack, pop up all the elements <= than this new element,
          so that keep the stack monotonic

    Algo:
    - walk through all elements, O(n)
        - maintain the monotonic stack by popping up all the elements <= than current element
        - save current result into a result array
        - push current element

    Time:  O(n), walk through all elements
    Space: O(n), monotonic stack
     */
    class Solution {

        public int[] nextGreaterElement(int[] nums) {
            int[] res = new int[nums.length];

            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = nums.length - 1; i >= 0; i--) {
                // pop all the element <= current element
                while (!stack.isEmpty() && nums[i] >= stack.peek()) stack.pop();
                // save current result
                res[i] = stack.isEmpty() ? -1 : stack.peek();
                // push current element
                stack.push(nums[i]);
            }
            return res;
        }
    }


    @Test
    public void test() {
        int[] nums = {2, 1, 2, 4, 3};
        int[] res = new Solution().nextGreaterElement(nums);
        assertArrayEquals(new int[]{4, 2, 4, -1, -1}, res);
    }

}
