package edu.neu.leetcode.day20_Bit_Manipulation;

public class LC191_Number_of_1_Bits {

    /*
    Thinking:
    - `n &= (n - 1)` can turn the right most 1 to 0

    Time:  O(1), because at most 32 bits
    Space: O(1)
     */
    public class Solution1 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0;
            while (n != 0) {
                n &= (n - 1);   // turn the right most 1 to 0
                count++;
            }
            return count;
        }
    }

    /*
    Thinking:
    - Bit Mask

    Time:  O(1), because at most 32 bits
    Space: O(1)
     */
    public class Solution2_BitMask {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0;
            while (n != 0) {
                if ((n & 1) == 1) count++;
                n = n >>> 1;    // unsigned right shift operation
            }
            return count;
        }
    }

    /*
    Thinking:
    - Bit Mask

    Time:  O(1), because at most 32 bits
    Space: O(1)
     */
    public class Solution3_BitMask {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0, mask = 1;
            for (int i = 0; i < 32; i++) {
                if ((n & mask) != 0) count++;
                mask <<= 1;
            }
            return count;
        }
    }

}
