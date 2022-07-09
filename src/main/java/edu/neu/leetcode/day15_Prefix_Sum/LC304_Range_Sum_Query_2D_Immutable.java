package edu.neu.leetcode.day15_Prefix_Sum;

public class LC304_Range_Sum_Query_2D_Immutable {

    /*
    Thinking:
    - 2D prefix sum
     */
    class NumMatrix {

        // sum(x,y) = sum of M(x-1,y-1)
        private int[][] sum;  // prefix sum

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            sum = new int[m + 1][n + 1];

            for (int i = 1; i < m + 1; i++)
                for (int j = 1; j < n + 1; j++) {
                    sum[i][j] = matrix[i-1][j-1] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
                    //System.out.printf("i=%d, j=%d, s=%d\n", i, j, sum[i][j]);
                }
        }


        // region_sum = sum(r2+1,c2+1) - sum(r1,c2+1) - sum(r2+1,c1) + sum(r1,c1)
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum[row2+1][col2+1] - sum[row1][col2+1] - sum[row2+1][col1] + sum[row1][col1];
        }
    }
}
