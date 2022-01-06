package edu.neu.leetcode.day14_Sort_1;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class QuickSort {

    public void quickSort(int[] A) {
        quickSort(A, 0, A.length - 1);
    }

    private void quickSort(int[] A, int left, int right) {
        if (left >= right) return;
        int pivot = partition(A, left, right);
        quickSort(A, left, pivot - 1);
        quickSort(A, pivot + 1, right);
    }

    private int partition(int[] A, int left, int right) {
        int pivot = A[right];
        int i = left;   // i-1: end of < pivot, i: start of >= pivot
        for (int j = left; j <= right - 1; j++) {   // j: unprocessed part
            if (A[j] < pivot) {
                swap(A, i, j);
                i++;
            }
        }
        swap(A, i, right);  // swap i (>=pivot) with right (pivot)
        return i;
    }

    // random pivot
    private int randomPivotIndex(int left, int right) {
        return left + (int) (Math.random() * (right - left + 1));
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    @Test
    public void test() {

        int[] arr = {35, 33, 42, 10, 14, 19, 27, 44};
        QuickSort sort = new QuickSort();
        sort.quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
