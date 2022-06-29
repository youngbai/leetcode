package edu.neu.leetcode.day24_tree.p2_tree_to_list;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LC106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {

    /*
    Same as LC105
     */
    class Solution1_postorder {
        int[] inorder;
        int[] postorder;
        Map<Integer, Integer> inorderMap = new HashMap<>(); // postorder value -> postorder index
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            this.inorder = inorder;
            this.postorder = postorder;
            int N = inorder.length;
            for (int i = 0; i < N; i++) inorderMap.put(inorder[i], i);
            return dfs(N - 1, 0, N - 1);
        }

        private TreeNode dfs(int rootIndexPostOrder, int inStart, int inEnd) {
            // base case
            if (inStart > inEnd) return null;

            TreeNode root = new TreeNode(postorder[rootIndexPostOrder]);
            int rootIndexInorder = inorderMap.get(root.val);
            int rightTreeSize = inEnd - rootIndexInorder;

            root.left = dfs(rootIndexPostOrder - 1 - rightTreeSize, inStart, rootIndexInorder - 1);
            root.right = dfs(rootIndexPostOrder - 1, rootIndexInorder + 1, inEnd);
            return root;
        }
    }
}
