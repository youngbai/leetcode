package edu.neu.leetcode.day12_Monotonic_Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC862_Shortest_Subarray_with_Sum_at_Least_K {


    /*
    Thinking:
    - preSum
    - Monotonic Queue
    - turn the original problem into finding the difference between two elements in prefix sum

    prefix sum:
    - with O(1), the sum of the elements between two indices can be found
    - sum from i to j-1 = preSum[j] - preSum[i]
    - example
      preSum [           |sum from i to j-1|   ]
      preSum [|preSum[i]|                      ]
      preSum [|           preSum[j]           |]

    Example:
    - nums=[1,1,1,1,6,-3,-3,1,9,2], k=11
    - build prefix sum
      [1,1,1,1,6,-3,-3,1,9, 2]
       0,1,2,3,4,10, 7,4,5,14,16 	prefix sum
    - Bad solution O(N^2):
      for i = 0 to N+1	// right of the window
          for j = i - 1 to 0	// left of the window
              j.val - i.val >= 11	// find the difference that is >= 11
    - Monotonic queue Solution Process:
      0
      0 1
      0 1 2
      0 1 2 3
      0 1 2 3 4
      0 1 2 3 4 10
      0 1 2 3 4  7
      0 1 2 3    4
      0 1 2 3    4 5
            3    4 5 14			Length=6
                   5 14 16		Length=3

     Time:  O(N), each element enter Q once, out Q at most once
     Space: O(N), Q store at most every elements

     Ref:
     https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque
     https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque#:~:text=same%20idea%2C%20this%20is%20my%20thinking%20process%20to%20improve%20the%20o(n%5E2)%20solution%20to%20o(n).
     https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque#:~:text=for%20people%20still%20do%20not%20understand%20the%20two%20while%20loop
     */
    class Solution {
        public int shortestSubarray(int[] nums, int k) {
            int N = nums.length, res = N + 1;

            // 1.prefix sum
            long[] ps = new long[N + 1];   // use long instead of int
            for (int i = 1; i < N + 1; i++) ps[i] = ps[i - 1] + nums[i - 1];
            //System.out.println("prefix sum: "+ Arrays.toString(ps));

            // 2.find shortest subarray by going through prefix sum
            // goal: ps[j] - ps[i] >= k && (j - i) is shortest
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < N + 1; i++) {
                // poll first
                while (!q.isEmpty() && ps[i] - ps[q.peekFirst()] >= k)
                    res = Math.min(res, i - q.pollFirst());
                // poll last
                while (!q.isEmpty() && ps[i] <= ps[q.peekLast()])
                    q.pollLast();
                // nums[i] enter queue
                q.offerLast(i);
            }
            return res <= N ? res : -1;
        }
    }




}
