package edu.neu.leetcode.day9_Binary_Search;

import java.util.Arrays;

public class LC410_Split_Array_Largest_Sum {

    /*
    Thinking:
    - Binary Search
    - the answer is between `max element of nums` and `sum of nums`
      - subarray at least has one element, so answer should >= `max element of nums`
      - subarray may have most elements, so answer should < `sum of nums`
    - then binary search between low (`max element of nums`) and high (`sum of nums`)
    - use valid() to check if we can split array into m subarray
      - if we can, it means the answer should be on the left side
      - if we can not, it means the answer should be on the right side

    Time:  O(log(sum-max)*n)
    - log(sum-max): binary search between max and sum
    - n : valid() traverse all elements
    Space: O(1)

    Ref:
    https://leetcode.com/problems/split-array-largest-sum/discuss/89817/Clear-Explanation%3A-8ms-Binary-Search-Java/1049987/
     */
    class Solution1 {
        public int splitArray(int[] nums, int m) {
            int sum = Arrays.stream(nums).sum();
            int max = Arrays.stream(nums).max().getAsInt();
            return binary(nums, m, max, sum);
        }

        private int binary(int[] nums, int m, int low, int high) {
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (valid(nums, m, mid)) high = mid - 1;
                else low = mid + 1;
            }
            return low;
        }

        private boolean valid(int[] nums, int m, int subArraySum) {
            int curSum = 0, count = 1;
            for (int num : nums) {
                curSum += num;
                if (curSum > subArraySum) { // it means we need a new subarray
                    curSum = num;   // put num into new subarray
                    count++;        // and add count
                    if (count > m) return false; // if count > m, means we need more than m subarray, so return false
                }
            }
            return true;
        }
    }

    /*
    Thinking:
    - DFS
    - first position, cut or don't cut, then second position, cut or don't cut
     */
}
