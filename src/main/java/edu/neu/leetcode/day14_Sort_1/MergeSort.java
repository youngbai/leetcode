package edu.neu.leetcode.day14_Sort_1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MergeSort {

    public void mergeSort(int[] A) {
        mergeSort(A, 0, A.length - 1);
    }

    private void mergeSort(int[] A, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(A, left, mid);
        mergeSort(A, mid + 1, right);
        merge(A, left, mid, right);
    }

    private void merge(int[] A, int left, int mid, int right) {
        int[] aux = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (A[i] <= A[j])
                aux[k++] = A[i++];
            else
                aux[k++] = A[j++];
        }

        while (i <= mid) aux[k++] = A[i++];
        while (j <= right) aux[k++] = A[j++];

        for (i = left, k = 0; i <= right; i++) {
            A[i] = aux[k++];
        }
    }


    @Test
    public void test() {

        int[] arr = {35, 33, 42, 10, 14, 19, 27, 44};
        MergeSort sort = new MergeSort();
        sort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
