package edu.neu.leetcode.day12_Monotonic_Queue;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * All Monotonic Queue solution can be transfer to Priority Queue solution
 * Note:
 * - Monotonic Queue, O(N)      Better
 * - Priority Queue, O(NlogN)
 */
public class LC239_Sliding_Window_Maximum {

    /*
    Solution4: Block+pretreatment(like Sparse Table)
    The Main Idea:
    1.Divide original data into k groups(local value), precalculate the min/max/sum value of each local k groups
    2.Use the Local min/max/sum value to get the Global min/max/sum value
    3.it also used dynamic programming

    Time:  O(n): loop n time for both prefixMax and suffixMax calculation
    Space: O(n): array of prefixMax, suffixMax
    */
    class Solution4_Block_Pretreatment {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] prefixMax = new int[n];
            int[] suffixMax = new int[n];

            for (int i = 0; i < n; i++) {   // O(n)
                if (i % k == 0) {
                    prefixMax[i] = nums[i];
                } else {
                    prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
                }
            }

            for (int i = n - 1; i >= 0; i--) {   // O(n)
                if (i == n - 1 || (i + 1) % k == 0) {
                    suffixMax[i] = nums[i];
                } else {
                    suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
                }
            }

            int[] res = new int[n - k + 1];
            for (int i = 0; i <= n - k; i++) {  // O(n)
                res[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
            }
            return res;
        }
    }

    /*
    Thinking:
    - Best
    - Monotonic Queue (store index, not the value)
    - maintain a decreasing queue, because we are looking for a max element

    Time:  O(N), go through each element
    Space: O(k), Monotonic Queue
     */
    class Solution3_Monotonic_Queue {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int N = nums.length;
            int[] res = new int[N - k + 1];
            Deque<Integer> q = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                int startWindowIndex = i - k + 1;   // left side of window
                // poll all elements outside of window from head
                while (!q.isEmpty() && q.peekFirst() < startWindowIndex) q.pollFirst();
                // maintain a decreasing monotonic queue by polling tail elements
                while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) q.pollLast();
                // offer current element
                q.offerLast(i);
                // find max (head of queue) in current window
                if (startWindowIndex >= 0) res[startWindowIndex] = nums[q.peekFirst()];
            }
            return res;
        }
    }


    /*
    Thinking:
    - PQ
    - window [i, k + i - 1]
      - if max element is out of window, poll it until find one inside window
    - PQ.peek() can find the max element
    - Better than Solution1, worse than Monotonic Queue solution

    Time:  O(Nlogk)
      - go through each element, O(N)
      - operation on PQ, O(logk)
    Space: O(k), PQ's heap contains about k elements
     */
    class Solution2_Priority_Queue {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int[] res = new int[nums.length - k + 1];
            // int[] = {value, index}
            // order by value DESC, index ASC
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[0] != b[0] ? b[0] - a[0] : a[1] - b[1];
                }
            });

            // init PQ with elements in first window
            for (int i = 0; i < k; i++) pq.offer(new int[]{nums[i], i});
            res[0] = pq.peek()[0];

            for (int i = 1; i < res.length; i++) {  // these two for loop: O(N)
                // current window [i, k + i - 1]
                pq.offer(new int[]{nums[k + i - 1], k + i - 1});    // pq.offer(): O(logk)

                // peek queue, check if it is out of window, if so, poll it until find one inside window
                while (pq.peek()[1] < i) pq.poll();                 // pq.poll():  O(logk)

                // find max in this window
                res[i] = pq.peek()[0];
            }
            return res;
        }
    }

    class Solution2_Priority_Queue2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int N = nums.length;
            int[] res = new int[N - k + 1];
            // PQ (value DESC, index AEC)
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0]? b[0] - a[0] : a[1] - b[1]);

            for (int i = 0; i < N; i++) {
                // if index <= i - k, it is out of window
                while (!pq.isEmpty() && pq.peek()[1] <= i - k) pq.poll();
                pq.offer(new int[]{nums[i], i});
                if (i - k + 1 >= 0) res[i - k + 1] = pq.peek()[0];
            }

            return res;
        }
    }


    /*
    Thinking:
    - Worst solution

    Time:  O(Nk)
    Space: O(1)
     */
    class Solution1_Brute_Force {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int N = nums.length;
            int[] res = new int[N - k + 1];

            for (int i = 0; i < res.length; i++) {  // O(N-k+1)
                int max = Integer.MIN_VALUE;
                for (int j = 0; j < k; j++) {       // O(k)
                    max = Math.max(max, nums[i + j]);
                }
                res[i] = max;
            }
            return res;
        }
    }


    /*
    Thinking:
    - DP
    - Priority Queue

    Time : O(Nlogk), for O(N), pq operation O(logk)
    Space: O(k), pq stores k elements
     */
    class Solution {
        public int maxResult(int[] nums, int k) {
            int N = nums.length;
            int[] S = new int[N];
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

            for (int i = 0; i < N; i++) {
                int windowMax = pq.isEmpty()? 0 : pq.peek()[0];
                S[i] = windowMax + nums[i];
                while (!pq.isEmpty() && i - pq.peek()[1] >= k) pq.poll();
                pq.offer(new int[]{S[i], i});
            }
            return S[N - 1];
        }
    }
}
