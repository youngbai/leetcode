package edu.neu.leetcode.day6_Sweep_List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1229_Meeting_Scheduler {

    /*
    Thinking:
    - compute intersection
    - who ends earlier turn to next
     */
    class Solution {
        public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
            // sort
            Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
            Arrays.sort(slots2, (a,b) -> a[0] - b[0]);

            // traverse each slot, check duration, who ends earlier turn to next
            int i = 0, j = 0;
            int n1 = slots1.length, n2 = slots2.length;
            while (i < n1 && j < n2) {
                int intersectStart = Math.max(slots1[i][0], slots2[j][0]);  // intersect start is the latest start
                int intersectEnd = Math.min(slots1[i][1], slots2[j][1]);    // intersect end is the earliest end
                if (intersectEnd - intersectStart >= duration)
                    return Arrays.asList(intersectStart, intersectStart + duration);
                else if (slots1[i][1] < slots2[j][1]) i++;  // if slots1 end earlier, turn to next
                else j++;                                   // if slots2 end earlier, turn to next
            }
            return new ArrayList<>();
        }
    }
}
