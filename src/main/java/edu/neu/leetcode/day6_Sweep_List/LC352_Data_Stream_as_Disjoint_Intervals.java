package edu.neu.leetcode.day6_Sweep_List;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LC352_Data_Stream_as_Disjoint_Intervals {

    /*
    Thinking:
    - use TreeSet to easily get low and high

    4 cases:
        - merge(low, interval, high)
        - merge(interval, high)
        - merge(low, interval)
        - disjoint, just add to set
     */
    class SummaryRanges {
        TreeSet<int[]> set;

        /** Initialize your data structure here. */
        public SummaryRanges() {
            set = new TreeSet<>((a,b) -> a[0] == b[0]? a[1] - b[1] : a[0] - b[0]);
        }

        public void addNum(int val) {

            // already exist
            int[] interval = {val, val};
            if (set.contains(interval)) return;

            // get low, high
            int[] low = set.lower(interval), high = set.higher(interval);

            // merge low and high
            if (low != null && low[1] + 1 == val && high != null && val + 1 == high[0]) {
                low[1] = high[1];
                set.remove(high);
            }
            // merge low
            else if (low != null && low[1] + 1 >= val) low[1] = Math.max(low[1], val);
                // merge high
            else if (high != null && high[0] - 1 <= val) high[0] = Math.min(high[0], val);
                // disjoint, just add to set
            else set.add(interval);
        }

        public int[][] getIntervals() {
            List<int[]> res = new ArrayList<>(set);
            return res.toArray(new int[0][]);
        }
    }

}
