package edu.neu.leetcode.day20_Bit_Manipulation;

public class LC371_Sum_of_Two_Integers {

    /*
    Thinking:
    - Bit Manipulation
    - This problem is reduced down to find the sum of the answer without carry and the carry.

    Time:  O(1), because each integer contains 32 bits.
    Space: O(1), because we don't use any additional data structures.
     */
    class Solution1_Iteration {
        public int getSum(int a, int b) {
            while (b != 0) {        // loop until carry becomes 0
                int carry = (a & b) << 1;   // carry
                int sum = a ^ b;            // sum without carry
                a = sum;
                b = carry;
            }
            return a;
        }
    }


    class Solution2_Recursion {
        public int getSum(int a, int b) {
            return b == 0? a : getSum(a ^ b, (a & b) << 1);
        }
    }


}
