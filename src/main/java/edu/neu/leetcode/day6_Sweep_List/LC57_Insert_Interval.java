package edu.neu.leetcode.day6_Sweep_List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC57_Insert_Interval {

    /*
    Thinking:
    Algo:
        - if cur, newInterval
            insert(cur)
        - if newInterval, cur
            insert (newInterval, cur)
            newInterval=null
        - if overlap
            merge

    What is the difference between List.of and Arrays.asList?
    https://stackoverflow.com/questions/46579074/what-is-the-difference-between-list-of-and-arrays-aslist
     */
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> res = new ArrayList<>();
            for (int[] cur : intervals) {
                if (newInterval == null|| cur[1] < newInterval[0]) res.add(cur);    // cur, newInter
                else if (newInterval[1] < cur[0]) {    // newInter, cur
                    //res.addAll(List.of(newInterval, cur));        // JDK9
                    res.addAll(Arrays.asList(newInterval, cur));    // JDK8
                    newInterval = null;
                } else {    // overlap, merge
                    newInterval[0] = Math.min(newInterval[0], cur[0]);
                    newInterval[1] = Math.max(newInterval[1], cur[1]);
                }
            }
            if (newInterval != null) res.add(newInterval);
            return res.toArray(new int[0][]);
        }
    }
}
