package edu.neu.leetcode.day24_tree.p3_BST;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC450_Delete_Node_in_a_BST {

    class Solution1_BST {
        public TreeNode deleteNode(TreeNode root, int key) {
            // base case
            if (root == null) return null;

            // search and delete key node
            if (key < root.val) root.left = deleteNode(root.left, key);         // go left
            else if (key > root.val) root.right = deleteNode(root.right, key);  // go right
            else if (root.left == null) return root.right;                      // found & delete, but left is null
            else if (root.right == null) return root.left;                      // found & delete, but right is null
            else {                                  // found & delete, but left,right are not null
                root.val = findMin(root.right);     // replace key node with min value of the right subtree
                root.right = deleteNode(root.right, root.val);  // delete min node
            }
            return root;
        }

        private int findMin(TreeNode root) {
            while (root.left != null) root = root.left;
            return root.val;
        }
    }
}
