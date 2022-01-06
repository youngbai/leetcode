package edu.neu.leetcode.day12_Monotonic_Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC1438_Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_or_Equal_to_Limit {

    /*
    Thinking:
    - two Monotonic Queue
    - sliding window
      - Each time expend the window to the right
      - Shrink the left side to make it valid
      - make sure the dropped element is also drop in min Deque and max Deque

    Time : O(N), go through all element
    Space: O(N), min Deque, max Deque

    Ref:
    https://www.youtube.com/watch?v=p8-f0_CwWLk
     */

    class Solution1_Two_Monotonic_Queue {
        public int longestSubarray(int[] nums, int limit) {
            int N = nums.length;
            int l = 0, res = 0;
            Deque<Integer> minD = new ArrayDeque<>(), maxD = new ArrayDeque<>();

            for (int r = 0; r < N; r++) { // r is the right side of window
                // maintain the minD, maxD
                while (!minD.isEmpty() && minD.peekLast() > nums[r]) minD.pollLast();
                while (!maxD.isEmpty() && maxD.peekLast() < nums[r]) maxD.pollLast();
                minD.offerLast(nums[r]);
                maxD.offerLast(nums[r]);

                // shrink left side of window
                while (maxD.peekFirst() - minD.peekFirst() > limit) {
                    if (minD.peekFirst() == nums[l]) minD.pollFirst();
                    if (maxD.peekFirst() == nums[l]) maxD.pollFirst();
                    l++;
                }
                res = Math.max(res, r - l + 1);
            }
            return res;
        }
    }



}
