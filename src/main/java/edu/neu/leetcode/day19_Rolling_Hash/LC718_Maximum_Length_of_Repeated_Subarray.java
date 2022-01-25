package edu.neu.leetcode.day19_Rolling_Hash;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC718_Maximum_Length_of_Repeated_Subarray {


    /*
    Thinking:
    - Binary Search
    - Rolling Hash

    Time:  O((M+N) * log(min(M,N)))
    Space: O(M), HashSet
     */
    class Solution1_RollingHash {
        long MOD = (long)1e9 + 7, a = (long)1e6;
        public int findLength(int[] A, int[] B) {
            // Binary Search (log(min(M,N)))
            int N1 = A.length, N2 = B.length, res = 0;
            int left = 0, right = Math.min(N1, N2);
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (search(A, B, mid)) left = mid + 1;
                else right = mid - 1;
            }   // end: right|left
            return right;
        }

        private boolean search(int[] A, int[] B, int L) {   // O(M+N)
            long h1 = 0, h2 = 0, aL = 1;
            for (int i = 0; i < L; i++) {
                h1 = (h1 * a + A[i]) % MOD;
                h2 = (h2 * a + B[i]) % MOD;
                aL = (aL * a) % MOD;
            }
            Set<Long> seen = new HashSet<>(List.of(h1));
            for (int i = 1; i + L <= A.length; i++) {
                h1 = h1 * a;
                h1 = (h1 - A[i - 1] * aL % MOD + MOD) % MOD;
                h1 = (h1 + A[i + L - 1]) % MOD;
                seen.add(h1);
            }
            if (seen.contains(h2)) return true;
            for (int i = 1; i + L <= B.length; i++) {
                h2 = h2 * a;
                h2 = (h2 - B[i - 1] * aL % MOD + MOD) % MOD;
                h2 = (h2 + B[i + L - 1]) % MOD;
                if (seen.contains(h2)) return true;
            }
            return false;
        }
    }


    /*
    Thinking:
    - DP

    Algo:
    - base case
      dp(0,j)=0, dp(i,0)=0, where 0<=i<=M+1, 0<=j<=N+1, M=len(A), N=len(B)

    - transformation
      if A[i-1] == B[j-1], then dp(i,j) = dp(i-1,j-1) + 1
      if A[i-1] != B[j-1], then dp(i,j) = 0

    - answer
      answer = max(dp[][])


    Time:  O(M*N)
    Space: O(M*N)
     */
    class Solution2_DP {
        public int findLength(int[] nums1, int[] nums2) {
            int ans = 0, N1 = nums1.length, N2 = nums2.length;
            int[][] dp = new int[N1 + 1][N2 + 1];
            for (int i = 1; i < dp.length; i++) {           // O(M)
                for (int j = 1; j < dp[0].length; j++) {    // O(N)
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        ans = Math.max(ans, dp[i][j]);
                    }
                }
            }
            return ans;
        }
    }
}
