package edu.neu.leetcode.day23_dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Google_MaxReward {

    /*
    Thinking:
    - DP

    Time: O(NK)
    Space: O(NK)
     */
    class Solution1_DP_BottomUp {

        Map<String, Integer> memo;

        public int maxReward(int[] A, int K) {
            memo = new HashMap<>();
            return findMax(A, 0, K);
        }

        private int findMax(int[] A, int i, int k) {
            // base case
            if (i == A.length) return 0;

            String key = i + ":" + k;
            if (memo.containsKey(key)) return memo.get(key);

            int r = 0;
            if (k > 0) {
                int r1 = A[i] + findMax(A, i + 1, k - 1);   // work
                int r2 = findMax(A, i + 1, k + 1);          // don't work
                r = Math.max(r1, r2);
            } else {
                r = findMax(A, i + 1, k + 1);               // don't work, coz k == 0
            }

            // memorize the result
            memo.put(key, r);
            return r;
        }
    }


    @Test
    public void test1() {
        int[] A = {4, 8, 1, 10, 2};
        int K = 2;

        Solution1_DP_BottomUp s = new Solution1_DP_BottomUp();
        assertEquals(s.maxReward(A, K), 22);


        A = new int[]{4, 8, 1, 10, 5};
        assertEquals(s.maxReward(A, K), 23);
    }


    /*
    Thinking:
    - Greedy
    - PQ

    Time: O(NlogN)
    Space: O()
     */
    class Solution2_PQ {

        public int maxReward(int[] A, int K) {
            if (K >= A.length) {
                return Arrays.stream(A).sum();
            }

            // PQ(reward, reward)
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < A.length; i++) {
                if (i < K) {
                    pq.offer(A[i]);
                } else {
                    if (!pq.isEmpty() && A[i] > pq.peek()) {
                        pq.poll();
                        pq.offer(A[i]);
                    }
                    if (i < A.length - 1) {
                        i++;
                        pq.offer(A[i]);
                    }
                }
            }

            int res = 0;
            for (int n : pq) res += n;
            return res;
        }
    }

    @Test
    public void test2() {
        int[] A = {4, 8, 1, 10, 2};
        int K = 2;

        Solution2_PQ s = new Solution2_PQ();
        assertEquals(s.maxReward(A, K), 22);


        A = new int[]{4, 8, 1, 10, 5};
        assertEquals(s.maxReward(A, K), 23);
    }

}
