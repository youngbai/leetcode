package edu.neu.leetcode.day10_Divide_and_Conquer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Amz_Inversion_Count {


    /*
    Thinking:
    Time:  O(n^2)
    Space: O(1)
     */
    class Solution1_BruteForce {

        public int inversionCount(int[] arr) {

            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i] > arr[j]) count++;
                }
            }
            return count;
        }

    }

    /*
    Thinking:
    - Divide and Conquer
    - like mergesort
    - divide is like merge
    - conquer is like sort

    Conquer
    -    left      right     Both left,right are ordered
      [...a...] [...b...]
    - if a > b, then numbers following a is also > b, they also count for inversion count
      then count it, and move `b` forward, `a` stay
    - if a <= b, then no inversion, move `a` forward, `b` stay

    Time:  O(nlogn), like mergesort
    Space: O(n)
     */
    class Solution2_Divide_Conquer {
        public int inversionCount(int[] arr) {
            return mergeSortAndCount(arr, 0, arr.length - 1);
        }

        public int mergeSortAndCount(int[] arr, int l, int r) {
            // base case
            if (l >= r) return 0;

            // divide
            int count = 0;
            int m = l + (r - l) / 2;
            count += mergeSortAndCount(arr, l, m);
            count += mergeSortAndCount(arr, m + 1, r);

            // conquer
            count += mergeAndCount(arr, l, m, r);
            return count;
        }

        public int mergeAndCount(int[] arr, int l, int m, int r) {

            int[] left = Arrays.copyOfRange(arr, l, m + 1);      // NOTE: Arrays.copyOfRange(array, from, to)
            int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

            int i = 0, j = 0, k = l, count = 0;
            while (i < left.length && j < right.length) {
                if (left[i] <= right[j])
                    arr[k++] = left[i++];
                else {
                    arr[k++] = right[j++];
                    //count += (m + 1) - (l + i);
                    count += left.length - i;
                }
            }
            while (i < left.length) arr[k++] = left[i++];
            while (j < right.length) arr[k++] = right[j++];

            return count;
        }
    }

    @Test
    public void testSolution1() {

        Amz_Inversion_Count amz = new Amz_Inversion_Count();
        Solution1_BruteForce s1 = amz.new Solution1_BruteForce();
        int[] arr1 = {8, 4, 2, 1};
        assertEquals(6, s1.inversionCount(arr1));

        int[] arr2 = {3, 1, 2};
        assertEquals(2, s1.inversionCount(arr2));
    }

    @Test
    public void testSolution2() {

        Amz_Inversion_Count amz = new Amz_Inversion_Count();
        Solution2_Divide_Conquer s2 = amz.new Solution2_Divide_Conquer();
        int[] arr1 = {8, 4, 2, 1};
        assertEquals(6, s2.inversionCount(arr1));

        int[] arr2 = {3, 1, 2};
        assertEquals(2, s2.inversionCount(arr2));
    }


}
