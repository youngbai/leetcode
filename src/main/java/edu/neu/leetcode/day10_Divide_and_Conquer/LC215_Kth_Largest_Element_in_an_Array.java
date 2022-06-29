package edu.neu.leetcode.day10_Divide_and_Conquer;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class LC215_Kth_Largest_Element_in_an_Array {

    /*
    Thinking:
    - sort first
    - a[k-1] is the answer

    Time : O(NlogN)
    Space: O(1)
     */
    class Solution0 {

    }

    /*
    Thinking:
    - quick sort partition or Quick Select
    - divide and conquer

    Intuition:
    - turn kth largest problem to (n-k)th smallest problem
    - choose a pivot, let all nums on the left of pivot is < pivot, all nums on the right is >= pivot
    - if pivot is kth smallest (pivot's index is k), then pivot is the answer
    - if pivot's index < k, then the answer should be on pivot's right side
    - if pivot's index > k, then the answer should be on pivot's left side

    Advantage:
    - no need to sort all elements, just deal with left or right partition
    - so, average time complexity is much less than O(NlogN)

    Time:  Best O(NlogN), Worst O(N^2), Avg O(N)
    - how to improve: randomly choose the pivot
    Space: O(1)

    Other solutions
    Ref: https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60294/Solution-explained
     */
    class Solution1_Quick_Select_Iteration {
        public int findKthLargest(int[] nums, int k) {
            k = nums.length - k;    // turn kth largest problem to (n-k)th smallest problem
            int lo = 0, hi = nums.length - 1;
            while (lo < hi) {
                // after partition, all nums on the left of pivot is < pivot, all nums on the right is >= pivot
                int j = partition(nums, lo, hi);
                if (k < j) hi = j - 1;
                else if (k > j) lo = j + 1;
                else break; // we find it
            }
            return nums[k];
        }

        // like quick sort, choose hi as pivot, and let all nums on the left of pivot is < pivot, all nums on the right is >= pivot
        private int partition(int[] nums, int lo, int hi) {
            int pivot = nums[hi];
            int i = lo;  // i : first element > pivot
            for (int j = lo; j < hi; j++) {  // j : next unchecked number
                if (nums[j] < pivot) {
                    swap(nums, i, j);
                    i++;
                }
            }
            swap(nums, i, hi);
            return i;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }


    /*
    Thinking:
    - Quick Select Recursion implementation
     */
    class Solution1_Quick_Select_Recursion {
        class Solution {
            public int findKthLargest(int[] nums, int k) {
                //shuffle(nums);
                divide(nums, 0, nums.length - 1, k);
                return nums[nums.length - k];
            }

            private void shuffle(int[] nums) {
                Random random = new Random();
                for (int i = 0; i < nums.length; i++) {
                    int j = random.nextInt(i + 1);  // [0, i+1)
                    swap(nums, i, j);
                }
                System.out.println(Arrays.toString(nums));
            }

            private void divide(int[] nums, int left, int right, int k) {
                if (left >= right) return;
                int pivotIndex = partition(nums, left, right);
                if (pivotIndex == nums.length - k) return;
                else if (pivotIndex < nums.length - k) divide(nums, pivotIndex + 1, right, k);
                else divide(nums, left, pivotIndex - 1, k);
            }

            private int partition(int[] nums, int left, int right) {
                int pivot = nums[right];
                int i = left;   // i-1: end of left part (<pivot), i: start of the right part (>=pivot)
                for (int j = left; j < right; j++) {
                    if (nums[j] < pivot) {
                        swap(nums, i, j);
                        i++;
                    }
                }
                swap(nums, i, right);
                return i;
            }

            private void swap(int[] nums, int i, int j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    }

    /*
    Thinking:
    - Heap

    Intuition:
    - use a min Heap to maintain k elements
    - when k+1 element comes, push it into Heap, and poll 1 element from Heap
    - do the same things for following elements, so Heap always has the largest k elements
    - in the end, poll from Heap, that is the answer

    Time : O(Nlogk)
    Space: O(k)
     */
    class Solution2_Heap {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> n1 - n2);

            for (int num : nums) {  // O(N)
                pq.offer(num);      // O(logk)
                if (pq.size() > k) pq.poll();   // O(logk)
            }

            return pq.poll();
        }
    }

}
