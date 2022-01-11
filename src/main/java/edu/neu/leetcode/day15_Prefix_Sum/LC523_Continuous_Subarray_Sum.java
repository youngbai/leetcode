package edu.neu.leetcode.day15_Prefix_Sum;

import java.util.HashMap;
import java.util.Map;

public class LC523_Continuous_Subarray_Sum {

    /*
    Thinking:
    - prefix remainder

    Time:  O(N)
    Space: O(N), use map to save the prefix remainder (for same prefix remainder, only save the first)
     */
    class Solution1_PrefixSum {
        public boolean checkSubarraySum(int[] nums, int k) {
            int prefix = 0;  // prefix remainder
            Map<Integer, Integer> map = new HashMap<>();  // map(prefix_remainder, index)
            map.put(0, -1);  // init prefix remainder
            for (int i = 0; i < nums.length; i++) {
                prefix = (prefix + nums[i] % k) % k; // make sure k is > 0
                if (map.containsKey(prefix)){
                    if (i - map.get(prefix) > 1) return true;
                } else map.put(prefix, i);
            }
            return false;
        }
    }
}
