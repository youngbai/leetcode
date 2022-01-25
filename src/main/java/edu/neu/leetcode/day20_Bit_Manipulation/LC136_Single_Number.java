package edu.neu.leetcode.day20_Bit_Manipulation;

public class LC136_Single_Number {

    /*
    Thinking:
    - Bit Manipulation
    - a^a=0
    - 0^a=a
    - a^b^a=(a^a)^b=0^b=b

    Time:  O(N)
    Space: O(1)
     */
    class Solution1_BitManipulation {
        public int singleNumber(int[] nums) {
            // a^a=0, 0^a=a, a^b^a=(a^a)^b=0^b=b
            int res = 0;
            for (int n : nums) res ^= n;
            return res;
        }
    }
}
