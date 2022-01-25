package edu.neu.leetcode.day8_DFS;

import java.util.List;
import java.util.ArrayList;

public class LC39_Combination_Sum {

    /*
    Thinking:
    - DFS, Backtracking

    Time:  O(N^target)
    - Every time you have N choice (since you can use number repeatedly), and you can choose at most target times
    (actually it's smaller than target), so we can assume the worst-case time complexity is O(N^target).

    Space: O(target)
    - in worst case, the height of recursion tree is target
     */
    class Solution1_Backtracking {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            //Arrays.sort(candidates); // if it has duplicate integers, then should use it
            backtracking(res, candidates, target, new ArrayList<Integer>(), 0);
            return res;
        }

        private void backtracking(List<List<Integer>> res, int[] candidates, int target, List<Integer> path, int start) {
            if (target < 0) return; // overflow

            if (target == 0) {  // find one path
                res.add(new ArrayList<Integer>(path));
                return;
            }

            // keep looking
            for (int i = start; i < candidates.length; i++) {
                path.add(candidates[i]);
                backtracking(res, candidates, target - candidates[i], path, i);
                path.remove(path.size() - 1);
            }
        }
    }

}
