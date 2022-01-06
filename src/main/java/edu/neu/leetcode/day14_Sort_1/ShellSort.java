package edu.neu.leetcode.day14_Sort_1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
Time:  O(N^2)
Space: O(1)
 */
public class ShellSort {

    public void shellSort(int[] arr) {
        int N = arr.length;
        for (int gap = N / 2; gap > 0; gap /= 2) {
            // insert sort,  [i - gap, i, i + gap, i + gap * 2, i + gap * 3]
            for (int i = gap; i < N; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    @Test
    public void test() {

        int[] arr = {35, 33, 42, 10, 14, 19, 27, 44};
        ShellSort sort = new ShellSort();
        sort.shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
