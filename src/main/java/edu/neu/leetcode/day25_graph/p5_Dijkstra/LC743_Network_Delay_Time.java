package edu.neu.leetcode.day25_graph.p5_Dijkstra;

import java.util.*;

public class LC743_Network_Delay_Time {

    /*
    Thinking: (Best)
    - Dijkstra
    - PQ

    Time: O(ElogV)
    - traverse each edge
    - poll each edge from PQ, about V times

    Space: O(E+V)
    - O(E), Map G
    - O(V), int[] distance, boolean[] visited
     */
    class Solution1_Dijkstra_PQ {
        public int networkDelayTime(int[][] times, int n, int k) {
            // build Graph
            Map<Integer, Map<Integer, Integer>> G = new HashMap<>();
            for (int[] t : times) {
                int u = t[0], v = t[1], w = t[2];
                G.computeIfAbsent(u, key -> new HashMap<>()).put(v, w);
            }

            // init distance, visited
            int[] distance = new int[n + 1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            boolean[] visited = new boolean[n + 1];

            // start from src
            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]); // node, distance to src
            q.offer(new int[]{k, 0});
            distance[k] = 0;

            int res = 0, count = 0;
            while (!q.isEmpty()) {      // O(E)
                // remove u from Q
                int[] cur = q.poll();   // O(logV)
                int u = cur[0], w = cur[1];
                if (visited[u]) continue;   // if current node is visited, skip
                visited[u] = true;
                count++;                // keep track of visited node count
                res = Math.max(res, w); // keep track of the longest path
                // res = Math.max(res, distance[u]);    // same as previous line

                // go through unvisited neighbors of u
                // update neighbors' distance if a shorter path is found
                // offer the shorter path to PQ
                Map<Integer, Integer> neighbors = G.getOrDefault(u, new HashMap<>());
                for (int nei : neighbors.keySet()) {
                    int uToNeiDistance = neighbors.get(nei);
                    if (!visited[nei] && distance[u] + uToNeiDistance < distance[nei]) { // if nei is unvisited & get shorter path from u
                        distance[nei] = distance[u] + uToNeiDistance; // update nei's distance
                        q.offer(new int[]{nei, distance[nei]});  // offer the shorter path to PQ
                    }
                }
            }

            if (count < n) return -1;
            else return res;
        }
    }


    /*
    - Dijkstra
    - use for loop to find the min distance instead of PQ

    Time: O(V^2)
    - O(V), while loop traverse each V
    - O(V), use for loop to find the min distance

    Space: O(E+V)
    - O(E), Map G
    - O(V), int[] distance, boolean[] visited
     */
    class Solution2_Dijkstra_No_PQ {
        public int networkDelayTime(int[][] times, int n, int k) {
            // build Graph
            Map<Integer, List<int[]>> G = new HashMap<>();
            for (int[] t : times) {
                int u = t[0], v = t[1], w = t[2];
                G.computeIfAbsent(u, key -> new ArrayList<>()).add(new int[]{v, w});
            }

            // init distance, visited array
            int[] distance = new int[n + 1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            boolean[] visited = new boolean[n + 1];

            // start from src
            distance[k] = 0;

            while (true) {                                          // O(V)
                // find the min distance and its according node
                int curNode = -1, curMinDist = Integer.MAX_VALUE;
                for (int i = 1; i <= n; i++) {                      // O(V)
                    if (!visited[i] && distance[i] < curMinDist) {
                        curNode = i;
                        curMinDist = distance[i];
                    }
                }

                if (curNode == -1) break;   // no node was found
                visited[curNode] = true;    // set curNode visited

                // go through unvisited neighbors of curNode
                // update neighbors' distance if a shorter path is found
                for (int[] nei : G.getOrDefault(curNode, new ArrayList<>())) {
                    int neiNode = nei[0], curToNeiDist = nei[1];
                    if (!visited[neiNode] && distance[curNode] + curToNeiDist < distance[neiNode])
                        distance[neiNode] = distance[curNode] + curToNeiDist;
                }
            }

            // final result
            int res = 0;
            for (int i = 1; i <= n; i++) {
                if (distance[i] == Integer.MAX_VALUE) return -1;
                res = Math.max(res, distance[i]);
            }
            return res;
        }
    }
}
