package edu.neu.leetcode.day25_graph.p2_ShortestPath;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Dijkstra {

    /**
     * @param N number of vertex
     * @param G Graph
     * @param src source vertex
     */
    public void dijkstra(int N, int[][] G, int src) {
        // init G to Graph(Map)
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] edge : G) graph.computeIfAbsent(edge[0], k -> new HashMap<>()).put(edge[1], edge[2]);

        // init distance[], pre[], visited[]
        int[] distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        int[] pre = new int[N];
        Arrays.fill(pre, -1);
        boolean[] visited = new boolean[N];

        // start from src
        distance[src] = 0;
        pre[src] = src;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);    // node, cost
        pq.offer(new int[]{src, 0});   // start from src
        while (!pq.isEmpty()) {
            // remove u from PQ
            int[] cur = pq.poll();
            int u = cur[0], cost = cur[1];
            if (visited[u]) continue;   // if current node is visited, skip
            visited[u] = true;          // mark current node visited

            // go through unvisited neighbors of y
            // update these neighbors' distance if a shorter path is found
            // offer the shorter path to PQ
            Map<Integer, Integer> neighbors = graph.getOrDefault(u, new HashMap<>());
            for (int nei : neighbors.keySet()) {
                int uToNeiCost = neighbors.get(nei);   // cost from u to nei
                if (!visited[nei] && distance[u] + uToNeiCost < distance[nei]) { // if nei is not visited & a shorter path is found
                    distance[nei] = distance[u] + uToNeiCost;
                    pq.offer(new int[]{nei, distance[nei]});
                    pre[nei] = u;
                }
            }
        }

        printShortestPath(distance, pre, src);
    }

    private void printShortestPath(int[] distance, int[] pre, int src) {
        System.out.println("----Distance----");
        for (int i = 0; i < distance.length; i++) {
            System.out.printf("%s->%s: %s\n", C[src], C[i], distance[i]);
        }

        System.out.println("----Shortest Path----");
        for (int i = 0; i < pre.length; i++) {
            System.out.printf("%s->%s\n", C[pre[i]], C[i]);
        }

    }


    char[] C = {'A', 'B', 'C', 'D', 'E'};

    @Test
    public void test1() {
        int[][] G = {
                {0, 1, 4},
                {0, 2, 2},
                {1, 2, 3},
                {1, 3, 2},
                {1, 4, 3},
                {2, 1, 1},
                {2, 3, 4},
                {2, 4, 5},
                {4, 3, 1},
        };


        Dijkstra d = new Dijkstra();
        d.dijkstra(5, G, 0);
    }
}


