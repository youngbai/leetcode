package edu.neu.leetcode.day24_tree.p5_Backtracking_PureRecursion;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC124_Binary_Tree_Maximum_Path_Sum {

    /*
    3 cases:
    - left + root + right
    - left + root, or root + right
    - root, because left or right are negative, we should ignore left or right
    - return left path or right path or just root, so that the previous node can construct new path using them

    Time:  O(N)
    Space: O(logN), height of tree
     */
    class Solution1_DFS_PureRecursion {
        int max = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            dfs(root);
            return max;
        }

        private int dfs(TreeNode root) {
            // base case
            if (root == null) return 0;

            // dfs(root.left) might be negative, if so, we should not consider left path, that's why we use max(0, dfs(root,left))
            int left = Math.max(0, dfs(root.left));
            int right = Math.max(0, dfs(root.right));
            max = Math.max(max, left + root.val + right);

            return root.val + Math.max(left, right);
        }
    }
}
