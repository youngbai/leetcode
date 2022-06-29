package edu.neu.leetcode.day24_tree.p3_BST;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC99_Recover_Binary_Search_Tree {

    /*
    - Inorder
    - Iteration
     */
    class Solution1_Iteration {
        public void recoverTree(TreeNode root) {
            TreeNode first = null, second = null;
            TreeNode pre = new TreeNode(Integer.MIN_VALUE);
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (root.val < pre.val) {   // cur node(root) < pre node
                    if (first == null) first = pre; // find the first target node
                    second = root; // find the second target node
                }
                // move forward
                pre = root;
                root = root.right;
            }

            // swap first and second
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }


    /*
    - Inorder
    - Recursion
     */
    class Solution2_Recursion {
        TreeNode first = null, second = null;
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);

        public void recoverTree(TreeNode root) {
            if (root == null) return;
            dfs(root);  // in order

            // swap first and second
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }

        private void dfs(TreeNode root) {
            // base case
            if (root == null) return;

            dfs(root.left);                 // left

            if (root.val < pre.val) {       // root
                if (first == null) first = pre;
                second = root;
            }
            pre = root;

            dfs(root.right);                // right
        }
    }
}
