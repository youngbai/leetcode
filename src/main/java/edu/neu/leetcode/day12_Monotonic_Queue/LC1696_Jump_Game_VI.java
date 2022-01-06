package edu.neu.leetcode.day12_Monotonic_Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC1696_Jump_Game_VI {

    /*
    Thinking: (1st)
    - Monotonic Queue
    - DP

     Time:  O(N), each element enter Q once, out Q at most once
     Space: O(k), Q store at most k elements
     */
    class Solution1_Monotonic_Queue_1 {
        public int maxResult(int[] nums, int k) {
            int N = nums.length;
            int[] S = new int[N];
            Deque<Integer> q = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                int windowMax = q.isEmpty() ? 0 : S[q.peekFirst()]; // windowMax is max value inside the window
                S[i] = windowMax + nums[i]; // calculate result
                //System.out.println(S[i]);
                while (!q.isEmpty() && S[q.peekLast()] <= S[i]) q.pollLast(); // right out
                while (!q.isEmpty() && i - q.peekFirst() >= k) q.pollFirst(); // left out
                q.offerLast(i); // enter Q
            }
            return S[N - 1];
        }
    }


    /*
    Thinking: (2nd)
    - Monotonic Queue
    - DP

    Time:  O(N), each element enter Q once, out Q at most once
    Space: O(k), Q store at most k elements
    */
    class Solution1_Monotonic_Queue_2 {
        public int maxResult(int[] nums, int k) {
            int N = nums.length;
            int[] S = new int[N];
            S[0] = nums[0]; // init S[0]
            Deque<Integer> q = new ArrayDeque<>();
            q.offerLast(0); // make sure q is not empty

            // go though from index=1
            for (int i = 1; i < N; i++) {
                S[i] = S[q.peekFirst()] + nums[i]; // calculate result
                while (!q.isEmpty() && i - q.peekFirst() >= k) q.pollFirst(); // left out
                while (!q.isEmpty() && S[q.peekLast()] <= S[i]) q.pollLast(); // right out
                q.offerLast(i); // enter Q
            }
            return S[N - 1];
        }
    }



}
