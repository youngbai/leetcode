package edu.neu.leetcode.day24_tree.p1_traversal;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.*;

public class LC145_Binary_Tree_Postorder_Traversal {


    class Solution1_Recursion {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            helper(root, res);
            return res;
        }

        private void helper(TreeNode root, List<Integer> res) {
            if (root == null) return;
            helper(root.left, res);
            helper(root.right, res);
            res.add(root.val);
        }
    }


    // 1. root, right, left
    // 2. reverse the result, it will become left, right, root, which is the postorder
    class Solution2_Iteration {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> stack = new ArrayDeque<>();
            if (root != null) stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();            // access root
                res.add(root.val);             // add val
                if (root.left != null) stack.push(root.left);   // push left
                if (root.right != null) stack.push(root.right); // push right
            }

            Collections.reverse(res);   // Note: MUST reverse the order
            return res;
        }
    }
}
