package edu.neu.leetcode.day10_Divide_and_Conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
Closest Pair of Points
 */
public class Amz_Closest_Pair_of_Points {

    /*
    Thinking
    - for each points, calculate its distance to all other points

    Time : O(N^2)

    https://www.youtube.com/watch?v=ldHA8UcQI9Q
     */
    class Solution1_BruteForce {
        public long findClosedPair(int n, int[] xs, int[] ys) {
            // init points
            List<Point> points = new ArrayList<>(xs.length);
            for (int i = 0; i < xs.length; i++) points.add(new Point(xs[i], ys[i]));

            // find
            long min = Long.MAX_VALUE;
            Point[] pair = new Point[2];
            for (int i = 0; i < points.size(); i++)
                for (int j = i + 1; j < points.size(); j++) {
                    long dist = points.get(i).distance(points.get(j));
                    if (dist < min) {
                        min = dist;
                        pair[0] = points.get(i);
                        pair[1] = points.get(j);
                    }
                }

            System.out.printf("min dist is %s, pair is (%s), (%s)%n", min, pair[0], pair[1]);
            return min;
        }

    }

    /*
    Thinking:
    - Divide and Conquer
     */
    class Solution2_Divide_Conquer {
        public long findClosedPair(int n, int[] xs, int[] ys) {
            // init points
            List<Point> points = new ArrayList<>(xs.length);
            for (int i = 0; i < xs.length; i++) points.add(new Point(xs[i], ys[i]));

            // sort points with x for the half split guarantee
            points.sort((a, b) -> a.x - b.x);
            return divide(0, points.size() - 1, points);
        }

        private long divide(int left, int right, List<Point> points) {
            long curMinDis = Long.MAX_VALUE;

            // terminal case
            if (left == right) return curMinDis;
            if (left + 1 == right) return points.get(left).distance(points.get(right));

            // Step1: split points into left and right
            int mid = left + (right - left) / 2;
            long leftMinDis = divide(left, mid, points);
            long rightMinDis = divide(mid, right, points);
            curMinDis = Math.min(leftMinDis, rightMinDis);

            // Step2: deal with middle padding [mid-curMinDis, mid+curMinDis] in x dimension
            List<Point> paddingPoints = new ArrayList<>();
            for (int i = left; i <= right; i++) {
                if (Math.pow(points.get(i).x - points.get(mid).x, 2) <= curMinDis)
                    paddingPoints.add(points.get(i));
            }

            // sort the padding points in y dimension, so that we are able to search it from top to bottom
            paddingPoints.sort((a, b) -> a.y - b.y);

            // search the padding (we only need to search the up next 5 points)
            for (int i = 0; i < paddingPoints.size(); i++)
                for (int j = i + 1; j < Math.min(i + 6, paddingPoints.size()); j++) {
                    if (Math.pow(paddingPoints.get(i).y - paddingPoints.get(j).y, 2) >= curMinDis) break;
                    long tempDis = paddingPoints.get(i).distance(paddingPoints.get(j));
                    curMinDis = Math.min(curMinDis, tempDis);
                }

            return curMinDis;
        }
    }


    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public long distance(Point p) {
            return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
        }

        public String toString() {
            return String.format("(%s, %s)", x, y);
        }
    }

    public static void main(String[] args) {
        int n = 90;
        int[] xs = gen(n);
        int[] ys = gen(n);

        System.out.println("--------- Solution1 ----------");
        Amz_Closest_Pair_of_Points amz = new Amz_Closest_Pair_of_Points();
        long result1 = amz.new Solution1_BruteForce().findClosedPair(n, xs, ys);
        System.out.println("result1 is " + result1);

        System.out.println("--------- Solution2 ----------");
        long result2 = amz.new Solution2_Divide_Conquer().findClosedPair(n, xs, ys);
        System.out.println("result2 is " + result2);
    }

    private static int[] gen(int n) {
        int min = 1, max = 1000;
        int[] nums = new int[n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            nums[i] = rand.nextInt(max - min) + min;
        }

        System.out.println(Arrays.toString(nums));
        return nums;
    }


}
