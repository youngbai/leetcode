package edu.neu.leetcode.day15_Prefix_Sum;

import java.util.HashMap;
import java.util.Map;

public class LC525_Contiguous_Array {

    /*
    Thinking:
    - turn this problem into a two sum problem by converting 0 to -1

    Time:  O(N)
    Space: O(N), use map to save prefix sum
     */
    class Solution1_PrefixSum_TwoSum {
        public int findMaxLength(int[] nums) {
            // convert 0 to -1
            for (int i = 0; i < nums.length; i++) if (nums[i] == 0) nums[i] = -1;
            // two sum problem
            int res = 0, sum = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1); // map(prefix_sum, index)
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (map.containsKey(sum)) res = Math.max(res, i - map.get(sum));
                else map.put(sum, i);
            }
            return res;
        }
    }
}
