package edu.neu.leetcode.day15_Prefix_Sum;

import java.util.HashMap;
import java.util.Map;

public class LC974_Subarray_Sums_Divisible_by_K {

    /*
    Thinking:
    - prefix sum remainder
      - try to find two sum whose remainder are the same
    - negative num: (prefix remainder + num % k + k) % k to guarantee the remainder is non-negative
    - e.g.
        k=5
          4,5,0,-2,-3,1   nums
        0 4 4 4  2  4 0   prefix remainder

        remainder%5=x   count
        4  		 %5=4     0
        4  		 %5=4     1   [5]
        4  		 %5=4     2   [5,0] [0]
        2  		 %5=2     0
        4  		 %5=4     3   [5,0,-2,-3] [0,-2,-3] [-2,-3]
        0  		 %5=0     1   [4,5,0,-2,-3,1]
        total:      7

    Time:  O(N)
    Space: O(N), use map to save (remainder -> frequency)
     */
    class Solution1_PrefixSum {
        public int subarraysDivByK(int[] nums, int k) {
            // prefix sum remainder
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
