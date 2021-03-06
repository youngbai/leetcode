package edu.neu.leetcode.day3Heap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeapSortASC {

    public void sort(int[] A) {
        int N = A.length;

        // 1.build max heap
        for (int i = N / 2; i >= 0; i--) {
            heapify(A, N, i);
        }

        // 2.swap root(0) and last element(i=N-1, i--), heapify on root
        for (int i = N - 1; i > 0; i--) {
            // exchange root (which is 0) and last element (which is i)
            swap(A, 0, i);
            // heapify on 0
            heapify(A, i, 0);
        }
    }

    // do heapify on element i
    public void heapify(int[] A, int N, int i) {
        int max = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l >= N && r >= N) return;
        if (l < N && A[l] > A[max]) max = l;
        if (r < N && A[r] > A[max]) max = r;
        if (max != i) {
            // exchange element in i and max
            swap(A, i, max);
            heapify(A, N, max);
        }
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static boolean isSorted(int[] A) {
        for (int i = 1; i < A.length; i++) {
            if (A[i-1] > A[i]) return false;
        }
        return true;
    }


    @Test
    public void test1() {

        int[] arr = {3, 2, 1, 7, 8, 4, 5,};
        HeapSortASC heapSort = new HeapSortASC();
        heapSort.sort(arr);

        System.out.println(Arrays.toString(arr));
        assertTrue(isSorted(arr));
    }
}
