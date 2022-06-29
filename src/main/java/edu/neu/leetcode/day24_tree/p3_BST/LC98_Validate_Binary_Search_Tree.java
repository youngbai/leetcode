package edu.neu.leetcode.day24_tree.p3_BST;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC98_Validate_Binary_Search_Tree {

    /*
    - lower & upper bound
     */
    class Solution1_Lower_Upper_Bound {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        private boolean isValidBST(TreeNode root, Integer min, Integer max) {
            if (root == null) return true;
            if (min != null && root.val <= min) return false;
            if (max != null && root.val >= max) return false;
            return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
        }
    }


    /*
    - Inorder
    - Recursion
     */
    class Solution2_Inorder_Recursion {
        // the value of node can be -2^31 which is Integer.MIN_VALUE,
        // and the pre should be less than the MIN_VALUE, so we should use Long
        long pre = Long.MIN_VALUE;
        public boolean isValidBST(TreeNode root) {
            // base case
            if (root == null) return true;

            // inorder
            boolean left = isValidBST(root.left);       // left
            if (!left) return false;
            if (pre >= root.val) return false;          // root
            pre = root.val;
            return isValidBST(root.right);              // right
        }
    }


    /*
    - Inorder
    - Iteration
     */
    class Solution3_Inorder_Iteration {
        public boolean isValidBST(TreeNode root) {
            TreeNode pre = null;
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {  // go to the very left
                    stack.push(root);
                    root = root.left;
                }

                root = stack.pop();     // root
                if (pre != null && pre.val >= root.val) return false;
                pre = root;

                root = root.right;               // right
            }
            return true;
        }
    }

}
