package edu.neu.leetcode.day13_Sliding_Window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC3_Longest_Substring_Without_Repeating_Characters {

    /*
    Thinking:
    - Sliding Window
      - left, right side of the window
    - Set store the char

    Time:  O(N), go through each char only once
    Space: O(N), store the substring
     */
    class Solution1_Sliding_Window_Set {
        public int lengthOfLongestSubstring(String s) {
            int l = 0, res = 0, N = s.length();
            Set<Character> set = new HashSet<>();

            for (int r = 0; r < N; r++) {
                char c = s.charAt(r);
                while (!set.add(c)) {           // enter window
                    set.remove(s.charAt(l++));  // exit window
                }
                res = Math.max(res, r - l + 1);
            }
            return res;
        }
    }

    /*
    Thinking:
    - Sliding Window
      - left, right side of the window
    - Map store last valid char(Key) and position(Value)

    Time:  O(N), go through each char at most once, so it is better than Solution1_Set
    Space: O(N), store the substring
     */
    class Solution2_Sliding_Window_Map {
        public int lengthOfLongestSubstring(String s) {
            int l = 0, N = s.length(), res = 0;
            //    char       index
            Map<Character, Integer> map = new HashMap<>();
            for (int r = 0; r < N; r++) {
                char c = s.charAt(r);
                if (map.containsKey(c)) {
                    l = Math.max(map.get(c) + 1, l);    // exit window (shrink left)
                }
                map.put(c, r);                          // enter window
                res =  Math.max(res, r - l + 1);
            }
            return res;
        }
    }
}
