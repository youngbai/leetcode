package edu.neu.leetcode.day24_tree.p4_LCA;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {

    /*
    Algorithm:
    1. Start traversing the tree from the root node.
    2. If both the nodes p and q are in the right subtree,
       then continue the search with right subtree starting step 1.
    3. If both the nodes p and q are in the left subtree, then continue the search
       with left subtree starting step 1.
    4. If both step 2 and step 3 are not true, this means we have found the node
       which is common to node p's and q's subtrees. and hence we return this common node as the LCA.

    Time:  O(N), where N is the number of nodes in the BST. In the worst case we might be visiting all the nodes of the BST.
    Space: O(N), This is because the maximum amount of space utilized by the recursion stack would be N since the height of a skewed BST could be N.
     */
    class Solution1_Recursion {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
            if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
            return root;
        }
    }


    class Solution2_Iteration {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            while (true) {
                if (root.val > p.val && root.val > q.val) root = root.left;
                else if (root.val < p.val && root.val < q.val) root = root.right;
                else return root;   // first time that p <= root <= q
            }
        }
    }
}
