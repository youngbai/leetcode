package edu.neu.leetcode.day8_DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC47_Permutations_II {

    /*
    Thinking:
    - duplicate problem -> sort
    - Backtracking DFS
    - Intuition:
        - only use first duplicate number
        - if cur number is used,
            then skip it
        - if pre duplicate number is NOT used, => it's a horizontal duplicate case
            then SKIP it
            e.g. (1) skip second [1]
                 (2) skip second [2,1]
        - if pre duplicate number is used, => it's a vertical duplicate case
            then USE it
            e.g.  []
                 /  ...
               [1]  ...    pre is used
               /
             [1,1]  ...    vertical case, use it

    - e.g. [1,1,2]
                     [ ]
              /       |       \
            [1]      [1]X     [2]   # (1) skip second [1]
           /  \              /   \
       [1,1] [1,2]       [2,1]  [2,1]X   # (2) skip second [2,1]
        /      |           |
    [1,1,2]  [1,2,1]    [2,1,1]
    - (1) skip second [1], because pre 1 is NOT used, which => horizontal duplicate case
    - (2) skip second [2,1], because pre 1 is NOT used, which => horizontal duplicate case

    Time: O(N!)
     */
    class Solution1 {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            dfs(res, new ArrayList<Integer>(), nums, new boolean[nums.length]);
            return res;
        }

        private void dfs(List<List<Integer>> res, List<Integer> path, int[] nums, boolean[] used) {
            if (path.size() == nums.length) res.add(new ArrayList<>(path));
            else {
                for (int i = 0; i < nums.length; i++) {
                    // used[i] : cur number is used
                    // i > 0 && nums[i] == nums[i - 1] && !used[i - 1]
                    //   : if pre duplicate number is NOT used, => it's a horizontal duplicate case
                    if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
                    used[i] = true;
                    path.add(nums[i]);
                    dfs(res, path, nums, used);
                    used[i] = false;
                    path.remove(path.size() - 1);
                }
            }
        }
    }


}
