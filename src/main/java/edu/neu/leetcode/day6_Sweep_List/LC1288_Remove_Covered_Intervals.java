package edu.neu.leetcode.day6_Sweep_List;

import java.util.Arrays;


public class LC1288_Remove_Covered_Intervals {

    /*
    Thinking:
    - Sort the array, and note the previous left and right bound.
    - For evert interval v,
      - if v[0] > left && v[1] > right,
        It's a new uncovered interval,
        so we increment ++res.
      - else
        pre cover cur or cur cover pre

     Ref:
     https://leetcode.com/problems/remove-covered-intervals/discuss/451277/JavaC%2B%2BPython-Sort-Solution
     */
    class Solution1 {
        public int removeCoveredIntervals(int[][] intervals) {
            Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
            int cnt = 0, start = -1, end = -1;
            for (int[] i : intervals) {
                if (i[0] > start && i[1] > end) {
                    cnt++;
                    start = i[0];
                }
                end = Math.max(end, i[1]);
            }
            return cnt;
        }
    }

    /*
    Thinking:
    - sort start ascending,
      if start are same, we sort end descending
    - e.g. pre cur
        - pre start MUST <= cur start
        - if pre end < cur end, then NOT included
        - if pre end >= cur end, then pre includes cur, because end is descending when start are same

     Ref:
     https://leetcode.com/problems/remove-covered-intervals/discuss/451277/JavaC%2B%2BPython-Sort-Solution
     */
    class Solution2 {
        public int removeCoveredIntervals(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> a[0] != b[0]? a[0] - b[0] : b[1] - a[1]);
            int cnt = 0, end = 0;
            for (int[] i: intervals) {
                if (end < i[1]) {
                    cnt++;
                    end = i[1];
                }
            }
            return cnt;
        }
    }
}
