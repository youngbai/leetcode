package edu.neu.leetcode.day20_Bit_Manipulation;

public class LC260_Single_Number_III {

    /*
    Thinking:
    - Bit Manipulation
    - find the last 1 digit of ans1^ans2
    - use the last 1 digit to separate the numbers into two groups
      - ans1,ans2 will not in the same group
      - for other numbers, the same number will be in the same group, eg. 2 2 will be in group1
      - so xor every number in each group, which will filter out the same number end leave ans1 or ans2

    - Find the last 1 digit
      - x & (-x)  where -x is the complement code of x
      - x & (~x + 1) where ~x is the inverse code; Adding 1 will convert the inverse code to complement code
      - so `-x` is the same as `~x + 1`

    - Original code, Inverse code, Complement code
      - original code:    x
      - inverse code:     ~x
      - complement code:  -x or (~x + 1)

    Time:  O(N)
    Space: O(1)
     */
    class Solution1_BitManipulation {
        public int[] singleNumber(int[] nums) {
            // find the ans1^ans2
            int xor = 0;
            for (int n : nums) xor ^= n;
            // find the last 1 digit of ans1^ans2
            int lastDigit = xor & (-xor); // use complement code
            // int lastDigit = xor & (~xor + 1); // also works with inverse code and adding 1
            int group1 = 0, group2 = 0;
            for (int n : nums) {
                if ((lastDigit & n) == 0) group1 ^= n;
                else group2 ^= n;
            }
            return new int[]{group1, group2};
        }
    }
}
