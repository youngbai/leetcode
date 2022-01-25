package edu.neu.leetcode.day23_dp;

import java.util.Arrays;

public class LC198_House_Robber {

    /*
    Thinking:
    - DP, Iteration

    Example:
    nums = [2,1,1,2]
    A   = 0 2 2 3 4

    - Intuition: rob the current(i-th) house or not
    - init:
      A[0]=0, A[1]=nums[1]
    - transit function:
      f(i)=max(f(i-1), f(i-2)+nums(i-1))

    Time:  O(N)
    Space: O(N)
     */
    class Solution1_DP_Iteration {
        public int rob(int[] nums) {
            int N = nums.length;
            int[] A = new int[N + 1];
            // base case
            A[1] = nums[0];
            // transite function
            for (int i = 2; i < N + 1; i++) {
                A[i] = Math.max(A[i - 1], A[i - 2] + nums[i - 1]);
            }
            // result
            return A[N];
        }
    }


    class Solution1_DP_Recursion {
        public int rob(int[] nums) {
            int N = nums.length;
            int[] A = new int[N + 1];

            // init
            Arrays.fill(A, -1);

            // base case
            A[0] = 0;
            A[1] = nums[0];

            return rob(nums, A, N);
        }

        private int rob(int[] nums, int[] A, int i) {
            // hit mem success
            if (A[i] != -1) return A[i];

            // hit mem fail
            A[i] = Math.max(rob(nums, A, i - 1), rob(nums, A, i - 2) + nums[i - 1]);
            return A[i];
        }
    }

    /*
    Thinking:
    - DP

    Time:  O(N)
    Space: O(1)
     */
    class Solution2_DP_Optimized {
        public int rob(int[] nums) {
            int prepreMax = 0, preMax = 0;
            for (int i = 0; i < nums.length; i++) {
                int curMax = Math.max(preMax, prepreMax + nums[i]);
                prepreMax = preMax;
                preMax = curMax;
            }
            return preMax;
        }
    }
}
