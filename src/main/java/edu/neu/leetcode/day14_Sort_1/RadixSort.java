package edu.neu.leetcode.day14_Sort_1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
Algo:
- counting sort on last digit
- counting sort on second last digit
- counting sort on third last digit
- ...
- end

Example:
Example:
max = 999
keep max/exp>0
999/1>0		countSort
999/10>0	countSort
999/100>0	countSort
999/1000=0	no action

- 1st Loop
A = [21 12 11]
exp=1

count
0 0
1 2
2 1

count (prefix sum)
0 0
1 2
2 3

output = [11 21 12]

- 2nd Loop
exp=10

count
0 0
1 2
2 1

count (prefix sum)
0 0
1 2
2 3

output = [11, 12, 21]



Time:  O(d(N+k))
  - d: digits
  - N: length of A array
  - k: length of count array
Space: O(N)
 */
public class RadixSort {

    public void sort(int[] A) {
        int m = Arrays.stream(A).max().getAsInt();
        for (int exp = 1; m / exp > 0; exp *= 10) countSort(A, exp);
    }

    private void countSort(int[] A, int exp) {
        int[] output = new int[A.length];
        int[] count = new int[10];
        for (int n : A) count[(n / exp) % 10]++;  // do counting
        for (int i = 1; i < count.length; i++) count[i] += count[i - 1];  // do prefix sum
        for (int i = A.length - 1; i >= 0; i--) output[--count[(A[i] / exp) % 10]] = A[i];  // do counting sort
        for (int i = 0; i < A.length; i++) A[i] = output[i]; // copy result to A
        System.out.println(Arrays.toString(A));
    }

    @Test
    public void test() {
        int[] arr = {35, 33, 42, 10, 14, 19, 27, 44};
        RadixSort sort = new RadixSort();
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
