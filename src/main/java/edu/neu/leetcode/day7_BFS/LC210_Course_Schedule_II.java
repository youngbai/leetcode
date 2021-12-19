package edu.neu.leetcode.day7_BFS;

import java.util.*;

public class LC210_Course_Schedule_II {

    /*
    Thinking:
    - Graph traversal
    - Topological Sort, like BFS, but not same

     Time:  O(V+E)
     Space: O(V+E)
     */
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<Integer> res = new ArrayList<>();
            // init graph, indegree
            Map<Integer, List<Integer>> graph = new HashMap<>();
            int[] indegree = new int[numCourses];
            for (int[] pre : prerequisites) {
                int end = pre[0], start = pre[1];   // start->end
                graph.computeIfAbsent(start, x -> new ArrayList<>()).add(end);
                indegree[end]++;
            }

            // topological sort with BFS
            // init Queue
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < indegree.length; i++)
                if (indegree[i] == 0) q.offer(i);

            // BFS
            while (!q.isEmpty()) {
                int v = q.poll();
                res.add(v);
                for (int nei : graph.getOrDefault(v, new ArrayList<>()))
                    if (--indegree[nei] == 0) q.offer(nei);
            }

            return res.size() == numCourses? res.stream().mapToInt(i->i).toArray() : new int[0];
        }
    }
}
