package edu.neu.leetcode.day6_Sweep_List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC56_Merge_Intervals {

    /*
    Thinking:

    Algo:
        - sort intervals by start
        - if next.start <= cur.end:  # overlapped
            merge(cur, next)
          else                       # NOT overlapped
            insert cur to result list

    Corner case:
        - [1, 4] [2, 3], so max(cur[1], next[1])

    Time:  O(n)
    Space: O(n)
     */
    class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length == 0) return new int[0][];

            // sort
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

            // traverse and see if overlapped
            List<int[]> list = new ArrayList<>();           // Space: O(n)
            int[] cur = intervals[0];
            for (int i = 1; i < intervals.length; i++) {    // Time: O(n)
                if (intervals[i][0] <= cur[1]) {        // overlapped
                    cur[1] = Math.max(cur[1], intervals[i][1]);
                } else {                                // NOT overlapped
                    list.add(cur);
                    cur = intervals[i];
                }
            }
            list.add(cur);
            return list.toArray(new int[0][]);
        }
    }

    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 1});
        list.add(new int[]{2, 2});

        int[][] arr = list.toArray(new int[0][0]);
        for (int[] e : arr) {
            System.out.println(Arrays.toString(e));
        }
    }
}
