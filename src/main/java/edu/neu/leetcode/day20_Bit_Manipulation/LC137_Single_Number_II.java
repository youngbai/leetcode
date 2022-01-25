package edu.neu.leetcode.day20_Bit_Manipulation;

public class LC137_Single_Number_II {

    /*
    Thinking:
    - Bit Manipulation
    - [1,0,6,3......1,0,1,0]    int[32]
    - 6 or 3 % 3 is 0, means it appear three* times, and should be removed
    - only 1 left

    Time:  O(N)
    Space: O(1)
     */
    class Solution1_BitManipulation {
        public int singleNumber(int[] nums) {
            int res = 0;
            int[] map = new int[32];
            for (int i = 0; i < nums.length; i++) saveToMap(nums[i], map);
            for (int i = 0; i < 32; i++) {
                if (map[i] % 3 == 0) map[i] = 0;
                else res |= (1 << i);
            }
            return res;
        }

        private void saveToMap(int num, int[] map) {
            for (int i = 0; i < 32; i++)
                map[i] += (num >> i) & 1;
        }
    }
}
