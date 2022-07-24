package edu.neu.leetcode.day13_Sliding_Window;

public class LC209_Minimum_Size_Subarray_Sum {

    /*
    Thinking:
    - Sliding Window

    Goals
    - contiguous subarray
    - sum >= target

    Example:
    target = 7, nums = [2,3,1,2,4,3]

    num     sum operations     len
    2       2
    23      5
    231     6
    2312    8   C               4
    312     6   shrink left
    3124    10  C               4
    124     7   C shrink left   3
    24      6   shrink left
    243     9   C               3
    43      7   C shrink left   2

    Time:  O(N)
    Space: O(N)
    */
    class Solution1_Sliding_Window {
        public int minSubArrayLen(int target, int[] nums) {
            int minlen = Integer.MAX_VALUE, left = 0, sum = 0;
            for (int r = 0; r < nums.length; r++) {
                // enter window
                sum += nums[r];

                // shrink left
                while (sum >= target) {
                    minlen = Math.min(minlen, r - left + 1);
                    sum -= nums[left];
                    left++;
                }
            }
            return minlen == Integer.MAX_VALUE? 0: minlen;
        }
    }
}
