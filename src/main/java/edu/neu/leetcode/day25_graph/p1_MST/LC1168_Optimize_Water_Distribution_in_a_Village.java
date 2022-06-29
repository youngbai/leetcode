package edu.neu.leetcode.day25_graph.p1_MST;

import java.util.*;

public class LC1168_Optimize_Water_Distribution_in_a_Village {

    /*
    - Prim MST
    - adjacent list
    - add a virtual house connecting to every houses with edge of every houses' wells value

    Time: O(ElogE)
        - https://leetcode.com/problems/min-cost-to-connect-all-points/solution/#:~:text=Implementation-,Complexity%20Analysis,N2%2BN)%E2%89%88O(N2).,-Approach%203%3A%20Prim's
        - O(E), walk through each edge
        - O(logE), each edge poll(), offer() O(logE)
        - Worse case: E=V(V-1)/2, => O(V^2logV)
    Space: O(E+V+E)
        - Map graph O(E)
        - Set visited O(V)
        - PQ O(E)
     */
    class Solution1_Prim_MST {
        public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();    // {x: y->cost}

            // build virtual house 0
            for (int i = 1; i <= n; i++)
                graph.computeIfAbsent(0, k -> new HashMap<>()).put(i, wells[i - 1]);

            // build graph
            for (int[] edge : pipes) {
                int x = edge[0], y = edge[1], cost = edge[2];
                int costFromXToY = graph.computeIfAbsent(x, k -> new HashMap<>()).getOrDefault(y, Integer.MAX_VALUE);
                int costFromYToX = graph.computeIfAbsent(y, k -> new HashMap<>()).getOrDefault(x, Integer.MAX_VALUE);
                int mincost = Math.min(cost, Math.min(costFromXToY, costFromYToX));
                graph.get(x).put(y, mincost);
                graph.get(y).put(x, mincost);
            }
            //System.out.println(graph);

            // Prim's MST
            int res = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);    // {y,cost}
            Set<Integer> visited = new HashSet<>();
            pq.offer(new int[]{0, 0});      // start from point 0
            while (!pq.isEmpty()) {             // O(E)~O(V^2), walk through each edge
                int[] cur = pq.poll();          // O(logE)~log(V^2)~log(V), poll(), offer() PQ
                int y = cur[0], cost = cur[1];
                if (visited.add(y)) {
                    res += cost;
                    Map<Integer, Integer> neighbors = graph.getOrDefault(y, new HashMap<>());   // get y's neighbors
                    for (int nei : neighbors.keySet()) {
                        if (!visited.contains(nei)) pq.offer(new int[]{nei, neighbors.get(nei)});
                    }
                }
            }

            return res;
        }
    }

    /*
     - Kruskal MST
     - DSU
     - add virtual house and virtual edges

     Time:  O(ElogE), edges.sort()
     Space: O(E), List<int[]> edges, DSU
     */
    class Solution2_Kruskal_DSU {
        public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
            List<int[]> edges = new ArrayList<>();  // [(x,y,cost), ...]

            // add virtual house(index=0), and add virtual edges to edge list
            for (int i = 1; i <= n; i++) edges.add(new int[]{0, i, wells[i - 1]});

            // add real edges to edge list
            for (int[] edge : pipes) edges.add(edge);       // O(E)

            // sort all edges
            edges.sort((a, b) -> a[2] - b[2]);              // O(ElogE)

            // walk through all edges, select edge that does not create a cycle
            int res = 0;
            DSU dsu = new DSU(n + 1);
            for (int[] edge : edges) {                      // O(E)
                int x = edge[0], y = edge[1], cost = edge[2];
                if (dsu.find(x) == dsu.find(y)) continue;   // x,y are already connected
                dsu.union(x, y);
                res += cost;
            }

            return res;
        }
    }

    // Optimized by rank with path compression
    class DSU {
        int[] parent;
        int[] rank;

        public DSU(int N) {
            parent = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) parent[i] = i;
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
