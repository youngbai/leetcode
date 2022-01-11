package edu.neu.leetcode.day15_Prefix_Sum;

public class LC370_Range_Addition {

    /*
    Thinking:
    - segment tree, range update: lazy propagation

    Time:  O(M+N)
    Space: O(N)
     */
    class Solution1_LazyPropagation {
        public int[] getModifiedArray(int length, int[][] updates) {
            int[] res = new int[length];

            // lazy propagation
            for (int[] update : updates) {  // O(M)
                int start = update[0], end = update[1], value = update[2];
                res[start] += value;
                if (end < length - 1) res[end + 1] -= value;
            }

            // rolling sum
            // Implementation1:
            // int sum = 0;
            // for (int i = 0; i < length; i ++) {
            //     sum += res[i];
            //     res[i] = sum;
            // }
            // Implementation2:
            for (int i = 1; i < length; i++) {  // O(N)
                res[i] += res[i - 1];
            }
            return res;
        }
    }
}
