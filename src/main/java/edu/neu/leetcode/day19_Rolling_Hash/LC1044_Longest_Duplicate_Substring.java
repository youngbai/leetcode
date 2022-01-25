package edu.neu.leetcode.day19_Rolling_Hash;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class LC1044_Longest_Duplicate_Substring {

    /*
    Thinking:
    - Binary Search
    - Rabin Karp Rolling hash (String polynomial hashing)

    Q. When Rabin Karp rolling hash (String polynomial hashing) is used?
    ans. When the string comparison cost is expensive/high.

    Q. why did we take prime no as 31?
    ans. Always we have to take larger value than the character set
    here-- set of small alphabet letters i.e 'a' - 'z' is 26 so the nearest prime no is 29 and 31 but we take 31 for safer side.

    Q. Why do we always take prime number greater than character set?
    ans. lets take an example where prime no 'P' is 5
    then for letter "f" -- (f-'a' + 1) * 5^0 = 6 * 5^0 = 6
    and for letter "aa" -- ('a' - 'a' + 1)*5^0 + ('a' - 'a' + 1)*5^1 = 1+5 = 6
    Here for two different strings we get same hash value i.e collision
    So, that's why we always take P > character set

    Q. Why we take mod ?
    ans. So that integer overflow doesn't occur.

    Q. Why we use binary search ?
    ans. time complexity will be O(N log N) If we use binary search and if don't use it then the time complexity will be O(N2).

     */
    class Solution1_BinarySearch_RollingHash {
        //long MOD = (long)Math.pow(2, 32);// Note: Math.pow return double. BUT, 2^32 cause TLE
        long MOD = 1_000_000_007;          // But 1_000_000_007 works
        int a = 26;
        int N;
        public String longestDupSubstring(String s) {
            N = s.length();
            int[] nums = new int[N];
            for (int i = 0; i < N; i++) nums[i] = (int)s.charAt(i) - (int)'a';
            int low = 1, high = N - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int start = search(s, mid, nums);
                if (start == -1) high = mid - 1;
                else low = mid + 1;
            }

            int start = search(s, high, nums);
            return start == -1? "" : s.substring(start, start + high);
        }

        private int search(String s, int L, int[] nums) {
            // calculate hash value of the first substring with length L
            long h = 0; // hash value
            long aL = 1; // aL is used to remove the tail
            for (int i = 0; i < L; i++) {
                h = (h * a + nums[i]) % MOD;
                aL = (aL * a) % MOD;
            }
            // calculate hash value of the following substring
            //Set<Long> seen = new HashSet<>(List.of(h));
            Map<Long, List<Integer>> visited = new HashMap<>();
            visited.computeIfAbsent(h, k -> new ArrayList<>()).add(0);
            for (int start = 1; start < N - L + 1; start++) {
                h = h * a;
                h = (h - nums[start - 1] * aL % MOD + MOD) % MOD;
                h = (h + nums[start + L - 1]) % MOD;
                //if (!seen.add(h)) return start;
                if (visited.containsKey(h))
                    for (int i : visited.get(h))    //  check hash collision
                        if (s.substring(i, i + L).equals(s.substring(start, start + L)))
                            return i;
                visited.computeIfAbsent(h, k -> new ArrayList<>()).add(start);
            }
            return -1;
        }
    }
}
