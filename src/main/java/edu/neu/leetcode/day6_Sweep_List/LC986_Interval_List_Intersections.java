package edu.neu.leetcode.day6_Sweep_List;

import java.util.ArrayList;
import java.util.List;

public class LC986_Interval_List_Intersections {

    /*
    Thinking:
    - compute intersection
    - who ends earlier turn to next
     */
    class Solution {
        public int[][] intervalIntersection(int[][] A, int[][] B) {
            List<int[]> res = new ArrayList<>();
            int i = 0, j = 0;
            while (i < A.length && j < B.length) {
                int low = Math.max(A[i][0], B[j][0]);
                int high = Math.min(A[i][1], B[j][1]);
                // overlap
                if (low <= high) res.add(new int[]{low, high});
                // move to next
                if (A[i][1] < B[j][1]) i++;
                else j++;
            }
            return res.toArray(new int[0][]);
        }
    }
}
