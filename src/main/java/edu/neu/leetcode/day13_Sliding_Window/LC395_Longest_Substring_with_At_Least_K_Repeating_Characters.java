package edu.neu.leetcode.day13_Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class LC395_Longest_Substring_with_At_Least_K_Repeating_Characters {

    /*
    Goals
    - longest substring
    - frequency of each char >= k
      - Map<char, frequency>

    Example:
    s = "cababbc", k = 2

    unique=2
    map.keySize=2
    validCount=2

    char	map			 validCount	left,right	res
    c		{c:1}			  0		  0	  0     X
    a       {c:1,a:1}    	  0		  0	  1     X
    b       {a:1,b:1}	  	  0		  1	  2     X
    a       {a:2,b:1}		  1		  1	  3     X
    b       {a:2,b:2}		  2		  1	  4     C=4
    b       {a:2,b:3}		  2		  1	  5     C=5
    c       {b:2,c:1}	  	  1		  4	  6     X

    Algo:
    res=0
    for unique = 1 to 26:	// unique is window size
        left=0, validCount=0, map={}
        for r=0 to s.length:
            // enter window
            c = s(r)
            map.put(c, +1)
            if map(c) == k, then validCount++

            // shrink left
            while map.keySize > unique:	// larger than window size,shrink
                leftChar = s(left)
                if map(leftChar) == k, then validCount--
                map(leftChar)--, if value ends up 0, then remove leftChar
                left++

            if (unique == map.keySize == validCount)
                then res = max(res, r - left + 1)
        return res

    Time:  O(26*N)
    Space: O(N)
    */
    class Solution1_Sliding_Window {
        public int longestSubstring(String s, int k) {
            int res = 0;
            for (int unique = 1; unique <= 26; unique++) {
                int left = 0, validCount = 0;
                Map<Character, Integer> map = new HashMap<>();
                for (int r = 0; r < s.length(); r++) {
                    // enter window
                    char c = s.charAt(r);
                    map.put(c, map.getOrDefault(c, 0) + 1);
                    if (map.get(c) == k) validCount++;  // find a valid char, validCount++

                    // shrink left
                    while (map.keySet().size() > unique) { // larger than window size, shrink
                        char leftChar = s.charAt(left);
                        if (map.get(leftChar) == k) validCount--;
                        map.put(leftChar, map.get(leftChar) - 1);
                        if (map.get(leftChar) == 0) map.remove(leftChar);
                        left++;
                    }

                    // window has unique valid char
                    if (unique == map.keySet().size() && unique == validCount)
                        res = Math.max(res, r - left + 1);
                }
            }
            return res;
        }
    }
}
