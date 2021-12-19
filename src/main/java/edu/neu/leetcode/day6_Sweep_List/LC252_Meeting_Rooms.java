package edu.neu.leetcode.day6_Sweep_List;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
https://github.com/JacobHuang91/all-in-one/blob/master/_leetcode/252.%20Meeting%20Rooms.md
 */
public class LC252_Meeting_Rooms {

    public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    class Solution {
        public boolean canAttendMeetings(Interval[] intervals) {
            Arrays.sort(intervals, (a, b) -> a.start - b.start);
            for (int i = 0; i < intervals.length - 1; i++) {
                if (intervals[i].end > intervals[i + 1].start) return false;
            }
            return true;
        }
    }

    @Test
    public void test() {
        Solution s = new Solution();
        Interval[] intervals = new Interval[]{new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
        assertFalse(s.canAttendMeetings(intervals));

        intervals = new Interval[]{new Interval(7, 10), new Interval(2, 4)};
        assertTrue(s.canAttendMeetings(intervals));
    }

}
