package edu.neu.leetcode.day9_Binary_Search;

public class LC1292_Maximum_Side_Length_of_a_Square_with_Sum_Less_than_or_Equal_to_Threshold {

    /*
    Thinking:
    - key skill
      - DP
      - rangeSum (2D prefix sum)

    Intuition:
    - for each cell:
        start from current cell, try to find a square meeting the condition
        update answer
    - problem:
      - too much repeated calculation
      - e.g. square1, square2(include square1),
        so when we calculate square2 result, square1 is recalculated.
    - solution:
      - DP
      - rangeSum (2D prefix sum)
      - use dp to cached all the result, later we can get the rangeSum only within O(1)
    - DP, prefix formula:
      - mapping: dp(x,y) -- mat(x-1,y-1)
      - base: dp(0,y) = 0, dp(x,0) = 0
      - deduction: dp(x,y) = dp(x, y-1) + dp(x-1,y) - dp(x-1,y-1) + mat(x-1,y-1)
    - rangeSum(x1, y1, x2, y2) formula:
      = dp(x2, y2) - dp(x2, y1 - 1) - dp(x1 - 1, y2) + dp(x1 - 1, y1 - 1)

    Caution:
    - 2D array --- x,y coordinate
            0  1  2  3
            _  _  _  _ y
      0  |
      1  |
      2  |
      3  |
         x

    Caution:
    - k: length to add,  ans: size
    - k=0, ans=1
    - k=1, ans=2
    so k + 1 = ans

    Time : O(m*n + m*n*min(m,n)) ~ O(m*n*min(m,n)), Worst Time: O(n^3) when m = n
    - O(m*n): init dp[][]
    - O(m*n*min(m,n)): traverse each cell to find the square meeting the condition
    - Even Worst Time is O(n^3), with pruning operation, it can perform good enough
    Space: O(m*n) - dp[][]

    Ref:
    https://www.youtube.com/watch?v=SImXsBQQCEE
     */
    class Solution1_BruteForce {
        public int maxSideLength(int[][] mat, int threshold) {
            int m = mat.length, n = mat[0].length;

            // init dp[][]
            // O(m*n)
            int[][] dp = new int[m+1][n+1];
            for (int x = 1; x <= m; x++)
                for (int y = 1; y <= n; y++)
                    dp[x][y] = dp[x][y-1] + dp[x-1][y] - dp[x-1][y-1] + mat[x-1][y-1];

            // traverse each cell, try to find the square meeting the condition
            // O(m*n*min(m,n)), Worst Time: O(n^3), because three loop
            int ans = 0;
            for (int x = 1; x <= m; x++)
                for (int y = 1; y <= n; y++)
                    for (int k = 0; x + k <= m && y + k <= n; k++) {  // @Step1
                        if (rangeSum(dp, x, y, x + k, y + k) > threshold) break; // pruning
                        else ans = Math.max(ans, k + 1);
                    }
            return ans;
        }

        // calculate rangeSum from (x1,y1) to (x2,y2)
        // O(1), because dp already cached the results
        private int rangeSum(int[][]dp, int x1, int y1, int x2, int y2) {
            return dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
        }
    }

    /*
    Thinking:
    - key skill
      - DP
      - rangeSum (2D prefix sum)
      - Binary Search

    Intuition:
    - same idea with Solution1_BruteForce
    - but improve it with Binary Search in @Step1
    - @Step1 search the answer with linear operation, from k=0 to x+k<=m

    Time:  O(m*n*log(min(m,n)))
    Space: O(m*n)
     */
    class Solution2_BinarySearch {
        public int maxSideLength(int[][] mat, int threshold) {
            int m = mat.length, n = mat[0].length;
            int[][] dp = new int[m + 1][n + 1];
            for (int x = 1; x <= m; x++)
                for (int y = 1; y <= n; y++)
                    dp[x][y] = dp[x][y - 1] + dp[x - 1][y] - dp[x - 1][y - 1] + mat[x - 1][y - 1];

            int ans = 0;
            for (int x = 1; x <= m; x++)
                for (int y = 1; y <= n; y++) {
                    // binary search
                    // l,h,m is the length to add
                    int l = 0, h = Math.min(m - x, n - y);
                    while (l <= h) {
                        int mid = l + (h - l) / 2;
                        if (rangeSum(dp, x, y, x + mid, y + mid) > threshold) h = mid - 1;
                        else l = mid + 1;
                    } // h,l
                    ans = Math.max(ans, l);
                }
            return ans;
        }

        private int rangeSum(int[][] dp, int x1, int y1, int x2, int y2) {
            return dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
        }
    }


    /*
    Thinking:
    - key skill
      - DP
      - rangeSum (2D prefix sum)
      - Binary Search
      - Bounded Search: Start from current answer + 1, since smaller ones won't help.
        e.g. if current answer is 3, we don't need to search for 1, 2, 3. It just waste time.
        We can directly search for 4.

    Time:  O(m*n + min(m,n))
    Space: O(m*n)

    This trick makes the time complexity O(MN): enumerate length from res+1 found so far and break if checking fails.
    This trick helps avoid checking lengths that are not contributing to final answer. As the lengths we check are
    always increasing( from 1 to min(m,n)), we at most do the checking O(m*n+min(m,n)) times.
     */
    class Solution3_BoundarySearch {
        public int maxSideLength(int[][] mat, int threshold) {
            int m = mat.length, n = mat[0].length;
            int[][] dp = new int[m+1][n+1];
            for (int x = 1; x <= m; x++)
                for (int y = 1; y <= n; y++)
                    dp[x][y] = dp[x][y-1] + dp[x-1][y] - dp[x-1][y-1] + mat[x-1][y-1];

            int ans = 0;
            for (int x = 1; x <= m; x++)
                for (int y = 1; y <= n; y++) {
                    // binary search
                    // l,h,m is the length to add
                    int l = ans, h = Math.min(m - x, n - y);
                    if (l > h) continue;

                    // binary search
                    boolean found = false;
                    while (l <= h) {
                        int mid = l + (h - l) / 2;
                        if (rangeSum(dp, x, y, x + mid, y + mid) > threshold) h = mid - 1;
                        else {
                            found = true;
                            l = mid + 1;
                        }
                    }
                    if (found) ans = Math.max(ans, l);
                }
            return ans;
        }

        private int rangeSum(int[][] dp, int x1, int y1, int x2, int y2) {
            return dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
        }
    }






}
