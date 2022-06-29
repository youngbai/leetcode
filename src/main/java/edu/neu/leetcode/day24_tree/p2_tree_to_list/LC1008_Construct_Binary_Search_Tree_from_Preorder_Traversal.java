package edu.neu.leetcode.day24_tree.p2_tree_to_list;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC1008_Construct_Binary_Search_Tree_from_Preorder_Traversal {

    /*
    Tree - preorder
     */
    class Solution1_preorder {
        int i = 0;
        public TreeNode bstFromPreorder(int[] A) {
            return dfs(A, Integer.MAX_VALUE);
        }

        private TreeNode dfs(int[] A, int upper) {
            // base case
            if (i == A.length || A[i] > upper) return null;

            TreeNode root = new TreeNode(A[i++]);
            root.left = dfs(A, root.val);
            root.right = dfs(A, upper);
            return root;
        }
    }


}
