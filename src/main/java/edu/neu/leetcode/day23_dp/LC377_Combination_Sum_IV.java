package edu.neu.leetcode.day23_dp;

import java.util.Arrays;

public class LC377_Combination_Sum_IV {

    /*
    Thinking:
    - DFS, Backtracking
    - TLE
     */
    class Solution1_Backtracking {
        public int combinationSum4(int[] nums, int target) {
            Arrays.sort(nums);
            return dfs(nums, target);
        }

        private int dfs(int[] nums, int target) {
            if (target < 0) return 0;

            if (target == 0) return 1;

            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                if (target - nums[i] < 0) return res;
                res += dfs(nums, target - nums[i]);
            }
            return res;
        }
    }



    /*
    Thinking:
    - DFS, Backtracking
    - DP (top-down)
      - dfs(target) = sum( dfs(target - nums[i]) ), if target >= nums[i], where 0 <= i < nums.length

    Time: O(T*N)
    - T: target value
    - N: number of elements in the input array
    - Thanks to the memoization technique, for each invocation of dfs(remain), it would be evaluated only once,
      for each unique input value. In the worst case, we could have T different input values
    - For each invocation of dfs(remain), in the worst case it takes O(N) time for the non-recursive part
    - To sum up, the overall time complexity of the algorithm is T*O(N) = O(T*N)

    Space: O(T), int[] dp
     */
    class Solution2_Backtracking_DP_Top_Down {
        public int combinationSum4(int[] nums, int target) {
            // Arrays.sort(nums);  // minor optimization
            int[] dp = new int[target + 1]; // O(T)
            Arrays.fill(dp, -1);
            return dfs(nums, target, dp);
        }

        private int dfs(int[] nums, int target, int[] dp) {
            // find it
            if (target == 0) return 1;

            // memo hit success
            if (dp[target] != -1) return dp[target];

            // memo hit fail
            int res = 0;
            for (int i = 0; i < nums.length; i++) { // O(N)
                if (target >= nums[i])
                    res += dfs(nums, target - nums[i], dp);
            }
            dp[target] = res;   // save result into memo
            return res;
        }
    }

    /*
    Thinking:
    - DP, Bottom-Up

    Example:
    i nums[j]
    1   1   dp[1]=1
    2   1   dp[2]=1
    2   2   dp[2]=2
    3   1   dp[3]=2
    3   2   dp[3]=2+1=3
    3   3   dp[3]=2+1+1=4
    4   1   dp[4]=4
    4   2   dp[4]=4+2
    4   3   dp[4]=4+2+1=7

    Time:  O(T*N)
    Space: O(T), int[] dp
    */
    class Solution3_DP_Bottom_Up {
        public int combinationSum4(int[] nums, int target) {
            Arrays.sort(nums);  // prune1: minor optimization for prune2 early stopping
            int[] dp = new int[target + 1];
            dp[0] = 1;  // init dp
            for (int i = 1; i < dp.length; i++) {   // O(T)
                for (int j = 0; j < nums.length; j++) { // O(N)
                    if (i >= nums[j])
                        dp[i] += dp[i - nums[j]];
                    else break; // prune2: minor optimization, early stopping
                }
            }
            return dp[target];
        }
    }

}
