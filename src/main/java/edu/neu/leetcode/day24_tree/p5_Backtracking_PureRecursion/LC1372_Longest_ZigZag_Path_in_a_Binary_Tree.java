package edu.neu.leetcode.day24_tree.p5_Backtracking_PureRecursion;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC1372_Longest_ZigZag_Path_in_a_Binary_Tree {

    /*
    - DFS
    - Pure recursion

    Time:  O(N)
    Space: O(logN)
     */
    class Solution1_DFS_PureRecursion {
        int max;
        public int longestZigZag(TreeNode root) {
            dfs(root);
            return max == 0 ? 0 : max - 1;
        }

        // return [ans of going left, ans of going right]
        private int[] dfs(TreeNode root) {
            // base case
            if (root == null) return new int[2];

            int[] left = dfs(root.left);
            int[] right = dfs(root.right);

            int[] cur = new int[2];
            cur[0] = left[1] + 1;   // go left, then right
            cur[1] = right[0] + 1;  // go right, then left
            max = Math.max(max, Math.max(cur[0], cur[1]));
            return cur;
        }
    }

    
}
