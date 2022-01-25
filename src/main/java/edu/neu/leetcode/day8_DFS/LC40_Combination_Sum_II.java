package edu.neu.leetcode.day8_DFS;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class LC40_Combination_Sum_II {

    /*
    Thinking:
    - DFS, Backtracking

    Example
    nums=[1,2,3]    target=4
    			[]
        1			2		3
    1 	2	3	1	2		1
    1 2 1		1
    1


    Time:  O(N^target)
    - Every time you have N choice (since you can use number repeatedly), and you can choose at most target times
    (actually it's smaller than target), so we can assume the worst-case time complexity is O(N^target).
    - with prune1, prune2, Time becomes better

    Space: O(target)
    - in worst case, the height of recursion tree is target
     */
    class Solution1_Backtracking {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(candidates);    // prune1
            backtracking(res, candidates, target, new ArrayList<Integer>(), 0);
            return res;
        }

        private void backtracking(List<List<Integer>> res, int[] candidates, int target, List<Integer> path, int start) {
            if (target < 0) return; // overflow

            if (target == 0) {  // find a valid path
                res.add(new ArrayList<Integer>(path));
                return;
            }

            // try different candidates
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) continue;  // prune2
                path.add(candidates[i]);
                backtracking(res, candidates, target - candidates[i], path, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}
