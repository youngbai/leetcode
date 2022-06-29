package edu.neu.leetcode.day2DSU;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC305_NumOfIslandsII {

    /*
    Thinking:
    - Each island is a joint set
    - try to connect disjoint set
    - Graph disjoint component

    Summary:
    - Graph -> BFS, DFS -> visited(boolean[] or Set)
    - join disjoint component, number of component in a graph -> DSU

     */
    class Solution {

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            List<Integer> res = new ArrayList<>();
            boolean[][] islands = new boolean[m][n];
            DSU dsu = new DSU(m * n);

            int count = 0;
            for (int[] cur : positions) {
                if (islands[cur[0]][cur[1]]) { // already visited
                    res.add(count);
                    continue;
                }
                islands[cur[0]][cur[1]] = true;
                count++;    // assume it is a disjoint island
                for (int[] dir : dirs) {    // check its neighbors
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n || !islands[x][y])
                        continue; // try to find its neighbor island, but not found
                    int component1 = dsu.find(cur[0] * n + cur[1]);
                    int component2 = dsu.find(x * n + y);
                    if (component1 != component2) {
                        dsu.union(component2, component1);
                        count--;
                    }
                }
                res.add(count);
            }
            return res;
        }

        class DSU {
            int[] parent;

            public DSU(int N) {
                parent = new int[N];
                for (int i = 0; i < N; i++) parent[i] = i;
            }

            public int find(int x) {
                if (parent[x] != x) parent[x] = find(parent[x]);
                return parent[x];
            }

            public void union(int x, int y) {
                parent[find(x)] = find(y);
            }
        }
    }

    @Test
    public void test1() {
        Solution sol = new Solution();
        int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};
        List<Integer> res = sol.numIslands2(3, 3, positions);
        assertEquals(1, (int)res.get(0));
        assertEquals(1, (int)res.get(1));
        assertEquals(2, (int)res.get(2));
        assertEquals(3, (int)res.get(3));
    }


    @Test
    public void test2() {
        Solution sol = new Solution();
        int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {1, 1}};
        List<Integer> res = sol.numIslands2(3, 3, positions);
        assertEquals(1, (int)res.get(0));
        assertEquals(1, (int)res.get(1));
        assertEquals(2, (int)res.get(2));
        assertEquals(1, (int)res.get(3));
    }


}
