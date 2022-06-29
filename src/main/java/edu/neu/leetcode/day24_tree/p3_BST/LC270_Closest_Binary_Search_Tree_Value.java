package edu.neu.leetcode.day24_tree.p3_BST;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC270_Closest_Binary_Search_Tree_Value {

    // BST search
    class Solution1_BST_Search {
        int res = 0;
        double min = Double.MAX_VALUE;
        public int closestValue(TreeNode root, double target) {
            dfs(root, target);
            return res;
        }

        private void dfs(TreeNode root, double target) {
            // base case
            if (root == null) return ;

            if (Math.abs(root.val - target) < min) {        // root
                res = root.val;
                min = Math.abs(root.val - target);
            }


            if (target < root.val) dfs(root.left, target);  // left
            else dfs(root.right, target);                   // right
        }
    }
}
