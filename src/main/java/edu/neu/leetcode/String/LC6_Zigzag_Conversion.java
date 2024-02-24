package edu.neu.leetcode.String;

import java.util.Map;
import java.util.TreeMap;

public class LC6_Zigzag_Conversion {

    /*
    Thinking:
    - sort by row
    - TreeMap

    Time: O(N)
    Space: O(N)
     */
    class Solution1 {
        public String convert(String s, int numRows) {
            Map<Integer, StringBuilder> map = new TreeMap<>();
            boolean down = true;
            int line = 1;
            for (char c : s.toCharArray()) {
                map.computeIfAbsent(line, x -> new StringBuilder()).append(c);

                // direction
                if (line == 1) {
                    down = true;
                } else if (line == numRows) {
                    down = false;
                }

                // next line
                if (down) line++;
                else line--;
            }

            // res based on map
            StringBuilder sb = new StringBuilder();
            for (int row : map.keySet()) {
                sb.append(map.get(row));
            }

            return sb.toString();
        }
    }
}
