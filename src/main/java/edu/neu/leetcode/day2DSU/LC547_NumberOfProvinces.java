package edu.neu.leetcode.day2DSU;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LC547_NumberOfProvinces {

    /*
    DSU
    Thinking:
    Disjoint provinces -> Disjoint Set

    Optimization:
    1.for i=0 to M:
        for j=i+1 to M:
      The matrix is symmetric, isConnected[i][j] equals isConnected[j][i].
      We don't have to do the same thing twice,
      only need to consider about upper triangular matrix.

     Time: O(V^2/2), V: vertices
     */
    class Solution1_DSU {
        public int findCircleNum(int[][] isConnected) {
            int M = isConnected.length;
            DSU dsu = new DSU(M);

            int count = M;
            for (int i = 0; i < M; i++) {
                for (int j = i + 1; j < M; j++) {
                    if (isConnected[i][j] == 1) {   // i,j connected
                        int p1 = dsu.find(i);
                        int p2 = dsu.find(j);
                        if (p1 != p2) {
                            dsu.union(i, j);
                            count--;
                        }
                    }
                }
            }
            return count;
        }

        class DSU {
            int[] parent;

            public DSU(int N) {
                parent = new int[N];
                for (int i = 0; i < N; i++) parent[i] = i;
            }

            public int find(int x) {
                if (parent[x] != x) parent[x] = find(parent[x]);
                return parent[x];
            }

            public void union(int x, int y) {
                parent[find(x)] = find(y);
            }
        }
    }

    /*
    DFS
    Thinking:
    Walk through each vertex, use DFS to find all its neighbors and set them visited,
    then we got one province, count++.
    If current vertex is visited, it means it already belong to one province, then ignore it.
    After walking through all vertices, we got all provinces.
    result = count

    Time: O(V^2), because it is an adjacency matrix,
    it walks through every vertices in this matrix

    If graph uses adjacency list, Time is O(V+2E), because it walks through each vertices,
    and access each edge twice.
    https://www.interviewbit.com/tutorial/depth-first-search/
     */
    class Solution2_DFS_Recursive {
        public int findCircleNum(int[][] M) {
            boolean[] visited = new boolean[M.length];
            int count = 0;
            for (int i = 0; i < M.length; i++) {
                // if i is visited, it means it already belonged to a province
                // if i is NOT visited, it means it is a new province
                if (!visited[i]) {
                    dfs(M, visited, i);
                    count++;
                }
            }
            return count;
        }

        public void dfs(int[][] M, boolean[] visited, int person) {
            for (int other = 0; other < M.length; other++) {
                if (M[person][other] == 1 && !visited[other]) {
                    // we found an unvisited person in the current friend cycle
                    visited[other] = true;
                    dfs(M, visited, other); // start DFS on this new found person
                }
            }
        }
    }

    class Solution2_DFS_Iterative {
        public int findCircleNum(int[][] M) {
            boolean[] visited = new boolean[M.length];
            Deque<Integer> stack = new ArrayDeque<>();

            int count = 0;
            for (int i = 0; i < M.length; i++) {
                // if i is visited, it means it already belonged to a province
                // if i is NOT visited, it means it is a new province
                if (!visited[i]) {
                    count++;
                    visited[i] = true;
                    stack.push(i);

                    while (!stack.isEmpty()) {
                        int v = stack.pop();
                        for (int neighbor = 0; neighbor < M[v].length; neighbor++) {
                            if (M[v][neighbor] == 1 && !visited[neighbor]) {
                                visited[neighbor] = true;
                                stack.push(neighbor);
                            }
                        }
                    }
                }
            }
            return count;
        }
    }

    /*
    BFS:
    Thinking:
    Walk through each vertex, use BFS to find all its neighbors and set them visited,
    then we got one province, count++.
    If current vertex is visited, it means it already belong to one province, then ignore it.
    After walking through all vertices, we got all provinces.
    result = count

    Time: O(V^2), because it is an adjacency matrix,
    it walks through every vertices in this matrix

    If graph uses adjacency list, Time is O(V+2E), because it walks through ea ch vertices,
    and access each edge twice.
    https://www.interviewbit.com/tutorial/breadth-first-search/
     */
    class Solution3_BFS_Recursive {
        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();

            int count = 0;
            for (int i = 0; i < n; i++) {
                // if i is visited, it means it already belonged to a province
                // if i is NOT visited, it means it is a new province
                if (!visited[i]) {
                    count++;
                    visited[i] = true;
                    queue.offer(i);
                    bfs(isConnected, visited, queue);
                }
            }
            return count;
        }

        public void bfs(int[][] isConnected, boolean[] visited, Queue<Integer> queue) {
            if (queue.isEmpty()) return;
            int v = queue.poll();
            for (int neighbor = 0; neighbor < isConnected[v].length; neighbor++) {
                if (isConnected[v][neighbor] == 1 && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
            bfs(isConnected, visited, queue);
        }
    }

    class Solution3_BFS_Iterative {
        public int findCircleNum(int[][] isConnected) {
            boolean[] visited = new boolean[isConnected.length];
            Queue<Integer> queue = new LinkedList<>();

            int count = 0;
            for (int i = 0; i < isConnected.length; i++) {
                // if i is visited, it means it already belonged to a province
                // if i is NOT visited, it means it is a new province
                if (!visited[i]) {
                    count++;
                    visited[i] = true;
                    queue.offer(i);

                    while (!queue.isEmpty()) {
                        int v = queue.poll();
                        for (int neighbor = 0; neighbor < isConnected[v].length; neighbor++) {
                            if (isConnected[v][neighbor] == 1 && !visited[neighbor]) {
                                visited[neighbor] = true;
                                queue.offer(neighbor);
                            }
                        }
                    }
                }
            }
            return count;
        }
    }
}
