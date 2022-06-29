package edu.neu.leetcode.day24_tree.p5_Backtracking_PureRecursion;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC1120_Maximum_Average_Subtree {

    class Solution1_PureRecursion {
        double max = 0.0;
        public double maximumAverageSubtree(TreeNode root) {
            dfs(root);
            return max;
        }

        // return [sum of nodes, number of nodes]
        private int[] dfs(TreeNode root) {
            // base case
            if (root == null) return new int[2];

            int[] left = dfs(root.left);
            int[] right = dfs(root.right);

            int[] cur = new int[2];
            cur[0] = left[0] + root.val + right[0]; // sum of nodes
            cur[1] = left[1] + 1 + right[1];        // number of nodes

            max = Math.max(max, (double) cur[0] / cur[1]);
            return cur;
        }
    }
}
