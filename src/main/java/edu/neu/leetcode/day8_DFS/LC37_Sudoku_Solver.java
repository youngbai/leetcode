package edu.neu.leetcode.day8_DFS;

public class LC37_Sudoku_Solver {

    /*
    Thinking:
    - numerical form -> Graph traversal (BFS or DFS)
    - Backtracking, DFS
    - when DFS, we need feedback, so solve return boolean

     */
    class Solution1 {
        public void solveSudoku(char[][] board) {
            solve(board);
        }

        private boolean solve(char[][] board) {
            for (int i = 0; i < board.length; i++)
                for (int j = 0; j < board[0].length; j++)
                    if (board[i][j] == '.') {   // empty cell
                        for (char c = '1'; c <= '9'; c++) { // Try 1 through 9
                            if (isValid(board, i, j, c)) {
                                board[i][j] = c;    // put c in this cell
                                // keep DFS, if solve() return true, means we find the solution,
                                // then return true to its parent recursion call
                                if (solve(board)) return true;
                                else board[i][j] = '.'; // otherwise, backtrack
                            }
                        }
                        return false;   // have tried 1 to 9, but all failed, return false
                    }
            return true; // when we find the solution, last recursive call will reach here
        }

        private boolean isValid(char[][] board, int i, int j, char c) {
            for (int row = 0; row < board.length; row++)    // check every row
                if (board[row][j] == c) return false;
            for (int col = 0; col < board[0].length; col++) // check every column
                if (board[i][col] == c) return false;
            for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)   // check 3 * 3 sub-boxes
                for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
                    if (board[row][col] == c) return false;
            return true;
        }
    }

}
