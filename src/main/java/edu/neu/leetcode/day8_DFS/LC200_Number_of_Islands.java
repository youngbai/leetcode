package edu.neu.leetcode.day8_DFS;

public class LC200_Number_of_Islands {

    /*
    Thinking:
    - DFS

    Time:  O(N), go through each element once
    Space: Worst-O(N), memory stack, worst when all 1, dfs to all island
     */
    class Solution1_DFS {
        public int numIslands(char[][] grid) {
            int num = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        num++;
                        dfs(grid, i, j);
                    }
                }
            }
            return num;
        }

        private void dfs(char[][] grid, int i, int j) {
            grid[i][j] = '0'; // submerge this island, which means it is visited
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') continue;
                dfs(grid, x, y);
            }
        }
    }
}
