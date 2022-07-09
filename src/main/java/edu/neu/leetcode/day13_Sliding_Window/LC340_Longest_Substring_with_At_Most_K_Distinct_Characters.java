package edu.neu.leetcode.day13_Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class LC340_Longest_Substring_with_At_Most_K_Distinct_Characters {


    /*
    Thinking:
    - Sliding Window
    - use Map to track the number of distinct char within the slide window
    - Same as LC159_Longest_Substring_with_At_Most_Two_Distinct_Characters.java

    Time:  O(N)
    Space: O(k)
     */
    class Solution1_Sliding_Window {
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            int res = 0, left = 0, N = s.length();
            Map<Character, Integer> map = new HashMap<>();

            for (int r = 0; r < N; r++) {   // r: right pointer
                // add current char
                char c = s.charAt(r);
                map.put(c, map.getOrDefault(c, 0) + 1);

                // shrink left
                while (map.size() > k) {
                    char leftChar = s.charAt(left);
                    map.put(leftChar, map.get(leftChar) - 1);
                    if (map.get(leftChar) == 0) map.remove(leftChar);
                    left++;
                }

                // calculate result
                res = Math.max(res, r - left + 1);
            }
            return res;
        }
    }
}
