package edu.neu.leetcode.day15_Prefix_Sum;

import java.util.HashMap;
import java.util.Map;

public class LC974_Subarray_Sums_Divisible_by_K {

    /*
    Thinking:
    - prefix sum
    - negative num: (prefix remainder + num % k + k) % k to guarantee the remainder is non-negative

    Time:  O(N)
    Space: O(N), use map to save (remainder -> frequency)
     */
    class Solution1_PrefixSum {
        public int subarraysDivByK(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>(); // map(remainder, frequency)
            map.put(0, 1);
            int prefix = 0, res = 0;
            for (int n : nums) {    // O(N)
                prefix = (prefix + n % k + k) % k;  // guarantee the remainder is non-negative
                res += map.getOrDefault(prefix, 0);
                map.put(prefix, map.getOrDefault(prefix, 0) + 1);
            }
            return res;
        }
    }
}
