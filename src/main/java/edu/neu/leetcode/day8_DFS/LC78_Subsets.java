package edu.neu.leetcode.day8_DFS;

import java.util.ArrayList;
import java.util.List;

public class LC78_Subsets {

    /*
    Thinking:
    - Cascading
    - start from empty subset in output list. At each step one takes new integer
      into consideration and generates new subsets from the existing ones.
    - eg. [1,2,3]
Intuition: consider Nth element, combine it with previous solutions
2^0  -  []
2^1  -  [] [1]      # consider 1, combine 1 with []
2^2  -  [] [1] [2] [1,2]  # consider 2, combine 2 with [], [1], get [2], [1,2]
2^3  -  [] [1] [2] [1,2] [3] [1,3] [2,3] [1,2,3]  # consider 3, combine 3 with [], [1] [2] [1,2],
                                                  # get [3], [1,3], [2,3], [1,2,3]

     Time:  O(N * 2^N) reference Solution2
     Space: O(2^N), List<List<Integer>> temp
     */
    class Solution1 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<Integer>());

            for (int num : nums) {                  // O(N)
                List<List<Integer>> temp = new ArrayList<>();
                for (List<Integer> cur : res) {     // O(2^0)-O(2^N)
                    // List<Integer> newCombine = new ArrayList(cur);
                    // newCombine.add(num);
                    // temp.add(newCombine);
                    temp.add(new ArrayList<Integer>(cur){{add(num);}});
                }
                res.addAll(temp);
            }
            return res;
        }
    }

    /*
    Thinking:
    - problem -> find the power set
    - Backtracking recursion tree
      https://discuss.leetcode.com/assets/uploads/files/1503221799085-78.subsets-resized.png
                     [ ]
              /       |       \
            [1]      [2]     [3]
           /  \       |
       [1,2] [1,3]  [2,3]
        /
    [1,2,3]

    Backtracking:
    - Backtracking is an algorithm for finding all solutions by exploring all potential candidates.
      If the solution candidate turns to be not a solution (or at least not the last one),
      backtracking algorithm discards it by making some changes on the previous step,
      i.e. backtracks and then try again.
      https://leetcode.com/explore/learn/card/recursion-ii/472/backtracking/2654/
    - Backtracking is like traverse a tree with DFS
    - optimization: pruning the recursion tree
    - e.g.
        N-Queen puzzle
        Robot Room Cleaner
        Sudoku Solver

     Backtracking Template:
     result = []
     def backtracking(path, candidates):
        if Satisfy the end condition (path):
            result.add(path)
            return

        # iterate all possible candidates.
        for candidate in candidates:
            # try this partial candidate solution
            path.add(candidate)
            # DFS: given this candidate, explore further
            backtracking(path, candidates)
            # backtrack
            path.remove(candidate)

     Time:  O(N * 2^N)
        - O(2^N): there are N numbers and 2 decision ( whether to include or leave a number )
        - O(N): copy each combination to result list
     Space: O(N):
        - use O(N) space to maintain the List<Integer> level
     https://medium.com/@vasanths294/permutation-combination-subset-time-complexity-eca924e00071
     */
    class Solution2 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            backtracking(res, new ArrayList<Integer>(), nums, 0);
            return res;
        }
        private void backtracking(List<List<Integer>> res, List<Integer> level, int[] nums, int start) {
            res.add(new ArrayList<Integer>(level));   // save previous layer result by copying
            for (int i = start; i < nums.length; i++) {
                level.add(nums[i]); // add element based on previous layer result
                backtracking(res, level, nums, i + 1); // DFS next layer
                level.remove(level.size() - 1); // revert previous try, so that can continue next try
            }
        }
    }


    /*
    Thinking:
    - Lexicographic (Binary Sorted) Subsets
    - each subset --- a bitmask of length n,
      where 1 on the ith position in bitmask means the presence of nums[i] in the subset,
      and 0 means its absence.
    - e.g.[1,2,3]
      [123]  [123]  [123]  [123]  [123] ... [123]
      [000]  [001]  [010]  [100]  [101] ... [111]   mask from 0 to 2^N (NOT included)
      [   ]  [003]  [020]  [100]  [103] ... [123]
     */
    class Solution3 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            int N = nums.length, start = 0, end = 1 << N; // end = largest mask + 1 = 2^N

            for (int mask = start; mask < end; mask++) {
                List<Integer> oneResult = new ArrayList<>();
                for (int i = 0; i < N; i++) {
                    int val = (mask >> i) & 1;  // value of position i
                    if (val == 1) oneResult.add(nums[N - 1 - i]);
                }
                res.add(oneResult);
            }
            return res;
        }
    }





}
