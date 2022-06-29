package edu.neu.leetcode.day24_tree.p5_Backtracking_PureRecursion;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC1448_Count_Good_Nodes_in_Binary_Tree {


    /*
    - DFS
    - Backtracking, pass max to each node

    Time:  O(N)
    Space: O(logN), height of tree
     */
    class Solution1_DFS_Backtracking {
        int count = 0;
        public int goodNodes(TreeNode root) {
            if (root == null) return 0;
            dfs(root, root.val);
            return count;
        }

        private void dfs(TreeNode root, int max) {
            // base case
            if (root.val >= max) {
                count++;
                max = root.val;
            }

            if (root.left != null) dfs(root.left, max);
            if (root.right != null) dfs(root.right, max);
        }
    }


    // same as Solution1
    class Solution2_DFS_Backtracking {
        int count = 0;
        public int goodNodes(TreeNode root) {
            dfs(root, Integer.MIN_VALUE);
            return count;
        }

        private void dfs(TreeNode root, int max) {
            // base case
            if (root == null) return;

            if (root.val >= max) {
                count++;
                max = root.val;
            }

            dfs(root.left, max);
            dfs(root.right, max);
        }
    }
}
