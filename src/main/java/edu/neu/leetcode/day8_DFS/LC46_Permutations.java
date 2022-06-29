package edu.neu.leetcode.day8_DFS;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class LC46_Permutations {

    /*
    Thinking:
    - Backtracking DFS
    - LinkedHashSet, contains(), remove(), LinkedHashSet can save the insertion order

    Example: tree, dfs, backtracking
        []
     1   2   3
    2 3 1 3 1 2
    3 2 3 1 2 1

    Time:  O(N!)
    Space: O(N), memory stack, height of tree
     */
    class Solution1 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            // Arrays.sort(nums);   // Don't need sort
            dfs(res, new LinkedHashSet<Integer>(), nums);       // NOTE: LinkedHashSet
            return res;
        }
        private void dfs(List<List<Integer>> res, LinkedHashSet<Integer> path, int[] nums) {
            if (path.size() == nums.length) res.add(new ArrayList<Integer>(path)); // need a new List
            else {
                for (int i = 0; i < nums.length; i++) {
                    // check repeatedly access the same number
                    if (path.contains(nums[i])) continue;
                    path.add(nums[i]);
                    dfs(res, path, nums);
                    path.remove(nums[i]); // Caution: it is Set not List, so should not use `path.remove(path.size() - 1)`
                }
            }
        }
    }
}
