package edu.neu.leetcode.day19_Rolling_Hash;

public class LC1147_Longest_Chunked_Palindrome_Decomposition {

    /*
    Thinking:
    - Rolling Hash

    Time:  O(N)
    Space: O(1)
     */
    class Solution1_RollingHash {
        long MOD = (long)1e9 + 7, a = 26;
        public int longestDecomposition(String text) {
            int N = text.length(), p1 = 0, p2 = N - 1, res = 0;
            long h1 = 0, h2 = 0, aL = 1;
            while (p1 <= p2) {
                int left = text.charAt(p1) - 'a' + 1;
                int right = text.charAt(p2) - 'a' + 1;
                h1 = (h1 * a + left) % MOD;
                h2 = (right * aL + h2) % MOD;
                aL = aL * a % MOD;
                if (h1 == h2) {
                    res += p1 == p2? 1 : 2;
                    h1 = 0; h2 = 0; aL = 1;
                }
                p1++; p2--;
            }
            return h1 > 0 ? res + 1 : res;
        }
    }
}
