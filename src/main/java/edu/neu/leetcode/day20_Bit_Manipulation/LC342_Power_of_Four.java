package edu.neu.leetcode.day20_Bit_Manipulation;

public class LC342_Power_of_Four {

    /*
    Thinking:
    - Bit Manipulation

    Algo:
    - Firstly, make sure n is power of 2
    - Secondly, distinguish between even powers of two (when x is a power of four)
      and odd powers of two (when x is not a power of four)
    - What is the difference?
      In the first case (power of four), 1-bit is at even position: bit 0, bit 2, bit 4, etc.
      In the second case, 1-bit is at odd position: bit 1, bit 3, bit 5, ect.
    - so, power of 4 & power of 2 in odd position is 0
    - power of 2 in odd position is 0xaaaaaaaa

    Time:  O(1)
    Space: O(1)
     */
    class Solution1_BitManipulation {
        public boolean isPowerOfFour(int n) {
            return (n > 0) && ((n & (n - 1) )== 0) && ((n & 0xaaaaaaaa) == 0);
        }
    }


    /*
    Thinking:
    - x=4^a
    - a=log4 x = 1/2 * log2 x
    - so, log2 x must be an even number if x is power of 4

    Math:
    - loga b = log10 b / log10 a
    - log2 N = loge N / loge 2
    - int result = (int)(Math.log(N) / Math.log(2)); // result is log2 N

    Time:  O(1)
    Space: O(1)
     */
    class Solution2_Math {
        public boolean isPowerOfFour(int n) {
            return (n > 0) && (Math.log(n) / Math.log(2) % 2 == 0);
        }
    }


    /*
    Thinking:
    - x=4^a
    - a=log4(x), a must be an integer
    - How to check a double number equals an integer?
      d % 1 == 0, if true, then d is an integer

    Time:  O(1)
    Space: O(1)
     */
    class Solution3_Math {
        public boolean isPowerOfFour(int n) {
            return Math.log(n) / Math.log(4) % 1 == 0;
        }
    }
}
