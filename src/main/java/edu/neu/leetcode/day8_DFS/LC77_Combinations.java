package edu.neu.leetcode.day8_DFS;

import java.util.ArrayList;
import java.util.List;

public class LC77_Combinations {


    /*
    Thinking:
    - Backtracking DFS
    - eg. n = 4, k = 2
                 [ ]
            /   |   \   \
           1    2   3    4X
        / | \   | \  \
      12 13 14 23 24 34    <====== Results
    Time: C(k,n) = N!/(N-k)!k!
     */
    class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(res, new ArrayList<Integer>(), n, k, 1);
            return res;
        }
        private void dfs(List<List<Integer>> res, List<Integer> level, int n, int k, int start) {
            if (level.size() == k) res.add(new ArrayList<>(level));
            else {
                for (int i = start; i <= n; i++) {
                    level.add(i);
                    dfs(res, level, n, k, i + 1);
                    level.remove(level.size() - 1);
                }
            }
        }
    }
}
