package edu.neu.leetcode.day6_Sweep_List;

import java.util.Arrays;

public class LC435_Non_overlapping_Intervals {


    /*
    Thinking:
    - sort by end
    - traverse each interval
        - if NOT overlap, update end
        - if overlap, ignore the current one because current one's end is larger

    Explain:
    https://leetcode.com/problems/non-overlapping-intervals/discuss/91713/Java%3A-Least-is-Most
     */
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length == 0) return 0;
            Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
            int cnt = 0, end = Integer.MIN_VALUE;
            for (int[] cur : intervals) {
                if (end <= cur[0]) end = cur[1];
                else cnt++;
            }
            return cnt;
        }
    }
}
