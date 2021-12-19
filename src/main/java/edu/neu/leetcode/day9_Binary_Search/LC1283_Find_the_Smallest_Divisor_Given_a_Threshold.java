package edu.neu.leetcode.day9_Binary_Search;

import java.util.Arrays;

public class LC1283_Find_the_Smallest_Divisor_Given_a_Threshold {

    /*
    Thinking:
    - Binary Search
    - 1 <= divisor <= max
     */
    class Solution {
        public int smallestDivisor(int[] nums, int threshold) {
            int max = Arrays.stream(nums).max().getAsInt();
            return binary(nums, threshold, 1, max);
        }

        private int binary(int[] nums, int threshold, int low, int high) {
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (valid(nums, threshold, mid)) high = mid - 1;
                else low = mid + 1;
            }
            return low;
        }

        private boolean valid(int[] nums, int threshold, int divisor) {
            int sum = 0;
            for (int num : nums) {
                sum += Math.ceil(num * 1.0 / divisor);
                if (sum > threshold) return false;
            }
            return true;
        }
    }
}
