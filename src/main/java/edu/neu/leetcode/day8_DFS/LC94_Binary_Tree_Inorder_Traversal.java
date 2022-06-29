package edu.neu.leetcode.day8_DFS;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.*;

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
            inorder(root.left, res);    // left
            res.add(root.val);          // root
            inorder(root.right, res);   // right
        }
    }


    /*
    Thinking:
    - DFS
    - iterative
     */
    class Solution2 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {  // push left, find the most right node, like DFS
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();     // access root
                res.add(root.val);
                root = root.right;      // iterate right
            }
            return res;
        }
    }
}
