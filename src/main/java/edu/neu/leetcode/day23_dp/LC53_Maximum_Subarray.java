package edu.neu.leetcode.day23_dp;

public class LC53_Maximum_Subarray {

    /*
    Thinking:
    - DP
    - Kadane's Algorithm
      Ref: https://www.interviewbit.com/blog/maximum-subarray-sum/#:~:text=Kadane's%20Algorithm%20is%20an%20iterative,ending%20at%20the%20previous%20position.&text=Define%20two%2Dvariable%20currSum%20which,stores%20maximum%20sum%20so%20far.

    Intuition:
    - As expected, any subarray whose sum is positive is worth keeping. Let's start with an empty array,
    and iterate through the input, adding numbers to our array as we go along. Whenever the sum of the array
    is negative, we know the entire array is not worth keeping, so we'll reset it back to an empty array.

    Time:  O(N), We iterate through every element of nums exactly once.
    Space: O(1)
     */
    class Solution1_DP_KadaneAlgo {
        public int maxSubArray(int[] nums) {
            int curMax = 0, sumMax = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                curMax += nums[i];
                // if curMax > sumMax, update sumMax equals to curMax
                if (curMax > sumMax) sumMax = curMax;
                // if curMax is negative, throw it away, reset curMax = 0
                if (curMax < 0) curMax = 0;
            }
            return sumMax;
        }
    }


    /*
    Thinking:
    - Divide and Conquer, like merge sort

    Intuition:
    - If we were to split our input in half, then logically the optimal subarray either:
      - Uses elements only from the left side
      - Uses elements only from the right side
      - Uses a combination of elements from both the left and right side
        - In this case, the left side and right side MUST connect to mid element
        - so there only a few subarray; We only need to find the max of these subarray
          e.g. mid, x1, x2, x3
          []
          [x1]
          [x1, x2]
          [x1, x2, x3]

    Time:  O(N logN)
      - like merge sort, for each layer, we go through each element, which cost O(N)
      - there are logN layers, so O(N * logN)
    Space: O(logN), the recursion stack is the number of layers, which is O(logN)

     */
    class Solution2_Divide_Conquer {
        public int maxSubArray(int[] nums) {
            int N = nums.length;
            return helper(nums, 0, N - 1);
        }

        private int helper(int[] nums, int left, int right) {
            // Base case - empty array
            if (left > right) return Integer.MIN_VALUE;

            int mid = left + (right - left) / 2;
            int leftMax = helper(nums, left, mid - 1);
            int rightMax = helper(nums, mid + 1, right);
            int bothMax = conquer(nums, left, right, mid);

            return Math.max(bothMax, Math.max(leftMax, rightMax));
        }

        private int conquer(int[] nums, int left, int right, int mid) {
            // conquer left
            int leftSumMax = 0, leftCurMax = 0;
            for (int i = mid - 1; i >= left; i--) {
                leftCurMax += nums[i];
                if (leftCurMax > leftSumMax) leftSumMax = leftCurMax;
            }

            // conquer right
            int rightSumMax = 0, rightCurMax = 0;
            for (int i = mid + 1; i <= right; i++) {
                rightCurMax += nums[i];
                if (rightCurMax > rightSumMax) rightSumMax = rightCurMax;
            }

            return leftSumMax + nums[mid] + rightSumMax;
        }
    }

}
