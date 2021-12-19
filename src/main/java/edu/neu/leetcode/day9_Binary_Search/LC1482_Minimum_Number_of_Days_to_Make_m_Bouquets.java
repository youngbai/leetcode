package edu.neu.leetcode.day9_Binary_Search;

import java.util.Arrays;

public class LC1482_Minimum_Number_of_Days_to_Make_m_Bouquets {

    /*
    Thinking:
    - Binary Search
    - min day <= answer days <= max day
     */
    class Solution {
        public int minDays(int[] bloomDay, int m, int k) {
            if (m * k > bloomDay.length) return -1;
            int min = Arrays.stream(bloomDay).min().getAsInt();
            int max = Arrays.stream(bloomDay).max().getAsInt();
            return binary(bloomDay, m, k, min, max);
        }

        private int binary(int[] bloomDay, int m, int k, int low, int high) {
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (valid(bloomDay, m, k, mid)) high = mid - 1;
                else low = mid + 1;
            }
            return low;
        }

        private boolean valid(int[] bloomDay, int m, int k, int minDays) {
            int sum = 0, count = 0;
            for (int day: bloomDay) {
                if (day <= minDays) {
                    sum++;
                    if (sum == k) {
                        sum = 0;
                        count++;
                        if (count == m) return true;
                    }
                } else {
                    sum = 0;
                }
            }
            return false;
        }
    }
}
