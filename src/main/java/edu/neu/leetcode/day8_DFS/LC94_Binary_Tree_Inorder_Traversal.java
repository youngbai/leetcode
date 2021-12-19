package edu.neu.leetcode.day8_DFS;

import commonbean.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC94_Binary_Tree_Inorder_Traversal {

    /*
    Thinking:
    - DFS
    - recursive
     */
    class Solution1 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            inorder(root, res);
            return res;
        }
        private void inorder(TreeNode root, List<Integer> res) {
            if (root == null) return;
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }
    }


    /*
    Thinking:
    - BFS
    - iterative
     */
    class Solution2 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {  // find the most right node, like DFS
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
            return res;
        }
    }
}
