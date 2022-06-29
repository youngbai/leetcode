package edu.neu.leetcode.day24_tree.p4_LCA;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC236_Lowest_Common_Ancestor_of_a_Binary_Tree {

    /*
    - Postorder
    - Divide Conquer

     */
    class Solution1_Recursion {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // base case
            if (root == null || root == p || root == q) return root;

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) return root; // root is the LCA of p,q
            if (left != null) return left;      // left is one of p,q
            if (right != null) return right;    // right is one of p,q
            return null;    // find nothing
        }
    }


    /*
    - DFS
    - parentNode
    - find the joint node

    Time:  O(N), dfs(preorder) all nodes
    Space: O(N), map
     */
    class Solution2_DFS_ParentNode {
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            dfs(root, null);

            // path 1
            Set<TreeNode> ancestors = new HashSet<>();
            while (p != null) {
                ancestors.add(p);
                p = parent.get(p);
            }

            // path 2, try to find the joint node
            while (!ancestors.contains(q)) q = parent.get(q);
            return q;
        }

        private void dfs(TreeNode root, TreeNode parentNode) {
            if (root == null) return;
            parent.put(root, parentNode);
            dfs(root.left, root);
            dfs(root.right, root);
        }
    }

}
