package edu.neu.leetcode.day25_graph.p6_Cycle_Detection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.*;

public class CycleDetection {

    Map<Integer, List<Integer>> Graph = new HashMap<>();


    /*
    0-1-2-4
      |
      3
     */
    @Test
    public void test() {
        CycleDetection test = new CycleDetection();
        test.Graph.computeIfAbsent(0, x -> new ArrayList<>()).add(1);
        test.Graph.computeIfAbsent(3, x -> new ArrayList<>()).add(1);
        test.Graph.computeIfAbsent(1, x -> new ArrayList<>()).addAll(List.of(0, 2, 3));
        test.Graph.computeIfAbsent(2, x -> new ArrayList<>()).addAll(List.of(1, 4));
        test.Graph.computeIfAbsent(4, x -> new ArrayList<>()).add(2);
        assertFalse(test.isCyclicUndirectedGraph(5));
        assertFalse(test.isCyclicUndirectedGraph2(5));
    }


    // DFS + parent
    private boolean isCyclicUndirectedGraph(int n) {
        boolean[] visited = new boolean[n];

        // DFS each unvisited vertex
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                if (dfs(i, -1, visited))    // initially, parent node is -1
                    return true;
        }
        return false;
    }

    private boolean dfs(int cur, int parent, boolean[] visited) {
        // mark current node visited
        visited[cur] = true;

        // dfs all current node's neighbors
        for (int nei : Graph.getOrDefault(cur, new ArrayList<>())) {
            if (visited[nei]) {     // nei is visited
                if (nei != parent) return true; // if nei is not parent, means it did not go back, so we found a cycle
            } else {
                // nei is unvisited, keep dfs
                // if nei has a cycle, return true
                if (dfs(nei, cur, visited)) return true;
            }
        }

        // no cycle was found
        return false;
    }


    private boolean isCyclicUndirectedGraph2(int n) {
        // remove duplicate edges because it is an undirected graph
        // e.g. 0-1, 1-0 are the same edges
        // 1.sort them, we got 0-1, 0-1
        // 2.put them into a set to remove duplicate edges
        Set<List<Integer>> edges = new HashSet<>(); // [[0,1], [1,2], [2,3]]
        for (int i = 0; i < n; i++) {
            for (int nei : Graph.getOrDefault(i, new ArrayList<>())) {
                List<Integer> edge = new ArrayList<>(); // i->nei
                edge.add(i);
                edge.add(nei);
                Collections.sort(edge);
                edges.add(edge);
            }
        }

        // Detection cycle with DSU
        DSU dsu = new DSU(n);
        for (List<Integer> edge : edges) {
            int a = edge.get(0), b = edge.get(1);
            // initially, every vertex is not in the same unit.
            // if a and b are in the same unit, that means we found an another path connection a and b which is a cycle
            if (dsu.find(a) == dsu.find(b)) return true;
            dsu.union(a, b);
        }

        return false;
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
            return x;
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
