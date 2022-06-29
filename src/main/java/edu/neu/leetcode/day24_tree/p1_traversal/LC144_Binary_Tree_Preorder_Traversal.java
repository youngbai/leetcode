package edu.neu.leetcode.day24_tree.p1_traversal;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;

public class LC144_Binary_Tree_Preorder_Traversal {

    /*
    Thinking:
    - tree preorder

    Time:  O(N)
    Space: O(logN)
     */
    class Solution1_Recursion {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            helper(root, res);
            return res;
        }

        private void helper(TreeNode root, List<Integer> res) {
            if (root == null) return;
            res.add(root.val);
            helper(root.left, res);
            helper(root.right, res);
        }
    }

    class Solution2_Iteration {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();

            Deque<TreeNode> stack = new ArrayDeque<>();
            if (root != null) stack.push(root);     // Corner Case: root is null

            while (!stack.isEmpty()) {
                root = stack.pop();     // access root
                res.add(root.val);
                if (root.right != null) stack.push(root.right); // push right
                if (root.left != null) stack.push(root.left);   // push left
            }
            return res;
        }
    }



}
