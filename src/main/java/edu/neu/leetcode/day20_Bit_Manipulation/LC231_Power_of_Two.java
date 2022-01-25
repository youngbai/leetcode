package edu.neu.leetcode.day20_Bit_Manipulation;

public class LC231_Power_of_Two {

    /*
    Thinking:
    - `x & (x - 1)` remove the right most 1 digit

    Time:  O(1)
    Space: O(1)
     */
    class Solution1_BitManipulation {
        public boolean isPowerOfTwo(int n) {
            return (n > 0) && (n & (n - 1)) == 0;
        }
    }
}
