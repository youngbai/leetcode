package edu.neu.leetcode.day7_BFS;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Yahoo_Matrix_Min_Distance_From_Start_To_Destination {

    /*
    Thinking:
    - BFS or DFS
    - But, BFS is better DFS, because DFS has to try all possible path, but BFS does not have to.
      When the first time that we get the destination with BFS, we don't need to try the remaining possible path.
    - Original problem -> Graph layer traversal problem
      - layer traversal because the edge of the Graph is 1
      - once we get the destination, we get the min distance to start point

    Time: O(N)
    Space: O(N)
     */
    class Solution1_BFS {

        public int minDistance(int[][] matrix, int[] start, int[] dest) {
            int M = matrix.length, N = matrix[0].length;
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
            Queue<int[]> q = new LinkedList<>();

            // start from start point
            matrix[start[0]][start[1]] = 1; // mark visited
            q.offer(start);

            int step = 1;
            while (!q.isEmpty()) {
                // layer traversal
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    if (cur[0] == dest[0] && cur[1] == dest[1]) return step;

                    // try 8 directions and add unvisited node into q
                    for (int[] dir : dirs) {
                        int x = cur[0] + dir[0], y = cur[1] + dir[1];
                        if (x >= 0 && x < M && y >= 0 && y < N && matrix[x][y] == 0) {
                            matrix[x][y] = 1;   // mark visited
                            q.offer(new int[]{x, y});
                        }
                    }
                }
                step++;
            }
            return -1;
        }
    }


    @Test
    public void test1() {
        int[][] matrix = {
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };

        int M = matrix.length - 1, N = matrix[0].length;
        int[] start = {0, 0};
        int[] dest = {M - 1, N - 1};

        Solution1_BFS s = new Solution1_BFS();
        assertEquals(s.minDistance(matrix, start, dest), 6);
    }



    @Test
    public void test2() {
        int[][] matrix = {
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };

        int M = matrix.length - 1, N = matrix[0].length;
        int[] start = {0, 0};
        int[] dest = {M - 1, N - 1};

        Solution1_BFS s = new Solution1_BFS();
        assertEquals(s.minDistance(matrix, start, dest), 7);
    }




}
