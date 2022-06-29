package edu.neu.leetcode.day7_BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC505_The_Maze_II {

    /*
    Thinking:
    - BFS, try 4 direction
    - BFS DS:
        - Queue
        - int[][] distance = {MAX.VALUE}
    - if we wanna shortest distance, we have to traverse every possible route,
        then wen are able to find the shortest one
    - How do we traverse every possible route?
        - BFS, DFS

    Algo:
    int[][] distance = {MAX.VALUE}
    distance(start) = 0
    Q = Queue()
    Q.offer(start)
    while Q is not empty:
        cur = Q.poll()
        start from cur, try 4 directions until hit the wall, get new position (x,y) with `count` steps
        if (distance(cur) + count < distance(x,y)):
            # it means there is a better way to reach (x,y), which is from cur with current direction and `count` steps
            # then we update distance(x,y) with shorter distance
            # and explore further from (x,y) by offering (x,y) to the queue
            distance(x,y) = distance(cur) + count
            Q.offer([x,y])
    # after while loop, we reached all possible positions, and calculated their distance to start
    return distance(destination) == MAX? -1 : distance(destination)

    m - the number of rows of the maze.
    n - the number of columns of the maze.
    Time: O(m * n * max(m,n))
        - O(m * n) Complete traversal of maze will be done in the worst case.
        - O(max(m,n)) for every current node chosen, we can travel up to
            a maximum depth of max(m,n) in any direction.
    Space: O(mn)
        - O(mn) queue size can grow upto m∗n in the worst case.
        - O(mn) distance array of size m*nm∗n is used.
     */
    class Solution1_BFS {

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            // init distance array
            int[][] distance = new int[maze.length][maze[0].length];
            for (int[] row: distance) Arrays.fill(row, Integer.MAX_VALUE);

            // begin with start position
            distance[start[0]][start[1]] = 0;
            Queue<int[]> q = new LinkedList<>();    // (x,y) position
            q.offer(start);

            while (!q.isEmpty()) {
                // current position
                int[] cur = q.poll();
                // try 4 different directions
                for (int[] dir : dirs) {
                    int x = cur[0], y = cur[1], count = 0;
                    // keep going until hit the wall
                    while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                        count++;
                    }
                    // take a step back
                    x -= dir[0];
                    y -= dir[1];
                    count--;
                    // update distance of new position if current route is shorter than previous one
                    if (distance[cur[0]][cur[1]] + count < distance[x][y]) {
                        distance[x][y] = distance[cur[0]][cur[1]] + count;
                        q.offer(new int[]{x, y});
                    }
                }
            }
            return distance[destination[0]][destination[1]] == Integer.MAX_VALUE? -1 : distance[destination[0]][destination[1]];
        }
    }

    /*
    Thinking:
    - DFS, try 4 direction

    Algo:
    def shortestDistance(maze, start, destination):
        int[][] distance = {MAX.VALUE}
        distance(start) = 0
        dfs(maze, start, distance)
        return distance(destination) == MAX? -1 : distance(destination)

    def dfs(maze, start, distance):
        for dir in dirs:
            start from start, go dir until hit the wall, get new position (x,y) with `count` steps
            if distance(start) + count < distance([x,y]):
                distance([x,y]) = distance(start) + count
                dfs(maze, (x,y), distance)

    m - the number of rows of the maze.
    n - the number of columns of the maze.
    Time: O(m * n * max(m,n))
        - O(m * n) Complete traversal of maze will be done in the worst case.
        - O(max(m,n)) for every current node chosen, we can travel up to
            a maximum depth of max(m,n) in any direction.
    Space: O(mn)
        - O(mn) distance array of size m*nm∗n is used.
     */
    class Solution2_DFS {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            // init distance array
            int[][] distance = new int[maze.length][maze[0].length];
            for (int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);
            distance[start[0]][start[1]] = 0;

            // begin with start position
            dfs(maze, start, distance);
            return distance[destination[0]][destination[1]] == Integer.MAX_VALUE? -1 : distance[destination[0]][destination[1]];
        }

        private void dfs(int[][] maze, int[] start, int[][] distance) {
            // try 4 different directions
            for (int[] dir : dirs) {
                int x = start[0], y = start[1], count = 0;
                // keep going until hit the wall
                while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                // take a step back
                x -= dir[0];
                y -= dir[1];
                count--;
                // update distance of new position if current route is shorter than previous one
                // keep dfs if a shorter path is found
                if (distance[start[0]][start[1]] + count < distance[x][y]) {
                    distance[x][y] = distance[start[0]][start[1]] + count;
                    dfs(maze, new int[]{x, y}, distance);
                }
            }
        }
    }

    /*
    Thinking:
    - use Dijkstra Algorithm to walk through all possible path, and compute the shorted distance from start vertex
    - but have to traverse every cell in the maze to find the vertex of min distance from unvisited vertex set.
      Optimization: use PQ

    Algo:
    main():
        # init distance, visited array
        distance(all vertices) = MAX.VALUE
        distance(start) = 0
        visited(all vertices) = false

        # dijkstra
        dijkstra()

    dijkstra(distance, visited):
        while (true):
            u = find a vertex of min distance from unvisited vertex set
            mark u visited
            for (neighbor : u.unvisited_neighbors()):
                if distance(u) + weight(u, neighbor) < distance(neighbor):
                    # find a shorter path, update distance(neighbor)
                    distance(neighbor) = distance(u) + weight(u, neighbor)

     */
    class Solution3_Dijkstra_No_PQ {
        public int shortestDistance(int[][] maze, int[] start, int[] dest) {
            int[][] distance = new int[maze.length][maze[0].length];
            for (int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);

            distance[start[0]][start[1]] = 0;
            boolean[][] visited = new boolean[maze.length][maze[0].length];

            dijkstra(maze, distance, visited);
            return distance[dest[0]][dest[1]] == Integer.MAX_VALUE? -1 : distance[dest[0]][dest[1]];
        }

        public int[] minDistance(int[][] maze, int[][] distance, boolean[][] visited) {
            int[] min = {-1,-1};
            int min_val = Integer.MAX_VALUE;
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance[0].length; j++) {
                    if (!visited[i][j] && distance[i][j] < min_val) {
                        min = new int[]{i, j};
                        min_val = distance[i][j];
                    }
                }
            }
            return min;
        }

        public void dijkstra(int[][] maze, int[][] distance, boolean[][] visited) {
            int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            while (true) {
                // vertex with min distance
                int[] cur = minDistance(maze, distance, visited);
                // no vertex found, which means that all reachable vertex has been visited, just return
                if (cur[0] == -1) break;
                // mark cur visited
                visited[cur[0]][cur[1]] = true;
                // update its neighbors' distance
                for (int[] dir : dirs) {
                    // start from cur, try 4 directions until hit the wall, get new position (x,y) with `count` steps
                    int x = cur[0], y = cur[1], count = 0;
                    while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                        count++;
                    }
                    // take a step back
                    x -= dir[0];
                    y -= dir[1];
                    count--;
                    if (distance[cur[0]][cur[1]] + count < distance[x][y])
                        distance[x][y] = distance[cur[0]][cur[1]] + count;
                }
            }
        }
    }

    /*
    Thinking:
    - use Dijkstra Algorithm to walk through all possible path, and compute the shorted distance from start
    - use PQ to easily find the nearest vertex that has min distance from unvisited vertex set

    Algo:
    main():
        # init distance
        distance(all vertices) = MAX.VALUE
        distance(start) = 0

        # dijkstra
        dijkstra()

    dijkstra(distance, visited):
        pq = PriorityQueue()
        pq.offer([start.x, start.y, 0])
        while pq is not empty:
            u = pq.poll()
            if distance(u) < u[2]: continue, u[2] is the distance of previous path
            u = find a vertex of min distance from unvisited vertex set
            for (neighbor : u.neighbors()):
                if distance(u) + weight(u, neighbor) < distance(neighbor):
                    # find a shorter path, update distance(neighbor)
                    distance(neighbor) = distance(u) + weight(u, neighbor)
                    pq.offer([neighbor.x, neighbor.y, distance(neighbor)])
     */
    class Solution4_Dijkstra_PQ {
        public int shortestDistance(int[][] maze, int[] start, int[] dest) {
            int[][] distance = new int[maze.length][maze[0].length];
            for (int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);

            distance[start[0]][start[1]] = 0;
            dijkstra(maze, start, distance);
            return distance[dest[0]][dest[1]] == Integer.MAX_VALUE? -1 : distance[dest[0]][dest[1]];
        }

        public void dijkstra(int[][] maze, int[] start, int[][] distance) {
            int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            pq.offer(new int[]{start[0], start[1], 0}); // x,y,cost
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                //if (distance[cur[0]][cur[1]] < cur[2]) continue;
                for (int[] dir : dirs) {
                    int x = cur[0], y = cur[1], count = 0;
                    while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                        count++;
                    }
                    x -= dir[0];
                    y -= dir[1];
                    count--;
                    if (distance[cur[0]][cur[1]] + count < distance[x][y]) {
                        distance[x][y] = distance[cur[0]][cur[1]] + count;
                        pq.offer(new int[]{x, y, distance[x][y]});
                    }
                }
            }
        }
    }

}
