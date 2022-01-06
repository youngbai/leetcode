package edu.neu.leetcode.day11_Monotonic_Stack;

import edu.neu.leetcode.commonbean.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LC1019_Next_Greater_Node_In_Linked_List {

    /*
    Thinking:
    - preprocess
      - convert the ListNode to an array
      - reverse the ListNode
    - monotonic stack

    Time:  O(n), walk through all elements
    Space: O(n), monotonic stack
     */
    class Solution1_monotonic_stack {
        public int[] nextLargerNodes(ListNode head) {
            // to array
            List<Integer> nums = new ArrayList<>();
            for (ListNode cur = head; cur != null; cur = cur.next) nums.add(cur.val);

            int n = nums.size();
            int[] res = new int[n];
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums.get(i) >= stack.peek()) stack.pop();
                res[i] = stack.isEmpty() ? 0 : stack.peek();
                stack.push(nums.get(i));
            }
            return res;
        }
    }

}
