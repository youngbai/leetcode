package edu.neu.leetcode.day9_Binary_Search;

import java.util.Arrays;

public class LC1552_Magnetic_Force_Between_Two_Balls {

    /*
    Thinking:
    - Binary Search
    - the answer is between 1 and max distance (position[position.length - 1] - position[0])
      - the shortest distance is when ball next to each other, so answer >= 1
      - the longest distance is when two balls at the ends of baskets, so answer <= position[position.length - 1] - position[0]
    - then binary search between low (`shortest distance`) and high (`longest distance`)
    - use valid() to check if we can put balls into baskets with minDistance
      - if we can, it means the answer can be larger
      - if we can not, it means the answer should be smaller

    Time:  O(log(longest distance - shortest distance)*n)
    - log(longest distance - shortest distance): binary search between shortest distance and longest distance
    - n : valid() traverse all elements
    Space: O(1)
     */
    class Solution {
        public int maxDistance(int[] position, int m) {
            Arrays.sort(position);
            int low = 1, high = position[position.length - 1] - position[0];
            while (low <= high) {
                int mid = low + (high - low) / 2;
                //System.out.printf("low=%d, high=%d, mid=%d, valid=%b%n", low, high, mid, valid(position, m, mid));
                if (valid(position, m, mid)) low = mid + 1;
                else high = mid - 1;
            }
            return high;
        }
        private boolean valid(int[] position, int m, int minDist) {
            int curPos = position[0], count = 1;
            // System.out.printf("curPos=%d, count=%d%n", curPos, count);
            for (int i = 1; i < position.length; i++) {
                if ((position[i] - curPos) >= minDist) {
                    curPos = position[i];
                    count++;
                    // System.out.printf("curPos=%d, count=%d%n", curPos, count);
                    if (count >= m) return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {

        System.out.println(Math.ceil(5 * 1.0 / 2));
    }
}
