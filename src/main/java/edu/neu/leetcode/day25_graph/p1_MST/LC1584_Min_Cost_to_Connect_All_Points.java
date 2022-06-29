package edu.neu.leetcode.day25_graph.p1_MST;

import java.util.*;

public class LC1584_Min_Cost_to_Connect_All_Points {

    /*
     - Prim's (Optimized)
     - Dense Graph

     Time: O(V^2)
     Space: O(V^2), int[][] M
     */
    class Solution1_Optimized_Prim {
        public int minCostConnectPoints(int[][] points) {
            int n = points.length;
            // build graph with adjacent matrix
            int[][] M = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    M[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);

            // Prim
            boolean[] visited = new boolean[n];
            int[] distance = new int[n];    // distance[i] represents the distance from i to connected point set
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[0] = 0;
            for (int i = 0; i < n; i++) {       // O(n)
                // find the closed point from unvisited points
                int closedPoint = -1;
                for (int j = 0; j < n; j++)     // O(n)
                    if (!visited[j] && (closedPoint == -1 || distance[j] < distance[closedPoint])) closedPoint = j;
                // mark closedPoint visited
                visited[closedPoint] = true;
                // update distance of unvisited points, because the newly added point might give us a closer distance
                for (int j = 0; j < n; j++)
                    // if the distance from closedPoint to j is shorter than previous answer, then update
                    if (!visited[j] && M[closedPoint][j] < distance[j]) distance[j] = M[closedPoint][j];
            }

            // System.out.println(Arrays.toString(distance));
            return Arrays.stream(distance).sum();
        }
    }



    /*
    - Prim MST
    - PQ
    - worse than Solution1_Optimized_Prim, because this is dense graph

    Time: O(V^2 logV)
    Space: O(V^2), PQ
     */
    class Solution2_Prim_PQ {
        public int minCostConnectPoints(int[][] points) {
            int n = points.length;
            int[][] M = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    M[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);

            // Prim(PQ)
            int res = 0;
            Set<Integer> visited = new HashSet<>();
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);    // {y,cost}
            pq.offer(new int[]{0, 0});  // start from point 0
            while (!pq.isEmpty()) {     // walk through each edge
                int[] cur = pq.poll();
                int y = cur[0], cost = cur[1];
                if (visited.add(y)) {
                    res += cost;
                    for (int i = 0; i < n; i++) {   // check y's neighbors
                        if (!visited.contains(i)) pq.offer(new int[]{i, M[y][i]});
                    }
                }
            }
            return res;
        }
    }


    /*
     - Kruskal MST
     - DSU
     - Dense Graph speed up: only need to find n-1 valid edges (MST)

     Time:  O(ElogE), edges.sort()
     Space: O(E), List<int[]> edges, DSU
     */
    class Solution3_Kruskal_DSU {
        public int minCostConnectPoints(int[][] points) {
            int n = points.length;
            // find all edges
            List<int[]> edges = new ArrayList<>();  // [(x,y,cost)]
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    edges.add(new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])});

            // sort all edges
            edges.sort((a, b) -> a[2] - b[2]);

            // walk through all edges, select edge that does not create a cycle until we get n-1 valid edges
            int res = 0, count = 0;
            DSU dsu = new DSU(n);
            for (int[] edge : edges) {
                int x = edge[0], y = edge[1], cost = edge[2];
                if (dsu.find(x) == dsu.find(y)) continue;
                dsu.union(x, y);
                res += cost;
                count++;
                if (count == n - 1) break;  // we get n-1 valid edges which form a MST
            }
            return res;
        }
    }

    class DSU {
        int[] parent;
        int[] rank;     // height of tree

        public DSU (int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
            Arrays.fill(rank, 1);
        }

        public int find(int x) {
            if (parent[x] != x) return find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return;
            if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
            else if (rank[rootX] > rank[rootY]) parent[rootY] = rootX;
            else {
                parent[rootX] = rootY;
                rank[rootY]++;
            }
        }
    }



}
