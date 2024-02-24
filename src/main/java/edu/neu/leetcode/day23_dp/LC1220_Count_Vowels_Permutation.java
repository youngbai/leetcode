package edu.neu.leetcode.day23_dp;

import java.util.HashMap;
import java.util.Map;

public class LC1220_Count_Vowels_Permutation {

    /*
    Thinking:
    - DP, TopDown

    n=1 aeiou   5
    n=2 ae      10
        ea ei
        ia ie io iu
        oi ou
        ua

    # base case, n=1
    f(?,1)=1

    # transit function
    n>1
    f(c, n)=
     if c is a, f(e, n-1)
     if c is e, f(a, n-1) + f(i, n-1)
     if c is i, f(a, n-1) + f(e, n-1) + f(o, n-1) + f(u, n-1)
     if c is o, f(i, n-1) + f(u, n-1)
     if c is u, f(a, n-1)

    Time: O(N)
    Space: O(N)
    */
    class Solution {

        //  c:n      number
        Map<String, Long> mem = new HashMap<>();
        long MOD = (long)1e9 + 7;

        public int countVowelPermutation(int n) {
            return (int)((count('a', n) + count('e', n) + count('i', n) + count('o', n) + count('u', n)) % MOD);
        }

        private long count(char c, int n) {
            if (n == 1) return 1;

            String key = c + ":"  + n;
            if (mem.containsKey(key)) return mem.get(key);

            long res = 0;
            if (c == 'a') {
                res = count('e', n - 1);
            } else if (c == 'e') {
                res = count('a', (n - 1)) + count('i', n - 1);
            } else if (c == 'i') {
                res = count('a', (n - 1)) + count('e', n - 1) + count('o', (n - 1)) + count('u', n - 1);;
            } else if (c == 'o') {
                res = count('i', (n - 1)) + count('u', n - 1);
            } else {
                res = count('a', (n - 1));
            }

            mem.put(key, res % MOD);
            return res;

        }
    }



}
