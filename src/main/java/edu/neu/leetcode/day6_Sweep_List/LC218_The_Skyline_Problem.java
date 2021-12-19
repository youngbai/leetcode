package edu.neu.leetcode.day6_Sweep_List;

import java.util.*;

public class LC218_The_Skyline_Problem {

    /*
    Thinking:
    - compare the initial point and the result, try to find a pattern
    - find a data structure to help us implement the pattern

    Problem:
    - PQ remove() is O(n), not O(logn), use TreeMap instead, see Solution2
     */
    class Solution1 {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> res = new ArrayList<>();

            // create sweep line
            List<int[]> height = new ArrayList<>();
            for (int[] b: buildings) {
                height.add(new int[]{b[0], b[2]});  // start point
                height.add(new int[]{b[1], -b[2]}); // end point
            }

            // sort sweep line
            // sort by x AES, y(height) DESC
            // let highest first, second highest second
            Collections.sort(height, (a, b) -> a[0] == b[0]? b[1] - a[1]: a[0] - b[0]);

            // traverse seep line, compute the result
            // PQ: Max PQ
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);
            pq.offer(0);
            int preMax = 0;
            for (int[] h : height) {
                if (h[1] > 0) pq.offer(h[1]);   // new house, add its height to the PQ
                else pq.remove(-h[1]);          // old house, remove its height from PQ
                int curMax = pq.peek();
                if (curMax != preMax) {         // if max height is changed, there must be a higher or lower point
                    res.add(Arrays.asList(h[0], curMax));
                    preMax = curMax;
                }
            }

            return res;
        }
    }


    /*
    Thinking:
    - using TreeMap because it gives log time performance, while PQ does not
    - map.compute() is used to update a value, or remove a mapping.
        - if compute() return null, then remove this mapping
        - else update this value
        - https://docs.oracle.com/javase/8/docs/api/java/util/Map.html#compute-K-java.util.function.BiFunction-
    - PQ allows same value
    - treemap does NOT allow same key, so we increase or decrease its value (value is 1 initially)
     */
    class Solution2 {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> res = new ArrayList<>();

            // create sweep line
            List<int[]> height = new ArrayList<>();
            for (int[] b: buildings) {
                height.add(new int[]{b[0], b[2]});  // start point
                height.add(new int[]{b[1], -b[2]}); // end point
            }

            // sort sweep line
            // sort by x AES, y(height) DESC
            // let highest first, second highest second
            Collections.sort(height, (a, b) -> a[0] == b[0]? b[1] - a[1]: a[0] - b[0]);

            // traverse seep line, compute the result
            // treeMap: as a Max Heap
            TreeMap<Integer, Integer> treeMap = new TreeMap<>((a , b) -> b - a);
            treeMap.put(0, 1);
            int preMax = 0;
            for (int[] h : height) {
                // new house, add its height to the treeMap; If height already exists then increase the value
                if (h[1] > 0) treeMap.compute(h[1], (k, v) -> (v == null) ? 1 : v + 1);
                // old house, decrease or remove its height from treeMap
                else treeMap.compute(-h[1], (k, v) -> (v == 1) ? null : v - 1);
                int curMax = treeMap.firstKey();
                if (curMax != preMax) {     // if max height is changed, there must be a higher or lower point
                    res.add(Arrays.asList(h[0], curMax));
                    preMax = curMax;
                }
            }
            return res;
        }
    }

}
