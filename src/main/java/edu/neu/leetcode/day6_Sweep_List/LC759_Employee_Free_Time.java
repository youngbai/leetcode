package edu.neu.leetcode.day6_Sweep_List;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LC759_Employee_Free_Time {

    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    /*
    Thinking:
    - sort all interval by PQ
    - if overlap, merge them
    - if NO overlap, we found the free time
     */
    class Solution {
        public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
            List<Interval> res = new ArrayList<>();
            PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
            for (List<Interval> list: schedule) {
                for (Interval interval : list)
                    pq.offer(interval);
            }

            Interval cur = pq.poll();
            while (!pq.isEmpty()) {
                if (cur.end >= pq.peek().start) {
                    // overlap, merge them
                    cur.end = Math.max(cur.end, pq.poll().end);
                } else {
                    // NO overlap, compute the free time
                    res.add(new Interval(cur.end, pq.peek().start));
                    cur = pq.poll();
                }
            }
            return res;
        }
    }
}
