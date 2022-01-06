package edu.neu.leetcode.day8_DFS;

import java.util.ArrayList;
import java.util.List;

public class LC51_N_Queens {

    /*
    Thinking:
    - Backtracking DFS
    - first put a Q in 1st row,
      then try to find valid position in 2nd row,
      then try to find valid position in 3nd row,
      ...
      until rowIdx == board.length

    - valid() Algo:
        - suppose two points: Q(i,j), candidate points (x,y)
        - we always try to find valid position in a new row,
          so it's impossible for Q and (x,y) in the same row,
          but they can be in the same column
        - for i = 0 to i < x    # Qs only exist in first x rows, rows(>=x) have not put any Qs
            for j = 0 to j < N  # traverse each column
        - same column if y == j
        - 45  degree line if x - i == y - j
        - 135 degree line if x - i == j - y
        - Mathematical Derivation
          (1) 45  degree formula: y = x + k
              so, i + k = j  for Q(i,j)
                  x + k = y  for (x,y) point
              =>  x - i = y - j
          (2) 135 degree formula: y = -x + k
              so, -i + k = j  for Q(i,j)
                  -x + k = y  for (x,y) point
              =>  x - i = j - y
        - Simplest way to check (i,j), (x,y) in a diagonal line
          |x-i| = |y-j|
     */
    class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            // init board with '.'
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    board[i][j] = '.';
            // DFS
            dfs(res, board, 0);
            return res;
        }

        private void dfs(List<List<String>>res, char[][] board, int rowIndex) {
            if (rowIndex == board.length) { // have traversed all row
                res.add(construct(board));
                return;
            }

            for (int j = 0; j < board[0].length; j++)   // traverse each column
                if (valid(board, rowIndex, j)) {        // if (rowIndex,j) is a valid point, dfs from it
                    board[rowIndex][j] = 'Q';
                    dfs(res, board, rowIndex + 1); // dfs in next row
                    board[rowIndex][j] = '.';
                }
        }

        private boolean valid(char[][] board, int x, int y) {
            for (int i = 0; i < x; i++) // Qs only exist in first x rows, rows(>=x) have not put any Qs
                for (int j = 0; j < board[0].length; j++)
                    if (board[i][j] == 'Q' && (y == j || x - i == j - y || x - i == y - j))
                        return false;
            return true;
        }

        private List<String> construct(char[][] board) {
            List<String> res = new ArrayList<>();
            for (char[] row : board)
                res.add(new String(row));
            return res;
        }
    }


}