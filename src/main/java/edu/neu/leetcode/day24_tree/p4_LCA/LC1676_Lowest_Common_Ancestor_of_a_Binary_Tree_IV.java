package edu.neu.leetcode.day24_tree.p4_LCA;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class LC1676_Lowest_Common_Ancestor_of_a_Binary_Tree_IV {

    class Solution1_LCA_DFS {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
            Set<Integer> set = new HashSet<>();
            for (TreeNode node : nodes) set.add(node.val);
            return LCA(root, set);
        }

        private TreeNode LCA(TreeNode root, Set<Integer> set) {
            if (root == null) return null;
            if (set.contains(root.val)) return root;    // first time find target node, don't have to search its children

            TreeNode left = LCA(root.left, set);
            TreeNode right = LCA(root.right, set);

            if (left != null && right != null) return root;
            if (left != null) return left;
            if (right != null) return right;
            return null;
        }
    }
}
