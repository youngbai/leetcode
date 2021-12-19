package edu.neu.leetcode.day6_Sweep_List;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://github.com/JacobHuang91/all-in-one/blob/master/_leetcode/253.%20Meeting%20Rooms%20II.md
 */
public class LC253_Meeting_RoomsII {

    public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    /*
    Thinking: (NOT best solution)
    - use Priority Queue to conveniently find the Earliest Ended Meeting (EEM),
        - if EEM.end <= current meeting's start, it means EEM room can be reused by current meeting,
            so we DON'T need extra meeting room, let offer current meeting to the PQ
        - else, it means EEM room can NOT be reused by current meeting,
            so we need extra meeting room, offer EEM and current meeting to the PQ
    - this algo requires firstly all meetings are sorted by start,
        and PQ is sorted by end
    - traverse each meeting,
        - if the new meeting is overlapped with earliest end of meeting,
            then need extra meeting room, pq.offer(new meeting)
        - if the new meeting is NOT overlapped with earliest end of meeting,
            then DON'T need extra meeting room
    - in the end, PQ.size is the max room we need

    e.g.
        - [1, 5] [7, 10]  NOT overlapped, [1, 5] room can be reused, let [7, 10] replace [1, 5] in PQ
        - [1, 5] [3, 8]  overlapped, [1, 5] room can NOT be reused, offer [3, 8] to PQ
        - PQ.size is the max room we need

    Time:  O(nlogn)
        - n, for loop, traverse each meeting
        - logn, PQ offer()
    Space: O(n)
     */
    class Solution1 {
        public int minMeetingRooms(int[][] intervals) {
            // check condition
            if (intervals == null || intervals.length == 0) return 0;

            // sort by start
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

            // create PQ
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pq.offer(intervals[0]);

            // traverse each meeting, either replace the EEM, or insert into the PQ
            for (int i = 1; i < intervals.length; i++) {    // O(n)
                int[] EEM = pq.poll();
                if (EEM[1] <= intervals[i][0]) {    // NOT overlapped, can be reused, NO extra meeting needed
                    pq.offer(intervals[i]);         // O(logn)
                } else {    // overlapped, can NOT be reused, need extra meeting
                    pq.offer(EEM);
                    pq.offer(intervals[i]);
                }
            }
            return pq.size();
        }
    }

    @Test
    public void testSolution1() {
        Solution1 s = new Solution1();
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        assertEquals(2, s.minMeetingRooms(intervals));

        intervals = new int[][]{{7, 10}, {2, 4}};
        assertEquals(1, s.minMeetingRooms(intervals));
    }

    /*
    Thinking: (best solution)
    - sweep list (1 array)

    Time:  O(n)
    Space: O(n)
     */
    class Solution2 {
        public int minMeetingRooms(int[][] intervals) {
            // create sweep line
            List<int[]> list = new ArrayList(intervals.length * 2);
            for (int[] interval : intervals) {
                list.add(new int[]{interval[0], 1});
                list.add(new int[]{interval[1], -1});
            }

            // sort sweep line
            Collections.sort(list, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);

            // traverse sweep line, compute result
            int res = 0, cnt = 0;
            for (int[] point : list) {
                cnt += point[1];
                res = Math.max(res, cnt);
            }
            return res;
        }
    }

    @Test
    public void testSolution2() {
        Solution2 s = new Solution2();
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        assertEquals(2, s.minMeetingRooms(intervals));

        intervals = new int[][]{{7, 10}, {2, 4}};
        assertEquals(1, s.minMeetingRooms(intervals));
    }

    /*
    Thinking: (best solution)
    - sweep list (2 array)

    Time:  O(n)
    Space: O(n)
     */
    class Solution3 {
        public int minMeetingRooms(int[][] intervals) {
            // create sweep line
            int[] starts = new int[intervals.length], ends = new int[intervals.length];
            for (int i = 0; i < intervals.length; i++) {
                starts[i] = intervals[i][0];
                ends[i] = intervals[i][1];
            }

            // sort sweep line
            Arrays.sort(starts);
            Arrays.sort(ends);

            // traverse sweep line and compute result
            int room = 0, endIdx = 0;
            for (int i = 0; i < starts.length; i++) {
                if (starts[i] < ends[endIdx]) room++;
                else endIdx++;
            }
            return room;
        }
    }

    @Test
    public void testSolution3() {
        Solution3 s = new Solution3();
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        assertEquals(2, s.minMeetingRooms(intervals));

        intervals = new int[][]{{7, 10}, {2, 4}};
        assertEquals(1, s.minMeetingRooms(intervals));
    }

}
