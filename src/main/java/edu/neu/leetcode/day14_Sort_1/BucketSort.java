package edu.neu.leetcode.day14_Sort_1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public void sort(float[] A, int N) {

        // 1. create N empty buckets
        List<Float>[] buckets = new List[N];    // N buckets
        for (int i = 0; i < N; i++) {
            buckets[i] = new ArrayList<>();
        }

        // 2. put each element into A[i]*N bucket
        for (int i = 0; i < A.length; i++) {
            int idx = (int)A[i] * N;
            buckets[idx].add(A[i]);
        }

        // 3. sort each bucket
        for (List<Float> bucket: buckets) Collections.sort(bucket);

        // 4. concatenate all buckets into A[]
        int idx = 0;
        for (int i = 0; i < N; i++) {   // go through each bucket
            for (float element: buckets[i]) {
                A[idx++] = element;
            }
        }
    }

    @Test
    public void test() {

        float[] A = {
                (float) 0.897, (float) 0.565,
                (float) 0.656, (float) 0.1234,
                (float) 0.665, (float) 0.3434
        };
        BucketSort sort = new BucketSort();
        int N = 10; // 10 buckets
        sort.sort(A, N);
        System.out.println(Arrays.toString(A));
    }

}
