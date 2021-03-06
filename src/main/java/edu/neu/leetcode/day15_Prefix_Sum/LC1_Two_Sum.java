package edu.neu.leetcode.day15_Prefix_Sum;

import java.util.Map;
import java.util.HashMap;

public class LC1_Two_Sum {

    class Solution {
        public int[] twoSum(int[] nums, int target) {
            //    nums[i]   i
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int diff = target - nums[i];
                if (map.containsKey(diff)) return new int[]{map.get(diff), i};
                map.put(nums[i], i);
            }
            return null;
        }
    }
}
