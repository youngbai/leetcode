package edu.neu.leetcode.day19_Rolling_Hash;

public class LC1392_Longest_Happy_Prefix {

    /*
    Thinking:
    - Rolling Hash
    - 1e9 is simply 10^9
    - 1e-9 is simply 10^-9
    - 1e9 and 1e-9 are double type

    Example:
    123888123
    i       j

    prefix: hash[0,i] = hash[0,i-1]*26 + nums[i]
    suffix: hash[j,N-1] = hash[j-1,N-1] + nums[j]*26^(N-1-j)

    prefix
    123
    i	hash[0,0] = 1*26^0 = 0*26 + 1
     i	hash[0,1] = 1*26^1 + 2*26^0 = hash[0,0]*26 + 2
      i hash[0,2] = 1*26^2 + 2*26^1 + 3*26^0

    Time:  O(N)
    Space: O(1)
     */
    class Solution1_RollingHash {
        public String longestPrefix(String s) {
            int N = s.length(), res = -1;
            long prefix = 0, suffix = 0, power = 1, MOD = 1_000_000_007; // MOD = (long)1e9 also works
            for (int i = 0, j = N - 1; i < N - 1; i++, j--) {
                int first = s.charAt(i) - 'a', last = s.charAt(j) - 'a';
                prefix = (prefix * 26 + first) % MOD;
                suffix = (suffix + last * power) % MOD;
                power = power * 26 % MOD;
                if (prefix == suffix) res = i;
            }
            return s.substring(0, res + 1);
        }
    }

}
