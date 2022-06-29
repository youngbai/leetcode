package edu.neu.leetcode.day8_DFS;

public class LC79_Word_Search {

    /*
    Thinking:
    - backtracking

    Time Complexity: O(N * 3^L) where N is the number of cells in the board and L is the length of the word to be matched.
    - For the backtracking function, initially we could have at most 4 directions to explore, but further
      the choices are reduced into 3 (since we won't go back to where we come from). As a result, the execution
      trace after the first step could be visualized as a 3-ary tree, each of the branches represent a potential
      exploration in the corresponding direction. Therefore, in the worst case, the total number of invocation
      would be the number of nodes in a full 3-nary tree, which is about 3^L
    - We iterate through the board for backtracking, i.e. there could be N times invocation for the
      backtracking function in the worst case.
    - As a result, overall the time complexity of the algorithm would be O(N * 3^L)

    Space Complexity: O(L) where L is the length of the word to be matched.
    - The main consumption of the memory lies in the recursion call of the backtracking function. The maximum length
      of the call stack would be the length of the word. Therefore, the space complexity of the algorithm is O(L)
     */
    class Solution1_Backtracking {
        char[][] board;
        int M;
        int N;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        public boolean exist(char[][] board, String word) {
            this.board = board;
            this.M = board.length;
            this.N = board[0].length;

            for (int i = 0; i < M; i++)
                for (int j = 0; j < N; j++)
                    if (dfs(i, j, word, 0))
                        return true;
            return false;
        }

        private boolean dfs(int i, int j, String word, int index) {
            // base case
            if (index >= word.length()) return true;

            // out of boundary, not equals, return false
            if (i < 0 || i >= M || j < 0 || j >= N || word.charAt(index) != board[i][j]) return false;

            // try 4 directions, # mark as visited
            boolean ret = false;
            board[i][j] = '#';
            for (int[] dir : dirs) {
                ret = dfs(i + dir[0], j + dir[1], word, index + 1);
                if (ret) break;
            }

            // recover
            board[i][j] = word.charAt(index);
            return ret;
        }
    }

}
