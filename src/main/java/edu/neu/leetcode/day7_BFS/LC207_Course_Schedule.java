package edu.neu.leetcode.day7_BFS;

import java.util.*;

public class LC207_Course_Schedule {

    /*
    Thinking:
    - Topological Sort, like BFS, but not same

    Topological Sort Algo:
    Require: G is a directed acyclic graph (DAG)
    def TopoSort(G):
        # init G with adjacent list, init indegree with map
        T = List()
        G = adjacent list
        map = initially {v1:0, v2:0, ...}, key=vertex, value=indegree
        for v in G:
            for u : v.neighbors:
                map(u)++

        # Topological Sort with BFS
        # init Q by putting all vertices with 0 indegree
        for v in G:
            if map.get(v) == 0:
                add v to Q
        # BFS
        Q = Queue()
        while Q is not empty:
            v = Q.offer()
            append v to T
            for u in v.neighbors:
                map(u)--
                if map(u) == 0:
                    add u to Q
        return T

     Time:  O(V+E)
     Space: O(V+E)
     */
    class Solution1 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // init graph, indegree
            Map<Integer, List<Integer>> graph = new HashMap<>();
            int[] indegree = new int[numCourses];
            for (int[] pre: prerequisites) {
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
            int count = 0;
            while (!q.isEmpty()) {
                int v = q.poll();
                count++;
                for (int nei : graph.getOrDefault(v, new ArrayList<>()))
                    if (--indegree[nei] == 0) q.offer(nei);
            }
            return count == numCourses;
        }
    }
}
