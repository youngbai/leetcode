package edu.neu.leetcode.day23_dp;

import java.util.Arrays;

public class LC72_Edit_Distance {

    /*
    Thinking:
    - recursive

    Algo:
    if word1 is empty
        return len(word2)

    if word2 is empty
        return len(word1)

    if w1(i-1) == w2(j-1)
        dp(i,j) = dp(i-1, j-1)
    else
        replace dp(i,j) = dp(i-1, j-1) + 1
        delete  dp(i,j) = dp(i-1, j)   + 1
        insert  dp(i,j) = dp(i, j-1)   + 1
        dp(i,j) = min(replace, delete, insert)

    Problem:
    - too many repeated calculations
    md("horse", "hello")
        md("orse", "ello")
            md("orse", "llo")
                md("orse", "lo")
                md("rse", "llo") <-
                md("rse", "lo")
            md("rse", "ello")
                md("rse", "llo") <-
                md("se", "ello")
                md("se", "llo") <<-
            md("rse", "llo")
                md("rse", "llo") <-
                md("se", "llo") <<-
                md("se", "lo")
    Optimization
    - DP (Bottom-up or Top-down)

    Time:  O(3^max_length_of(word1, word2)), it is a ternary tree, so # of node is 3^max_length_of(word1, word2)
    Space: O(max length of (word1, word2)), memory stack, depth of the recursion
    */
    class Solution1_Recursion {
        public int minDistance(String word1, String word2) {
            if (word1.equals("")) return word2.length();
            if (word2.equals("")) return word1.length();

            if (word1.charAt(0) == word2.charAt(0))
                return minDistance(word1.substring(1), word2.substring(1));
            else {
                int replace = minDistance(word1.substring(1), word2.substring(1));
                int delete = minDistance(word1.substring(1), word2);
                int insert = minDistance(word1, word2.substring(1));
                return 1 + Math.min(Math.min(replace, delete), insert);
            }
        }
    }


    /*
    Thinking:
    - DP, top-down
    - int[i][j] memo: i: index of word1, j: index of word2

    Time:  O(M*N), go through each cell in memo
    Space: O(M*N), M=len(word1), N=len(word2)
     */
    class Solution2_DP_Top_Down {
        public int minDistance(String word1, String word2) {
            // init memo with -1
            int[][] memo = new int[word1.length()][word2.length()];
            Arrays.stream(memo).forEach(a -> Arrays.fill(a, -1));
            return minDist(word1, word2, 0, 0, memo);
        }

        private int minDist(String w1, String w2, int i, int j, int[][] memo) {
            // base case
            if (i == w1.length()) return w2.length() - j;
            if (j == w2.length()) return w1.length() - i;

            // hit memo success
            if (memo[i][j] != -1) return memo[i][j];

            // hit memo fail
            int res = 0;
            if (w1.charAt(i) == w2.charAt(j))
                res = minDist(w1, w2, i + 1, j + 1, memo);
            else {
                int replace = minDist(w1, w2, i + 1, j + 1, memo) + 1;
                int delete = minDist(w1, w2, i + 1, j, memo) + 1;
                int insert = minDist(w1, w2, i, j + 1, memo) + 1;
                res = Math.min(Math.min(replace, delete), insert);
            }
            memo[i][j] = res;
            return res;
        }
    }


    /*
    Thinking:
    - DP, bottom-up
    - int[i][j] dp: i: ith char in word1, j: jth char in word2

    Time:  O(M*N), go through each cell in memo
    Space: O(M*N), M=len(word1), N=len(word2)
     */
    class Solution3_DP_Bottom_Up {
        public int minDistance(String word1, String word2) {
            int M = word1.length(), N = word2.length();
            int[][] dp = new int[M + 1][N + 1];

            // base case
            for (int i = 0; i < dp.length; i++) dp[i][0] = i;
            for (int j = 0; j < dp[0].length; j++) dp[0][j] = j;

            // bottom up
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1))
                        dp[i][j] = dp[i - 1][j - 1];
                    else {
                        int replace = dp[i - 1][j - 1] + 1;
                        int delete = dp[i - 1][j] + 1;
                        int insert = dp[i][j - 1] + 1;
                        dp[i][j] = Math.min(Math.min(replace, delete), insert);
                    }
                }
            }
            return dp[M][N];
        }
    }


}
