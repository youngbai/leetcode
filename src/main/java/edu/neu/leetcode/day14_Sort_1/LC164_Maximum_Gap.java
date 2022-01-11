package edu.neu.leetcode.day14_Sort_1;

import java.util.Arrays;

public class LC164_Maximum_Gap {

    /*
    Thinking:
    - Radix Sort

    Time:  O(d(N+k))
    Space: O(N), int[] out
     */
    class Solution1_Radix_Sort {

        public int maximumGap(int[] nums) {
            if (nums == null || nums.length < 2) return 0;

            // radix sort
            int m = Arrays.stream(nums).max().getAsInt();
            for (int exp = 1; m / exp > 0; exp *= 10) countSort(nums, exp);

            // calculate max gap
            int maxGap = 0;
            for (int i = 1; i < nums.length; i++)
                if (nums[i] - nums[i - 1] > maxGap) maxGap = nums[i] - nums[i - 1];
            return maxGap;
        }

        private void countSort(int[] nums, int exp) {
            int[] out = new int[nums.length];
            int[] count = new int[10];
            for (int n: nums) count[(n / exp) % 10]++;  // do counting
            for (int i = 1; i < count.length; i++) count[i] += count[i - 1];  // do prefix sum
            for (int i = nums.length - 1; i >= 0; i--) out[--count[(nums[i] / exp) % 10]] = nums[i]; // do counting sort
            for (int i = 0; i < nums.length; i++) nums[i] = out[i];
        }
    }
}
