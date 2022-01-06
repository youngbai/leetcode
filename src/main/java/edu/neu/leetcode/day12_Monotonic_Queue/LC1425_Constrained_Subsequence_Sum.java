package edu.neu.leetcode.day12_Monotonic_Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC1425_Constrained_Subsequence_Sum {

    /*
    Thinking:
    - Decreasing Monotonic Queue as the sliding window
    - with O(1) to get the maximum of the sliding window
    - sum[i] could be <= 0, don't offer it to Queue, because it will make
      the following value in sum array smaller, and our goal is to find the max value in sum

     Time:  O(N), each element enter Q once, out Q at most once
     Space: O(k), Q store at most k elements
     */
    class Solution1_DP_Monotonic_Queue {
        public int constrainedSubsetSum(int[] nums, int k) {
            int res = nums[0], N = nums.length;
            int[] sum = new int[N];
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                sum[i] = nums[i]; // init sum[i]
                if (!q.isEmpty())  sum[i] += sum[q.peekFirst()];   // find the max of sliding window
                res = Math.max(res, sum[i]);
                //System.out.println(sum[i]);
                while (!q.isEmpty() && i - q.peekFirst() >= k) q.pollFirst();       // poll left
                while (!q.isEmpty() && sum[q.peekLast()] <= sum[i]) q.pollLast();   // poll last
                if (sum[i] > 0) q.offerLast(i);
            }
            return res;
        }
    }
}
