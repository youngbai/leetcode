package edu.neu.leetcode.day25_graph.p4_Graph_Basic_Topological_Sorting;

import java.util.*;

public class LC269_Alien_Dictionary {

    /*
    Idea
    - Topological_Sort_BFS
    - Each char as a vertex
    - Each edge as order of two char
    - turn the problem to Topological Sort sub problem

     */
    class Solution1_Topological_Sort_BFS {

        Map<Character, List<Character>> G = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        boolean valid = true;

        public String alienOrder(String[] words) {
            // build Graph, indegree    O(E)
            build(words);
            if (!valid) return "";

            StringBuilder res = new StringBuilder();

            // TOP Sorting(BFS)         O(V)
            Queue<Character> q = new ArrayDeque<>();
            for (char c : indegree.keySet())
                if (indegree.get(c) == 0) q.offer(c);

            while (!q.isEmpty()) {
                char v = q.poll();
                res.append(v);
                for (char nei : G.get(v)) {
                    indegree.put(nei, indegree.get(nei) - 1);   // nei's indegree - 1
                    if (indegree.get(nei) == 0) q.offer(nei);   // offer nei with 0 indegree to Q
                }
            }

            return res.length() < G.size()? "" : res.toString();
        }

        private void build(String[] words) {

            // build Graph, indegree
            for (String w: words) {
                for (char c : w.toCharArray()) {
                    indegree.put(c, 0);
                    G.put(c, new ArrayList<>());
                }
            }

            // add edges to Graph, update indegree
            for (int i = 0; i < words.length - 1; i++) {
                String w1 = words[i], w2 = words[i + 1];
                if (w1.length() > w2.length() && w1.startsWith(w2)) valid = false;   // corner case
                for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                    char c1 = w1.charAt(j), c2 = w2.charAt(j);  // c1->c2
                    if (c1 != c2) {
                        G.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                        break;
                    }
                }
            }
        }
    }


    /*
    Idea
    - DFS

     */
    class Solution2_DFS {
        Map<Character, List<Character>> G = new HashMap<>();
        Map<Character, Integer> visited = new HashMap<>();
        boolean valid = true;
        StringBuilder sb = new StringBuilder();

        public String alienOrder(String[] words) {
            // build Graph, visited
            build(words);

            // DFS
            for (char c : visited.keySet()) {       // traverse each vertex
                if (visited.get(c) == 0) dfs(c);
            }

            if (!valid) return "";
            return sb.length() < G.size()? "" : sb.reverse().toString();
        }

        private void dfs(char c) {
            visited.put(c, 1);                  // in process

            for (char nei : G.get(c)) {
                if (visited.get(nei) == 0) dfs(nei);    // dfs unvisited char
                else if (visited.get(nei) == 1) valid = false;  // find an inprocess vertex, it is cycle
            }

            sb.append(c);
            visited.put(c, 2);                  // visited
        }

        private void build(String[] words) {
            for (String w : words) {
                for (char c : w.toCharArray()) {
                    G.put(c, new ArrayList<>());
                    visited.put(c, 0);          // each char unvisited
                }
            }

            // add edges to Graph
            for (int i = 0; i < words.length - 1; i++) {
                String w1 = words[i], w2 = words[i + 1];
                if (w1.length() > w2.length() && w1.startsWith(w2)) valid = false; // corner case
                for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                    char c1 = w1.charAt(j), c2 = w2.charAt(j);  // c1->c2
                    if (c1 != c2) {
                        G.get(c1).add(c2);
                        break;
                    }
                }
            }
        }

    }
}
