package edu.neu.leetcode.day20_Bit_Manipulation;

public class LC268_Missing_Number {

    /*
    Thinking:
    - XOR
    - x xor x = 0
    - 0 xor x = x, so for loop starts from 1
    - two loop, see Solution2 using 1 loop

    Time:  O(N)
    Space: O(1)
     */
    class Solution1_2Loop_XOR {
        public int missingNumber(int[] nums) {
            int miss = 0;
            for (int n : nums) miss ^= n;
            for (int i = 1; i <= nums.length; i++) miss ^= i;   // start from 1
            return miss;
        }
    }



    /*
    Thinking
    - XOR
    - 1 loop

    Time:  O(N)
    Space: O(1)
     */
    class Solution2_1Loop_XOR {
        public int missingNumber(int[] nums) {
            int miss = nums.length;
            for (int i = 0; i < nums.length; i++)
                miss ^= i ^ nums[i];
            return miss;
        }
    }



}
