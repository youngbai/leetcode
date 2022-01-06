package edu.neu.leetcode.day13_Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class LC159_Longest_Substring_with_At_Most_Two_Distinct_Characters {

    /*
    Thinking:
    - Sliding Window
    - use Map to track the number of distinct char within the slide window

    Goals:
    - longest substring
    - <= 2 distinct char

    Example:
    eceba
    [ece]ba

    How to get num of distinct char in [e,c,e]
    map {e:2,c:1}

    char map        res            left, right
    e   {e:1}       res=1           0       0
    ec  {e:1,c:1}   res=2           0       1
    ece {e:2,c:1}   res=3   Answer  0       2
    eb  {e:1,b:1}   res=2           2       3
    bc  {b:1,c:1}   res=2           3       4
    ca  {c:1,a:1}   res=2           4       5

    Time:  O(N)
    Space: O(2)
    */
    class Solution1_Sliding_Window {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int res = 0, left = 0, N = s.length();
            Map<Character, Integer> map = new HashMap<>();

            for (int r = 0; r < N; r++) {   // r: right pointer
                // add current char
                char c = s.charAt(r);
                map.put(c, map.getOrDefault(c, 0) + 1);

                // shrink left
                while (map.size() > 2) {
                    char leftChar = s.charAt(left);
                    map.put(leftChar, map.get(leftChar) - 1);
                    if (map.get(leftChar) == 0) map.remove(leftChar);
                    left++;
                }
                // calculate answer
                res = Math.max(res, r - left + 1);
            }
            return res;
        }
    }

}
