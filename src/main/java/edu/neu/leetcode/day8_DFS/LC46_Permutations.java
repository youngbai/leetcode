package edu.neu.leetcode.day8_DFS;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class LC46_Permutations {

    /*
    Thinking:
    - Backtracking DFS
    - LinkedHashSet, contains(), remove(), LinkedHashSet can save the insertion order

    Time: O(N!)
     */
    class Solution1 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            // Arrays.sort(nums);   // Don't need sort
            dfs(res, new LinkedHashSet<Integer>(), nums);
            return res;
        }
        private void dfs(List<List<Integer>> res, LinkedHashSet<Integer> level, int[] nums) {
            if (level.size() == nums.length) res.add(new ArrayList<Integer>(level)); // need a new List
            else {
                for (int i = 0; i < nums.length; i++) {
                    // check repeatedly access the same number
                    if (level.contains(nums[i])) continue;
                    level.add(nums[i]);
                    dfs(res, level, nums);
                    level.remove(nums[i]);
                }
            }
        }
    }
}
