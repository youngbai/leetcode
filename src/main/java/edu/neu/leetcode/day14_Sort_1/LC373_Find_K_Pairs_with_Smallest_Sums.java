package edu.neu.leetcode.day14_Sort_1;

import java.util.*;

public class LC373_Find_K_Pairs_with_Smallest_Sums {

    /*
    Thinking:
    - BFS, PQ
    - when we meet 'finding the first min/max K questions', we might use Heap, Quick Sort,
      because they dont have to sort every element
    - Memory Limit Exceeded, because boolean[][] visited cost O(M*N), so we use Set to optimize it

    Time: O(KlogK), K is the parameter
      - while loops PQ K times, O(K)
      - every time, we poll 1 element from PQ, and offer 2 elements to PQ, O(logK)
    Space: O(M*N)
      - boolean[][] visited, O(M*N)
      - PQ, O(K)
     */
    class Solution1_PQ {

        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            // base case
            int M = nums1.length, N = nums2.length;
            if (M == 0 || N == 0) return null;

            List<List<Integer>> res = new ArrayList<>();

            boolean[][] visited = new boolean[M][N];
            PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> nums1[a.i] + nums2[a.j] - nums1[b.i] - nums2[b.j]);

            // start from the cell(0,0)
            pq.offer(new Cell(0, 0));
            visited[0][0] = true;

            while (k > 0 && !pq.isEmpty()) {        // O(K)
                Cell cur = pq.poll();               // O(logK)
                res.add(Arrays.asList(nums1[cur.i], nums2[cur.j]));
                k--;

                Cell nei1 = new Cell(cur.i, cur.j + 1);
                if (nei1.j < N && !visited[nei1.i][nei1.j]) {
                    visited[nei1.i][nei1.j] = true;
                    pq.offer(nei1);                 // O(logK)
                }

                Cell nei2 = new Cell(cur.i + 1, cur.j);
                if (nei2.i < M && !visited[nei2.i][nei2.j]) {
                    visited[nei2.i][nei2.j] = true;
                    pq.offer(nei2);                 // O(logK)
                }
            }
            return res;
        }

        class Cell {
            int i, j;

            Cell(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }
    }

    /*
    Thinking:
    - same as Solution1_PQ
    - using Set to optimize the space
    - Set, must override equals() and hashCode()

    Time: O(KlogK)
    Space: O(K)
      - Set<Cell> visited
     */
    class Solution2_PQ {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            // base case
            int M = nums1.length, N = nums2.length;
            if (M == 0 || N == 0) return null;

            List<List<Integer>> res = new ArrayList<>();
            Set<Cell> visited = new HashSet<>();    // O(K)
            PriorityQueue<Cell> pq = new PriorityQueue<>((a,b) -> nums1[a.i] + nums2[a.j] - nums1[b.i] - nums2[b.j]);

            // start from the cell(0,0)
            Cell start = new Cell(0, 0);
            pq.offer(start);
            visited.add(start);

            while (k > 0 && !pq.isEmpty()) {
                Cell cur = pq.poll();
                res.add(Arrays.asList(nums1[cur.i], nums2[cur.j]));
                k--;

                Cell nei1 = new Cell(cur.i, cur.j + 1);
                if (nei1.j < N && visited.add(nei1)) {
                    pq.offer(nei1);
                }

                Cell nei2 = new Cell(cur.i + 1, cur.j);
                if (nei2.i < M && visited.add(nei2)) {
                    pq.offer(nei2);
                }
            }
            return res;
        }

        class Cell {
            int i, j;
            Cell(int i, int j) {
                this.i = i;
                this.j = j;
            }

            @Override
            public boolean equals(Object o) {
                if (o == this) return true;

                if (!(o instanceof Cell)) return false;

                Cell c = (Cell)o;
                return c.i == this.i && c.j == this.j;
            }

            @Override
            public int hashCode() {
                return i + j;
            }
        }
    }
}
