package edu.neu.leetcode.Array;

public class LC1497_Check_If_Array_Pairs_Are_Divisible_by_k {

    /*
    Thinking:

    Time: O(N)
    Space: O(K)
     */
    class Solution1 {
        public boolean canArrange(int[] arr, int k) {
            int[] freq = new int[k];
            for (int num : arr) {
                num = num % k;
                if (num < 0) num += k;
                freq[num]++;
            }

            if (freq[0] % 2 != 0) return false;

            for (int i = 1; i <= k / 2; i++) {
                if (freq[i] != freq[k - i]) return false;
            }
            return true;
        }
    }
}
