package edu.neu.leetcode.day25_graph.p1_MST;

import java.util.*;

public class LC1135_Connecting_Cities_With_Minimum_Cost {

    /*
    - Prim MST
    - adjacent list

    Time:  O(ElogV)
        - O(E), walk through each edge
        - O(logV), for each vertex, enter pq only once, and exit pq only once
    Space: O(E+V)
        - Map graph O(E)
        - Set visited O(V)
     */
    class Solution1_Prim_MST {
        public int minimumCost(int n, int[][] connections) {
            // build graph
            Map<Integer, List<int[]>> graph = new HashMap<>(); // {x: [y1,c1], [y2,c2]}
            for (int[] edge : connections) {
                int x = edge[0], y = edge[1], cost= edge[2];
                graph.computeIfAbsent(x, (k) -> new ArrayList<>()).add(new int[]{y, cost});
                graph.computeIfAbsent(y, (k) -> new ArrayList<>()).add(new int[]{x, cost});
            }

            // build MST(Prim's Minimum Spanning Tree)
            int costs = 0;
            Set<Integer> visited = new HashSet<>();
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);   // [x,y,cost]
            pq.offer(new int[]{1, 1, 0});   // start from point 1

            while (!pq.isEmpty()) {                     // O(E)
                int[] cur = pq.poll();                  // O(logV)
                int x = cur[0], y = cur[1], cost = cur[2];
                if (visited.add(y)) {
                    costs += cost;
                    for (int[] nei : graph.get(y)) {
                        // if condition guarantees that nei[0] always is unvisited vertex
                        if (!visited.contains(nei[0])) pq.offer(new int[]{y, nei[0], nei[1]});
                    }
                }
            }

            return visited.size() == n ? costs : -1;
        }
    }


    /*
     - Kruskal MST

     Time:  O(ElogE), Arrays.sort()
     Space: O(V), DSU
     */
    class Solution2_Kruskal_DSU {
        public int minimumCost(int n, int[][] connections) {
            // sort edges
            Arrays.sort(connections, (a, b) -> a[2] - b[2]);    // O(ElogE)

            // walk through each edge, select valid edges
            int res = 0;
            DSU dsu = new DSU(n + 1);
            for (int[] edge : connections) {                    // O(E)
                int x = edge[0], y = edge[1], cost = edge[2];
                if (dsu.find(x) == dsu.find(y)) continue;
                dsu.union(x, y);
                res += cost;
                n--;
            }
            return n == 1? res : -1;
        }
    }

    class DSU {
        int[] parent;
        int[] rank;

        public DSU(int n) {
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
