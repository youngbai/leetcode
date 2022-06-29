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
    class Solution1_Topological_Sort_BFS {
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
            // init Queue, and start from course with 0 indegree
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < indegree.length; i++)
                if (indegree[i] == 0) q.offer(i);

            // BFS
            while (!q.isEmpty()) {
                // get vertex with 0 indegree
                int v = q.poll();
                res.add(v);
                // update
                for (int nei : graph.getOrDefault(v, new ArrayList<>()))
                    if (--indegree[nei] == 0) q.offer(nei);
            }

            // NOTE: List.stream().mapToInt(i->i).toArray()
            return res.size() == numCourses? res.stream().mapToInt(i->i).toArray() : new int[0];
        }
    }


    /*
    - like LC207
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
        int[] res;
        int count;
        public int[] findOrder(int N, int[][] prerequisites) {
            // build Graph
            G = new ArrayList<>();
            for (int i = 0; i < N; i++) G.add(new ArrayList<>());
            for (int[] p : prerequisites) {
                int end = p[0], start = p[1];
                G.get(start).add(end);
            }

            // build visited array
            visited = new int[N];

            // DFS all unvisited vertex
            res = new int[N];
            count = N - 1;
            for (int i = 0; i < N; i++) {
                if (visited[i] == 0) dfs(i);
            }

            if (valid) return res;
            else return new int[0];
        }

        private void dfs(int u) {
            visited[u] = 1;

            for (int nei : G.get(u)) {
                if (visited[nei] == 0) dfs(nei);
                else if (visited[nei] == 1) {
                    valid = false;
                    break;
                }
            }

            res[count--] = u;
            visited[u] = 2;
        }
    }
}
