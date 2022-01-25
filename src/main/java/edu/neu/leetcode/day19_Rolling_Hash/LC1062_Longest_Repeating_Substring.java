package edu.neu.leetcode.day19_Rolling_Hash;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class LC1062_Longest_Repeating_Substring {

    /*
    Thinking:
    - Binary Search
    - Rabin Karp Rolling hash (String polynomial hashing)

     */
    class Solution1_BinarySearch_RollingHash {
        long MOD = 1_000_000_007;
        int a = 26;
        int N;
        public int longestRepeatingSubstring(String s) {
            N = s.length();
            int[] nums = new int[N];
            for (int i = 0; i < N; i++) nums[i] = (int)s.charAt(i) - (int)'a';
            int low = 0, high = N - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int start = search(s, mid, nums);
                if (start == -1) high = mid - 1;
                else low = mid + 1;
            }
            int start = search(s, high, nums);
            return start == -1? 0 : high;
        }

        private int search(String s, int L, int[] nums) {
            // calculate hash value of the first substring with length of L
            long h = 0;
            long aL = 1;
            for (int i = 0; i < L; i++) {
                h = (h * a + nums[i]) % MOD;
                aL = aL * a % MOD;
            }

            // calculate hash value of the following substring
            Map<Long, List<Integer>> visited = new HashMap<>();
            visited.computeIfAbsent(h, k -> new ArrayList<>()).add(0);
            for (int start = 1; start < N - L + 1; start++) {
                h = h * a;
                h = (h - nums[start - 1] * aL % MOD + MOD) % MOD;
                h = (h + nums[start + L - 1]) % MOD;
                if (visited.containsKey(h)) {
                    for (int i : visited.get(h)) {
                        String cur = s.substring(start, start + L);
                        if (s.substring(i, i + L).equals(cur))
                            return i;
                    }
                }
                visited.computeIfAbsent(h, k -> new ArrayList<>()).add(start);
            }
            return -1;
        }
    }
}
