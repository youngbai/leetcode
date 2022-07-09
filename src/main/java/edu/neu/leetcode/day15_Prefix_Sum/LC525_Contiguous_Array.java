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
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1); // map(prefix_sum, index)

            int res = 0, sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (map.containsKey(sum)) res = Math.max(res, i - map.get(sum));
                else map.put(sum, i);
            }
            return res;
        }
    }


    /*
    Thinking:
    - same idea with Solution1
    - but, we only need to maintain the sum and treat 1 as 1, 0 as -1.

    e.g
      1  0 1  0 1   original array
      1 -1 1 -1 1   treat 1 as 1, 0 as -1
    0 1  0 1  0 1   prefix sum

    */
    class Solution2_PrefixSum {
        public int findMaxLength(int[] nums) {
            //    sum     index of first 1 or 0
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);

            int sum = 0, max = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) sum += 1;
                else sum -= 1;
                if (map.containsKey(sum)) max = Math.max(max, i - map.get(sum));
                else map.put(sum, i);
            }
            return max;
        }
    }


}
