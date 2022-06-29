package edu.neu.leetcode.day15_Prefix_Sum;

import java.util.HashMap;
import java.util.Map;

public class LC560_Subarray_Sum_Equals_K {

    /*
    Thinking: (Time Limit Exceeded)
    - only use prefix sum

    Time:  O(N)
    Space: O(N), use array to store prefix sum
     */
    class Solution1_PrefixSum {
        public int subarraySum(int[] nums, int k) {
            int N = nums.length, res = 0;

            // prefix sum
            int[] ps = new int[N + 1];
            for (int i = 1; i <= N; i++) ps[i] = ps[i - 1] + nums[i - 1];

            // brute force two sum
            for (int s = 0; s <= N; s++) {          // O(N)
                for (int e = s + 1; e <= N; e++) {  // O(N)
                    if (ps[e] - ps[s] == k) res++;
                }
            }
            return res;
        }
    }


    /*
    Thinking: (Best Solution)
    - use map instead of array to store prefix sum
    - like Two Sum,
      turn the problem of `preSum - preSum = target`
      to the problem of finding if there exist a preSum that equals to `preSum - target`

    Time:  O(N)
    Space: O(N), use map to store prefix sum
     */
    class Solution2_PrefixSum_TwoSum {
        public int subarraySum(int[] nums, int k) {
            // prefix sum using map
            Map<Integer, Integer> map = new HashMap<>();  // map(prefix_sum, frequency)
            map.put(0, 1);

            int sum = 0, res = 0;   // sum is prefix sum
            for (int n : nums) {    // O(N)
                sum += n;
                if (map.containsKey(sum - k)) res += map.get(sum - k);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return res;
        }
    }
}
