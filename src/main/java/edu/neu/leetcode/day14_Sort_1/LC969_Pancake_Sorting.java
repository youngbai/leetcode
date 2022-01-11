package edu.neu.leetcode.day14_Sort_1;

import java.util.ArrayList;
import java.util.List;

public class LC969_Pancake_Sorting {


    /*
    Thinking:


    Example:
    [3, 2, 4, 1]
    max=4, idx=2
    flip(2) [4, 2, 3, 1]
    flip(3) [1, 3, 2, 4]
    [3,4]

    max=3, idx=1
    flip(1) [3, 1, 2, 4]
    flip(2) [2, 1, 3, 4]
    [3,4,2,3]

    max=2, idx=0
    flip(0) [2, 1, 3, 4]
    flip(1) [1, 2, 3, 4]
    [3,4,2,3,1,2]

    max=1, idx=0
    flip(0) [1, 2, 3, 4]
    flip(0) [1, 2, 3, 4]
    [3,4,2,3,1,2,1,1]
     */
    class Solution1_Pancake_Sort {
        public List<Integer> pancakeSort(int[] arr) {
            int N = arr.length, max = N;
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int index = find(arr, max);
                flip(arr, index);
                flip(arr, max - 1);
                res.add(index + 1);
                res.add(max--);
            }
            return res;
        }

        private int find(int[] A, int target) {
            for (int i = 0; i < A.length; i++)
                if (A[i] == target) return i;
            return -1;
        }

        private void flip(int[] A, int N) {
            int i = 0, j = N;
            while (i < j) {
                int temp = A[i];
                A[i++] = A[j];
                A[j--] = temp;
            }
        }
    }
}
