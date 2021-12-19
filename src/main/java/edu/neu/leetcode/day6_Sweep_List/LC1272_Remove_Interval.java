package edu.neu.leetcode.day6_Sweep_List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1272_Remove_Interval {

    /*
    Thinking:
    - list every different situation
     */
    class Solution1 {
        public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
            List<List<Integer>> res = new ArrayList<>();
            for (int[] i: intervals) {
                // NOT overlapped
                if (i[1] <= toBeRemoved[0] || toBeRemoved[1] <= i[0])
                    res.add(Arrays.asList(i[0], i[1]));
                else if (i[0] < toBeRemoved[0]) {            // i, TBR
                    res.add(Arrays.asList(i[0], toBeRemoved[0]));       // insert 1
                    if (toBeRemoved[1] < i[1])
                        res.add(Arrays.asList(toBeRemoved[1], i[1])); // insert 2
                } else if (toBeRemoved[1] < i[1]) {          // TBR, i
                    res.add(Arrays.asList(toBeRemoved[1], i[1]));
                }
            }
            return res;
        }
    }


    /*
    Thinking:
    - same as Solution1, but simpler than Solution1

    Algo:
        - Not overlapped
        - overlapped
            - if left subtraction exist, keep it
            - if right subtraction exist, keep it
     */
    class Solution2 {
        public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
            List<List<Integer>> res = new ArrayList<>();
            for (int[] i: intervals) {
                if (i[1] <= toBeRemoved[0] || toBeRemoved[1] <= i[0]) // NOT overlapped
                    res.add(Arrays.asList(i[0], i[1]));
                else {                                                // overlapped
                    if (i[0] < toBeRemoved[0])      // insert left substraction
                        res.add(Arrays.asList(i[0], toBeRemoved[0]));
                    if (toBeRemoved[1] < i[1])      // insert right substraction
                        res.add(Arrays.asList(toBeRemoved[1], i[1]));
                }
            }
            return res;
        }
    }
}
