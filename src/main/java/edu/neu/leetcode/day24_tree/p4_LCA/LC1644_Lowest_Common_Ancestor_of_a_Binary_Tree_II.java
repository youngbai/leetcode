package edu.neu.leetcode.day24_tree.p4_LCA;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC1644_Lowest_Common_Ancestor_of_a_Binary_Tree_II {

    /*
     - like LC236
     - if p or q does not exist in the tree, return null
     - if p,q exist in the tree, do LCA like LC236
     - but need 3 traversal
     Time: O(3N)
     */
    class Solution1_LCA {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode pNode = dfs(root, p.val), qNode = dfs(root, q.val);
            if (pNode == null || qNode == null) return null;    // p or q does not exist
            else return LCA(root, pNode, qNode);
        }

        private TreeNode dfs(TreeNode root, int target) {
            // base case
            if (root == null) return null;

            if (root.val == target) return root;        // root

            TreeNode left = dfs(root.left, target);     // left
            if (left != null) return left;

            TreeNode right = dfs(root.right, target);   // right
            if (right != null) return right;

            return null;
        }

        private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
            // base case
            if (root == null || root == p || root == q) return root;

            TreeNode left = LCA(root.left, p, q);
            TreeNode right = LCA(root.right, p, q);
            if (left != null && right != null) return root;
            if (left != null) return left;
            if (right != null) return right;
            return null;
        }
    }


    /*
    - Postorder
    - only need 1 traversal
    Time: O(N)
     */
    class Solution2_Postorder {
        int count = 0;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode LCA = LCA(root, p, q);
            return count == 2? LCA : null;
        }

        // have to use postorder to traverse all nodes
        private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;

            TreeNode left = LCA(root.left, p, q);   // left, after left LCA, count for left tree is maintained
            TreeNode right = LCA(root.right, p, q); // right, after right LCA, count for right tree is maintained
            if (root == p || root == q) {           // root, because p q are already counted, safely return root
                count++;
                return root;
            }

            if (left != null && right != null) return root;
            if (left != null) return left;
            if (right != null) return right;
            return null;
        }
    }



}
