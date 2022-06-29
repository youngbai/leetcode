package edu.neu.leetcode.day7_BFS;

import java.util.*;

public class LC207_Course_Schedule {

    /*
    Thinking:
    - Topological Sort, like BFS, but not same
    - always offer vertex with 0 indegree to Q, so that we can always poll vertex with 0 indegree from Q
    - always update neighbors' indegree when poll from Q

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
        # init Q by offering all vertices with 0 indegree
        for v in G:
            if map.get(v) == 0:
                add v to Q
        # BFS
        Q = Queue()
        while Q is not empty:
            v = Q.poll()
            append v to T
            for u in v.neighbors:
                map(u)--
                if map(u) == 0:
                    add u to Q
        return T

     Time:  O(V+E)
     Space: O(V+E)
     */
    class Solution1_Topological_Sort_BFS {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // init graph, indegree
            // pre_course, list_of_post_course
            Map<Integer, List<Integer>> graph = new HashMap<>();
            int[] indegree = new int[numCourses];
            for (int[] pre: prerequisites) {                            // O(E)
                int end = pre[0], start = pre[1];   // start->end
                graph.computeIfAbsent(start, x -> new ArrayList<>()).add(end);
                indegree[end]++;
            }

            // topological sort with BFS
            // init Queue, and start from course with 0 indegree
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < indegree.length; i++)
                if (indegree[i] == 0) q.offer(i);

            // BFS                          // O(V), each V enter Q once, out once
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

    /*
    Idea:
    - DFS(three color)
        - 0: unvisited
        - 1: in process
        - 2: visited
    - check if there is a cycle, if cycle exists, then can not finish, otherwise, can finish

     */
    class Solution2_DFS {
        List<List<Integer>> G;
        int[] visited;
        boolean valid = true;
        public boolean canFinish(int N, int[][] prerequisites) {
            // #1: build Graph
            G = new ArrayList<>();
            for (int i = 0; i < N; i++) G.add(new ArrayList<Integer>());
            for (int[] p : prerequisites) {
                int end = p[0], start = p[1];
                G.get(start).add(end);
            }

            // #2: build visited array
            // 0:unvisited, 1:in process, 2: visited
            visited = new int[N];

            // #3: DFS every unvisited vertex
            for (int i = 0; i < N; i++) {
                if (visited[i] == 0) dfs(i);
            }

            return valid;
        }

        private void dfs(int u) {
            visited[u] = 1;     // set current vertex in process

            for (int nei : G.get(u)) {
                if (visited[nei] == 0) dfs(nei);// dfs its unvisited neighbors
                else if (visited[nei] == 1) {   // == 1 means there is a cycle
                    valid = false;              // can not finish
                    break;                      // exit loop in advance
                }
            }

            visited[u] = 2;     // set current vertex visited
        }
    }
}
