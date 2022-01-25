package edu.neu.leetcode.day8_DFS;

import java.util.List;
import java.util.ArrayList;

public class LC216_Combination_Sum_III {

    /*
    Thinking:
    - DFS, Backtracking

    Time:  O(9!*K/(9-K)!)
    - In worst case, we have to explore all potential combinations to the very end. All potential combinations are 9!/(9-K)!
    - why *K? Each exploration takes a constant time to process, except the last step where it takes O(K) time to
      make a copy of combination.

    Space: O(K), the depth of the recursion tree
     */
    class Solution1_Backtracking {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(res, k, n, new ArrayList<Integer>(), 1);
            return res;
        }

        private void dfs(List<List<Integer>> res, int k, int n, List<Integer> path, int start) {
            if (n > 0 && (k == 0 || n < start)) return;
            if (n < 0) return;

            if (n == 0 && k == 0) {res.add(new ArrayList<>(path)); return;}

            for (int i = start; i < 10; i++) {
                path.add(i);
                dfs(res, k - 1, n - i, path, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}
