package edu.neu.leetcode.day24_tree.p5_Backtracking_PureRecursion;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC549_Binary_Tree_Longest_Consecutive_Sequence_II {

    /*
    - DFS, Postorder
    - Pure recursion

    Time:  O(N)
    Space: O(logN)
     */
    class Solution1_PureRecursion {
        int max = 0;

        public int longestConsecutive(TreeNode root) {
            dfs(root);
            return max;
        }


        // return [ans of increasing, ans of decreasing]
        private int[] dfs(TreeNode root) {
            // base case
            if (root == null) return new int[2];

            // current inc, dcr, in default
            int inc = 1, dcr = 1;

            if (root.left != null) {                        // left
                int[] left = dfs(root.left);
                if (root.val == root.left.val + 1) inc = left[0] + 1;      // if root = left + 1
                else if (root.val == root.left.val - 1) dcr = left[1] + 1; // if root = left - 1
            }

            if (root.right != null) {                       // right
                int[] right = dfs(root.right);
                if (root.val == root.right.val + 1) inc = Math.max(inc, right[0] + 1);
                else if (root.val == root.right.val - 1) dcr = Math.max(dcr, right[1] + 1);
            }

            max = Math.max(max, inc + dcr - 1);             // root
            return new int[]{inc, dcr};
        }
    }
}
