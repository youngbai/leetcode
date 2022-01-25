package edu.neu.leetcode.day19_Rolling_Hash;

public class LC214_Shortest_Palindrome {

    /*
    Thinking:
    - find the Palindrome firstly using Rolling Hash
    - we find a Palindrome as "aba" which means we only need to append the result of the String to front

    Time:  O(N)
    Space, O(1)
     */
    class Solution1_RollingHash {
        public String shortestPalindrome(String s) {
            long base = 26L, aL = 1L, MOD = (long)1e9 + 7;
            int N = s.length(), pos = -1;
            long hash1 = 0, hash2 = 0;
            for (int i = 0; i < N; i++) {
                int c = s.charAt(i) - 'a';
                hash1 = (hash1 * base + c) % MOD;
                hash2 = (c * aL + hash2) % MOD;
                aL = aL * base;
                if (hash1 == hash2) pos = i;
            }
            return new StringBuilder().append(s.substring(pos + 1)).reverse().append(s).toString();
        }
    }
}
