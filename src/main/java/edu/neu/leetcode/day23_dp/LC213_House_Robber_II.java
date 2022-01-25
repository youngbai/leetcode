package edu.neu.leetcode.day23_dp;

public class LC213_House_Robber_II {

    /*
    Thinking:
    - DP, Iteration
    - Hint: Since House[1] and House[n] are adjacent, they cannot be robbed together. Therefore, the problem becomes
    to rob either House[1]-House[n-1] or House[2]-House[n], depending on which choice offers more money.
    Now the problem has degenerated to the House Robber, which is already been solved.
    - Problem: rob house 1 ~ 9
      Sub-problem 1: rob house 1 ~ 8
      Sub-problem 2: rob house 2 ~ 9

    Time:  O(N)
    Space: O(1)
     */
    class Solution1_DP_Iteration {
        public int rob(int[] nums) {
            int N = nums.length;
            // base case
            if (N == 0) return 0;
            if (N == 1) return nums[0];
            // iteration
            int max1 = rob(nums, 0, N - 2); // rob [0, N-2]
            int max2 = rob(nums, 1, N - 1); // rob [1, N-1]
            return Math.max(max1, max2);
        }

        private int rob(int[] nums, int start, int end) {
            // p1, p2, cur
            int p1 = 0, p2 = 0;
            for (int i = start; i <= end; i++) {
                int cur = Math.max(p2, p1 + nums[i]);
                p1 = p2;
                p2 = cur;
            }
            return p2;
        }
    }
}
