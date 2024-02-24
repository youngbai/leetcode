package edu.neu.leetcode.day14_Sort_1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Question:
给一个almost sorted collection，其中1-2个element un-sorted，
用什么sorting method 达到最优time complexity。多次提醒后才发现最优情况应该是O(n)。
 */
public class Paypal_Almost_Sorted_Array {

    class Solution1_Insertion_Sort {

        public void sort(int[] A) {
            // for find the decreasing position
            // while swap

            for (int i = 1; i < A.length; i++) {
                if (A[i - 1] > A[i]) {
                    // decreasing
                    int j = i;  // swap(j-1, j)
                    while (j > 0 && A[j - 1] > A[j]) {
                        swap(A, j - 1, j);
                        j--;
                    }
                }
            }

        }

        private void swap(int[] A, int i, int j) {
            int t = A[i];
            A[i] = A[j];
            A[j] = t;
        }
    }


    @Test
    public void test() {

        int[] arr = {1, 3, 4, 2, 5, 6};
        test1(arr);

        arr = new int[]{1, 4, 3, 2, 5, 6};
        test1(arr);

        arr = new int[]{1, 3, 2, 5, 4, 6};
        test1(arr);
    }

    public void test1(int[] arr) {
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);

        Solution1_Insertion_Sort s = new Solution1_Insertion_Sort();
        s.sort(arr);
        System.out.println(Arrays.toString(arr));
        assertArrayEquals(arr, sorted);
    }

}
