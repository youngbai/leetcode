package edu.neu.leetcode.day23_dp;

import java.util.HashMap;
import java.util.Map;

public class LC509_Fibonacci_Number {

    /*
    Thinking:
    - DP

    Example:
        f(5)
    /          \
    f(4)     f(3)
    /     \       /   \
    f(3) f(2)  f(2)  f(1)
    /    \          /\
    f(2)  f(1) f(1) f(0)
    /      \
    f(1) f(0)


    Time:  O(N), go through each number
    Space: Memo Array/HashTable O(n) + Call Stack O(n) = O(2n) = O(n)
     */
    class Solution1_DP_Recursion {
        public int fib(int n) {
            Map<Integer, Integer> mem = new HashMap<>();
            // base case
            mem.put(0, 0);
            mem.put(1, 1);
            return fib(n, mem);
        }

        private int fib(int n, Map<Integer, Integer> mem) {
            // hit mem success
            if (mem.containsKey(n)) return mem.get(n);

            // hit mem fail
            int x = fib(n - 1, mem) + fib(n - 2, mem);
            mem.put(n, x);
            return x;
        }
    }


    /*
    Time:  O(N)
    Space: O(N)
     */
    class Solution2_DP_Iteration {
        public int fib(int n) {
            if (n <= 1) return n;

            int[] A = new int[n + 1];
            A[0] = 0;
            A[1] = 1;

            for (int i = 2; i <= n; i++)
                A[i] = A[i - 1] + A[i - 2];

            return A[n];
        }

    }
}
