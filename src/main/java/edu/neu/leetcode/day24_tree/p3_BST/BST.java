package edu.neu.leetcode.day24_tree.p3_BST;

import edu.neu.leetcode.commonbean.TreeNode;

/*
BST: search, insert
 */
public class BST {

    // recursion
    public TreeNode search(TreeNode root, int target) {
        // base case
        if (root == null || root.val == target) return root;

        if (target < root.val) return search(root.left, target);    // left
        else return search(root.right, target);                            // right
    }

    // iteration
    public TreeNode search2(TreeNode root, int target) {
        while (true) {
            // base case
            if (root == null) return null;
            if (target == root.val) return root;

            if (target < root.val) root = root.left;    // left
            else root = root.right;                     // right
        }
    }

    // recursion
    public TreeNode insert(TreeNode root, int target) {
        // base case
        if (root == null) {
            return new TreeNode(target);
        }

        if (target < root.val) root.left = insert(root.left, target);
        else if (target > root.val) root.right = insert(root.right, target);
        return root;
    }

    // iteration
    public TreeNode insert2(TreeNode root, int target) {
        TreeNode newNode = new TreeNode(target);
        if (root == null) {
            return newNode;
        }

        TreeNode cur = root, pre = null;
        while (cur != null) {
            pre = cur;
            if (target < cur.val) cur = cur.left;
            else cur = cur.right;
        }

        if (target < pre.val) pre.left = newNode;
        else pre.right = newNode;
        return root;
    }


}
