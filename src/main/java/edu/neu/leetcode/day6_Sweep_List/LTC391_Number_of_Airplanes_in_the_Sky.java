package edu.neu.leetcode.day6_Sweep_List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/*
LintCode:
https://www.lintcode.com/problem/391/description
 */
public class LTC391_Number_of_Airplanes_in_the_Sky {

    public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    /*
    Thinking:
    - Sweep Line, so don't have traverse every hour
    - Method1: use 1 array containing all points
    - Method1: use 2 array, 1st one contains the taking off point, 2nd one contains the landing point

    Sweep Line Common Algo:
        - create sweep line
        - sort sweep line
        - traverse sweep line and compute result
     */
    public class Solution1 {

        class Point implements Comparable<Point>{
            int T;
            int S;
            Point(int time, int status) {
                T = time;
                S = status;
            }
            /*
             | landing | taking off
            S|   -1    |     1
            */
            public int compareTo(Point p) {
                if (this.T != p.T) return this.T - p.T;
                return this.S - p.S;
            }
        }

        /**
         * @param airplanes: An interval array
         * @return: Count of airplanes are in the sky.
         */
        public int countOfAirplanes(List<Interval> airplanes) {
            // check condition
            if (airplanes == null || airplanes.size() == 0) return 0;

            // create sweep line
            List<Point> list = new ArrayList<>(airplanes.size()*2);
            for (Interval i : airplanes) {
                list.add(new Point(i.start, 1));
                list.add(new Point(i.end, -1));
            }

            // sort sweep line
            // use lambda
            Collections.sort(list, (Point p1, Point p2)->{
                if (p1.T != p2.T) return p1.T - p2.T;
                return p1.S - p2.S;
            });

            // traverse sweep line and compute the result
            int res = 0, cnt = 0;
            // for (Point p : list) {
            //     if (p.S == 1) {
            //         cnt++;
            //         res = Math.max(res, cnt);
            //     } else {
            //         cnt--;
            //     }
            // }
            for (Point p : list) {
                cnt = cnt + p.S;
                res = Math.max(res, cnt);
            }
            return res;
        }
    }


    /*
    Thinking:
    - same as Solution1
    - use 2 array, each array contains the time
     */
    public class Solution2 {
        /**
         * @param airplanes: An interval array
         * @return: Count of airplanes are in the sky.
         */
        public int countOfAirplanes(List<Interval> airplanes) {
            if (airplanes == null || airplanes.size() == 0) return 0;

            // create sweep line
            int n = airplanes.size();
            int[] start = new int[n];
            int[] end = new int[n];
            for (int i = 0; i < airplanes.size(); i++) {
                start[i] = airplanes.get(i).start;
                end[i] = airplanes.get(i).end;
            }

            // sort sweep line
            Arrays.sort(start);
            Arrays.sort(end);

            // traverse sweep line and compute result
            int i = 0, j = 0, res = 0, cnt = 0;
            // if it comes to the end of start array, there is plane to take off, end loop in advance
            while (i < n) {
                if (start[i] < end[j]) {
                    cnt++; i++;
                    res = Math.max(res, cnt);
                } else {
                    cnt--; j++;
                }
            }
            return res;
        }
    }

}
