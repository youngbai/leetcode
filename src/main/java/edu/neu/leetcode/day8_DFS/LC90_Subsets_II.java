package edu.neu.leetcode.day8_DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC90_Subsets_II {

    /*
    Thinking:
    - problem -> find the power set
    - sort firstly
    - Backtracking recursion tree
      https://discuss.leetcode.com/assets/uploads/files/1503221799085-78.subsets-resized.png
                     [ ]
              /       |       \
            [1]      [2]     [2]X   # the second [2] should be ignored
           /  \       |
       [1,2] [1,2]X  [2,2]          # the second [1,2] should be ignored
        /
    [1,2,2]

     Time:  O(N * 2^N)
        - O(2^N): there are N numbers and 2 decision ( whether to include or leave a number )
        - O(N): copy each combination to result list
     Space: O(N):
        - use O(N) space to maintain the List<Integer> level
     https://medium.com/@vasanths294/permutation-combination-subset-time-complexity-eca924e00071
     */
    class Solution1 {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            backtracking(res, new ArrayList<Integer>(), nums, 0);
            return res;
        }
        private void backtracking(List<List<Integer>> res, List<Integer> level, int[] nums, int start) {
            res.add(new ArrayList<Integer>(level));
            for (int i = start; i < nums.length; i++) {
                // if repeated number, ignore them
                if (i > start && nums[i - 1] == nums[i]) continue;
                level.add(nums[i]);
                backtracking(res, level, nums, i + 1);
                level.remove(level.size() - 1);
            }
        }
    }



}
