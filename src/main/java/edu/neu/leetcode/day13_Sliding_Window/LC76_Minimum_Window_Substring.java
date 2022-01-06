package edu.neu.leetcode.day13_Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class LC76_Minimum_Window_Substring {

    /*
    Thinking:
    - Sliding Window
      - left right pointer
    - Map stores char(Key) and count(Value)

    Goals:
    - min substring
    - s includes t

    Algo:
    int left = 0, minStart = 0, minLen = MAX, count
    map = {}
    for i = 0 to t.length   // init map
        map.put(t(i), +1)
    for r = 0 to s.length   // go through each char
        // add char to window (expand right), update map, count
        c = s(r)
        if map has c:
            if map(c) > 0, then count++
            map(c)--


        // check if it is valid substring
        while count == t.length:
            // if found a shorter substring, update minStart, minLen
            if r - left + 1 < minLen:
                minLen = r - left + 1
                minStart = left

            // shrink left
            leftChar = s(left)
            if map has leftChar:
                map(leftChar)++
                if map(leftChar) > 0, then count--
            left++

    return s.subString(minStart, minLen)


    Example:
    s = "AAAA", t = "AA"
    init map = {A:2}

    char    map     count
    # expand right
    A       {A:1}     1             not valid
    AA      {A:0}     2 = map(A)    valid
    AAA     {A:-1}    2 = map(A)    also valid
    # shrink left
    AA      {A:0}     2             valid
    A       {A:1}     1             not valid


    Notes:
    - if count = t.length, it is a valid substring
    - if count < t.length, it needs more required char
    - if map(A) > 0, it needs more A
    - if map(A) = 0, it does not need more A
    - if map(A) < 0, it has more A than needed, over meet requirement

    Example:
    s = "ABAACBAB", t = "ABC"

    A
    AB      not valid, expand right
    ABAAC   valid, length=5
    BAAC    shrink left to see if there is better answers, valid, length=4, better answer
    AAC     shrink left, not valid, expand right
    AACB    valid, length=4
    ACB     shrink left, valid, length=3, better answer
    CB      shrink left, not valid, expand right
    CBA     valid, length=3

    Time:  O(N)
    Space: O(k), k is the length of t
    */
    class Solution {
        public String minWindow(String s, String t) {
            int left = 0, minStart = 0, minLen = Integer.MAX_VALUE, count = 0;
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {    // init map
                char c = t.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }


            for (int r = 0; r < s.length(); r++) {  // go throuch each char in s
                // add char to window (expand right), update map, count
                char c = s.charAt(r);

                if (map.containsKey(c)) {
                    if (map.get(c) > 0) count++;
                    map.put(c, map.get(c) - 1);
                }

                // check if it is valid substring
                while (count == t.length()) {
                    // if found a shorter substring, update minStart, minLen
                    if (r - left + 1 < minLen) {
                        minLen = r - left + 1;
                        minStart = left;
                    }

                    // shrink left
                    char leftChar = s.charAt(left);
                    if (map.containsKey(leftChar)) {
                        map.put(leftChar, map.get(leftChar) + 1);
                        if (map.get(leftChar) > 0) count--;
                    }

                    left++;
                }
            }

            return minLen == Integer.MAX_VALUE? "" : s.substring(minStart, minStart + minLen);
        }
    }
}
