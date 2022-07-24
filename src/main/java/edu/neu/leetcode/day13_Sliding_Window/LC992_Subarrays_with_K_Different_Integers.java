package edu.neu.leetcode.day13_Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class LC992_Subarrays_with_K_Different_Integers {

    /*
    Goals
    - good subarray
    - diff int = k

    Formula
    exactly(K) = atMost(K) - atMost(K-1)

    Example
    nums = 1 2 1 2 3, k = 2

    nums[i]   res
    1         -1
    12        -2
    121       -3
    1212      -4
    12123
     2123
      123
       23     -2
    atMost(2)=12

    1        -1
    12
     2       -1
     21
      1      -1
      12
       2     -1
       23
        3    -1
    atMost(2)=5

    exactly(K) = atMost(K) - atMost(K-1)
    exactly(2) = atMost(2) - atMost(1) = 12 - 5 = 7


    Ref:
    https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/523136/JavaC%2B%2BPython-Sliding-Window
    */
    class Solution1_Sliding_Window {
        public int subarraysWithKDistinct(int[] nums, int k) {
            return atMost(nums, k) - atMost(nums, k - 1);
        }

        private int atMost(int[] nums, int k) {
            int left = 0, res = 0;
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                // enter window
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

                // shrink left
                while (map.size() > k) {
                    map.put(nums[left], map.get(nums[left]) - 1);
                    if (map.get(nums[left]) == 0) map.remove(nums[left]);
                    left++;
                }

                // calculate result
                res += i - left + 1;
            }
            return res;
        }
    }
}
