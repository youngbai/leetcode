package edu.neu.leetcode.day13_Sliding_Window;

import java.util.Arrays;

public class LC424_Longest_Repeating_Character_Replacement {

    /*
    Thinking:
    - Sliding Window

    Goals
    - longest repeating char
    - <= k replacement

    s="ABABBA", k=2

    char    count       winlen-maxcount     res
    A       [A:1]            1-1=1<k        1
    AB      [A:1,B:1]        2-1=1<k        2
    ABA     [A:1,B:2]        3-2=1<k        3
    ABAB    [A:2,B:2]        4-2=2=k        4
    ABABB   [A:2,B:3]        5-3=2=k        5
    ABABBA  [A:3,B:3]        6-3=3>k        5       need shrink left
     BABBA  [A:2,B:3]        5-3=2=k        5       after shrink left

    Time:  O(26N)
    Space: O(26)
    */
    class Solution1_Sliding_Window {
        public int characterReplacement(String s, int k) {
            int left = 0, res = 0;
            int[] count = new int[26];
            for (int r = 0; r < s.length(); r++) {
                // enter window
                count[s.charAt(r) - 'A']++;

                // while check if need shrink left
                while (r - left + 1 - findMax(count) > k) {
                    count[s.charAt(left) - 'A']--;
                    left++;
                }
                // update result
                res = Math.max(res, r - left + 1);
            }
            return res;
        }

        private int findMax(int[] count) {
            return Arrays.stream(count).max().getAsInt();
        }
    }
}
