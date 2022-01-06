package edu.neu.leetcode.day14_Sort_1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CountingSort {

    public void sort(int[] A) {
        int N = A.length;
        int[] output = new int[N];  // the sorted result
        int[] count = new int[100]; // contain how many time i occurs in A

        for (int i = 0; i < N; i++) count[A[i]]++;  // do counting
        for (int i = 1; i < count.length; i++) count[i] += count[i - 1]; // do prefix sum
        for (int i = 0; i < N; i++) {   // counting sort
            output[count[A[i]] - 1] = A[i];
            count[A[i]]--;
        }
        // copy result to original array
        for (int i = 0; i < N; i++) A[i] = output[i];
    }

    @Test
    public void test() {

        int[] arr = {35, 33, 42, 10, 14, 19, 27, 44};
        CountingSort sort = new CountingSort();
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
