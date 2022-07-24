package edu.neu.leetcode.day8_DFS;

import java.util.*;

public class LC947_Most_Stones_Removed_with_Same_Row_or_Column {

    /*
    Thinking:
    - DFS, Set<Cell>
    - Problem -> Graph
    - If there is a independent Graph (cluster), after removing stones, there is only one stone left.
      So, the answer = # stones - # cluster
    - Use Set<Cell> to remove duplicates
    - Override equals(), hashCode()
     */
    class Solution1_DFS {
        public int removeStones(int[][] stones) {
            int N = stones.length, cluster = 0;

            // build Graph (x->List<Cell>), (y->List<Cell>)
            Map<Integer, List<Cell>> GX = new HashMap<>();
            Map<Integer, List<Cell>> GY = new HashMap<>();
            for (int[] stone : stones) {
                int x = stone[0], y = stone[1];
                Cell c = new Cell(x, y);
                GX.computeIfAbsent(x, k -> new ArrayList<>()).add(c);
                GY.computeIfAbsent(y, k -> new ArrayList<>()).add(c);
            }

            // DFS
            Set<Cell> set = new HashSet<>();
            for (int[] stone : stones) {
                int x = stone[0], y = stone[1];
                Cell c = new Cell(x, y);
                if (set.add(c)) {
                    cluster++;
                    dfs(x, y, set, GX, GY);
                }
            }

            return N - cluster;
        }

        private void dfs(int x, int y, Set<Cell> set, Map<Integer, List<Cell>> GX, Map<Integer, List<Cell>> GY) {
            for (Cell c : GX.getOrDefault(x, new ArrayList<>())) {
                if (set.add(c)) {
                    dfs(c.i, c.j, set, GX, GY);
                }
            }

            for (Cell c : GY.getOrDefault(y, new ArrayList<>())) {
                if (set.add(c)) {
                    dfs(c.i, c.j, set, GX, GY);
                }
            }
        }


        class Cell {
            int i, j;
            Cell (int i, int j) {
                this.i = i;
                this.j = j;
            }

            // NOTE: equals()
            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                } else if (o instanceof Cell) {
                    Cell c = (Cell) o;
                    return this.i == c.i && this.j == c.j;
                } else {
                    return false;
                }
            }

            // NOTE: hashCode()
            public int hashCode() {
                return i + j;
            }
        }
    }


}
