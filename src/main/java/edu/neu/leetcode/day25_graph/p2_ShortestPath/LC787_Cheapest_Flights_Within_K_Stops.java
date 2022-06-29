package edu.neu.leetcode.day25_graph.p2_ShortestPath;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC787_Cheapest_Flights_Within_K_Stops {

    /*
    - Dijkstra
    - TLE

    Note:
    - we don't need distance[], because we might use the shortest path, and we might regret and choose the
      a more expensive path with less stops.
    - distance[] is only used when we want the shortest path
    - So, we put all possible path, might not the shortest path, into the PQ.
    - So, we can regret by ignore the shortest path, and pick up the next one from PQ

    Time: O(E + VlogV), where E is the total number of flights
    Space: O(E)
     */
    class Solution1_Dijkstra_TLE {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

            // init Graph using Map
            Map<Integer, Map<Integer, Integer>> G = new HashMap<>();
            for (int[] f : flights) G.computeIfAbsent(f[0], key -> new HashMap<>()).put(f[1], f[2]);

            // start from src
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);        // city, price, stops
            pq.offer(new int[]{src, 0, k + 1});

            // dijkstra
            while (!pq.isEmpty()) {
                // find the shortest edge
                int[] cur = pq.poll();
                int city = cur[0], price = cur[1], stops = cur[2];

                // find the dst
                if (city == dst) return price;

                // consider its neighbors, add neighbors to pq
                if (stops > 0) {
                    Map<Integer, Integer> neighbors = G.getOrDefault(city, new HashMap<>());
                    for (int nei : neighbors.keySet())
                        pq.offer(new int[]{nei, price + neighbors.get(nei), stops - 1});
                }
            }

            // dst not found
            return -1;
        }
    }



    /*
    - Dijkstra
    - use visited[] to store previous stops

    Note1:
    - we should allow city to be revisited
    - because the shorted path might exceed the stops limitation
    - we have to consider a more expensive path but with less stops, which lead to revisit the city

    Time: O(ElogE), where E is the total number of flights
    Space: O(E)
     */
    class Solution2_Dijkstra_Visited_Pruning {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // build Graph
            Map<Integer, Map<Integer, Integer>> G = new HashMap<>();
            for (int[] f : flights) {
                int from = f[0], to = f[1], price = f[2];
                G.computeIfAbsent(from, key -> new HashMap<>()).put(to, price);
            }
            Map<Integer, Integer> visited = new HashMap<>();   // city,stops

            // start from src
            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]); // city,price,stops
            q.offer(new int[]{src, 0, k + 1});

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int city = cur[0], price = cur[1], stops = cur[2];
                visited.put(city, stops);

                if (city == dst) return price;

                if (stops > 0) {
                    // consider its neighbors
                    Map<Integer, Integer> neighbors = G.getOrDefault(city, new HashMap<>());
                    for (int nei : neighbors.keySet()) {
                        // Note1:
                        // if nei is unvisited or nei is visited before but this time it has more stops left
                        // that means the current path is more expensive but has more stops left
                        // visited.get(nei) is the stops the nei had before
                        // stops - 1 is the stops the nei has in current path
                        if (!visited.containsKey(nei) || stops - 1 > visited.get(nei)) {
                            int curToNeiPrice = neighbors.get(nei);
                            q.offer(new int[]{nei, price + curToNeiPrice, stops - 1});
                        }
                    }
                }
            }

            return -1;
        }
    }
}
