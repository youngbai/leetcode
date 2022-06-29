package edu.neu.leetcode.day24_tree.p2_tree_to_list;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LC889_Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal {

    class Solution1 {
        int[] preorder;
        int[] postorder;
        int preIndex = 0;
        Map<Integer, Integer> postMap = new HashMap<>();    // postorder value -> postorder index

        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            this.preorder = preorder;
            this.postorder = postorder;
            int N = preorder.length;
            for (int i = 0; i < N; i++) postMap.put(postorder[i], i);
            return dfs(0, N - 1);  // preorder
        }

        private TreeNode dfs(int postStart, int postEnd) {
            // base case
            if (postStart > postEnd) return null;
            TreeNode root = new TreeNode(preorder[preIndex++]);
            if (postStart == postEnd) return root;

            int leftRootIndexPostorder = postMap.get(preorder[preIndex]); // left root index in postorder
            root.left = dfs(postStart, leftRootIndexPostorder);
            root.right = dfs(leftRootIndexPostorder + 1, postEnd - 1);
            return root;
        }
    }


}
