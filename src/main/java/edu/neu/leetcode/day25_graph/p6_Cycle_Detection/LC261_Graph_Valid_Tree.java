package edu.neu.leetcode.day25_graph.p6_Cycle_Detection;

import java.util.*;

public class LC261_Graph_Valid_Tree {

    /*
    Thinking:
    - DSU

    Time:  O(E)
    Space: O(V)
     */
    class Solution1_DSU {
        public boolean validTree(int n, int[][] edges) {
            DSU dsu = new DSU(n);
            for (int[] edge : edges) {      // O(E)
                int a = edge[0], b = edge[1];
                if (dsu.find(a) == dsu.find(b)) return false;   // we found a cycle, return false
                dsu.union(a, b);
            }

            // corner case: if there are isolated islands
            return edges.length == n - 1;
        }

        class DSU {
            int[] parent;
            int[] rank;

            public DSU(int n) {
                parent = new int[n];
                for (int i = 0; i < n; i++) parent[i] = i;
                rank = new int[n];
                Arrays.fill(rank, 1);
            }

            public int find(int x) {
                if (x != parent[x]) return find(parent[x]);
                return parent[x];
            }

            public void union(int x, int y) {
                int rootX = find(x), rootY = find(y);
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootX] = rootY;
                    rank[rootY]++;
                }
            }
        }
    }

    /*
    Thinking:
    - DFS + parent

    Time:  O(E)
    Space: O(E+V)
     */
    class Solution2_DFS_Parent {
        Map<Integer, List<Integer>> G = new HashMap<>();
        public boolean validTree(int n, int[][] edges) {
            // build graph
            for (int[] edge : edges) {
                int a = edge[0], b = edge[1];
                G.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
                G.computeIfAbsent(b, x -> new ArrayList<>()).add(a);
            }

            // DFS + parent
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    if (dfs(i, -1, visited)) return false;
                }
            }

            // corner case: isolated islands
            return edges.length == n - 1;
        }


        private boolean dfs(int cur, int parent, boolean[] visited) {
            // mark cur visited
            visited[cur] = true;

            // go through all neighbors except parent
            // if nei is visited & nei != parent, found a cycle
            // if nei is visited & nei == parent, stop dfs
            // if nei is not visited, dfs(nei)
            // after go through all nei, return false;
            for (int nei : G.getOrDefault(cur, new ArrayList<>())) {
                if (visited[nei]) {
                    if (nei != parent) return true;
                } else {
                    if (dfs(nei, cur, visited)) return true;
                }
            }
            return false;
        }
    }

}
