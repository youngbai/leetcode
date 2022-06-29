package edu.neu.leetcode.day24_tree.p3_BST;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC1382_Balance_a_Binary_Search_Tree {

    // like LC108
    class Solution1_BST {
        public TreeNode balanceBST(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            dfs(root, res);
            return build(res, 0, res.size() - 1);
        }

        private void dfs(TreeNode root, List<Integer> res) {
            if (root == null) return;
            dfs(root.left, res);
            res.add(root.val);
            dfs(root.right, res);
        }

        private TreeNode build(List<Integer> res, int start, int end) {
            if (start > end) return null;
            int mid = start + (end - start) / 2;
            TreeNode root = new TreeNode(res.get(mid));
            root.left = build(res, start, mid - 1);
            root.right = build(res, mid + 1, end);
            return root;
        }
    }
}
