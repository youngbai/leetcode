package edu.neu.leetcode.day24_tree.p3_BST;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC173_Binary_Search_Tree_Iterator {

    // inorder traversal implementation
    class BSTIterator {

        Deque<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new ArrayDeque<>();
            pushAllLeft(root);
        }

        public int next() {
            TreeNode node = stack.pop();
            pushAllLeft(node.right);
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        private void pushAllLeft(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }
    }
}
