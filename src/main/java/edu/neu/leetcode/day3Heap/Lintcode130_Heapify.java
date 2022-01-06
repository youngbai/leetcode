package edu.neu.leetcode.day3Heap;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lintcode130_Heapify {

    /*
    We don't have to do heapify for all node, because all leaves (nodes int the last layer) don't need heapify.
    We only need to do heapify from the penultimate layer. To be exact, from the last node with children, whose
    index is A.length / 2;
    E.g.
        0
     1    2
    3 4  5

    parent:         i
    left child:  2 * i + 1
    right child: 2 * i + 2

    left or right child: i
    parent: (i - 1) / 2

    Build Heap Time: O(N)
    See youdao notes: day3_Heap
     */
    public static void buildHeap(int[] A) {
        for (int i = A.length / 2; i >= 0; i--) {
            heapify(A, A.length, i);
        }
    }

    // Min Heap
    // recursive implementation
    // do heapify on element i
    private static void heapify(int[] A, int N, int i) {
        int min = i;    // i is root
        int l = 2 * i + 1;  // l is left
        int r = 2 * i + 2;  // r is right

        if (l >= N && r >= N) return;
        if (l < N && A[l] < A[min]) min = l;
        if (r < N && A[r] < A[min]) min = r;

        if (min != i) { // root is NOT the smallest number
            int swap = A[i];
            A[i] = A[min];
            A[min] = swap;
            // Recursively heapify the affected sub-tree
            heapify(A, N, min);
        }
    }


    @Test
    public void test1() {
        int[] arr = {3, 2, 1, 7, 8, 4, 5,};
        buildHeap(arr);
        System.out.println(Arrays.toString(arr));
        assertTrue(isMiniHeap(arr));
    }

    private boolean isMiniHeap(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < N && arr[left] < arr[i]) return false;
            if (right < N && arr[right] < arr[i]) return false;
        }
        return true;
    }
}
