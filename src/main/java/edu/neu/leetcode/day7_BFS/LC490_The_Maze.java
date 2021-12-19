package edu.neu.leetcode.day7_BFS;

import java.util.LinkedList;
import java.util.Queue;

public class LC490_The_Maze {

    /*
    Thinking:
    - BFS, try 4 direction
    - BFS Data structure
        - Queue
        - visited: set or boolean[][]

    Algo:
    boolean[][] visited
    Q = Queue()
    Q.offer(start), and mark start visited, visited[start] = true
    while Q is not empty:
        cur = Q.poll()
        if cur is destination: return true
        for (dir in dirs):
            start from cur, go dir until hit the wall, get new position (x,y)
            if (x,y) not visited:
                Q.offer([x,y])
                mark (x,y) visited, visited[x][y] = true
    return false if Q is empty, which means we have tried everything we can

    m - the number of rows of the maze.
    n - the number of columns of the maze.
    Time:  O(mn). Complete traversal of maze will be done in the worst case.
    Space: O(mn). visited array of size m*n is used and queue size can grow upto m*n in worst case.
     */
    class Solution1 {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        public boolean hasPath(int[][] maze, int[] start, int[] destination) {
            boolean[][] visited = new boolean[maze.length][maze[0].length];
            Queue<int[]> q = new LinkedList<>();
            q.offer(start);
            visited[start[0]][start[1]] = true;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                if (cur[0] == destination[0] && cur[1] == destination[1]) return true;
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                    }
                    // take a step back, because right now (x,y) is invalid and thus quit the while loop
                    x -= dir[0];
                    y -= dir[1];
                    if (!visited[x][y]) {
                        q.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            return false;
        }
    }

    /*
    Thinking:
    - DFS, try 4 direction

    Algo:
    dfs(maze, start, destination, visited):
        if start is visited: return false
        if start is destination: return true
        mark start is visited
        for (dir : dirs):
            start from start, go dir until hit the wall, get new position (x,y)
            if dfs(maze, (x,y), destination, visited): return true
        return false if all direction fail

    m - the number of rows of the maze.
    n - the number of columns of the maze.
    Time:  O(mn). Complete traversal of maze will be done in the worst case.
    Space: O(mn). visited array of size m*n is used.
     */
    class Solution2 {

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        public boolean hasPath(int[][] maze, int[] start, int[] destination) {
            boolean[][] visited = new boolean[maze.length][maze[0].length];
            return dfs(maze, start, destination, visited);
        }

        public boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
            if (visited[start[0]][start[1]]) return false;
            if (start[0] == destination[0] && start[1] == destination[1]) return true;
            visited[start[0]][start[1]] = true;
            for (int[] dir : dirs) {
                int x = start[0] + dir[0], y = start[1] + dir[1];
                while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                // take a step back, because right now (x,y) is invalid and thus quit the while loop
                x -= dir[0];
                y -= dir[1];
                if (dfs(maze, new int[]{x, y}, destination, visited)) return true;
            }
            return false;   // all direction fail
        }
    }

}
