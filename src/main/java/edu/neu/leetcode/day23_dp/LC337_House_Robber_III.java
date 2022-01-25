package edu.neu.leetcode.day23_dp;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC337_House_Robber_III {


    /*
    Thinking:
    - tree, recursion, DP

    Intuition:
    - Tree -> Recursion
    - Recursion typically has repeated calculation, so use DP to optimize it

    Algo1:
    - recursion helper() receives a node as input, and returns the maximum amount the thief can get starting from this node.
    - rob parent, not rob current node
    - not rob parent, rob current node or not, return the max
    - too much repeated calculation
    class Solution {	// TLE, only recursion, no DP
        public int rob(TreeNode root) {
            return helper(root, false);
        }

        private int helper(TreeNode node, boolean parentRob) {
            // base case
            if (node == null) return 0;

            // rob parent, not rob current node
            if (parentRob) {
                return helper(node.left, false) + helper(node.right, false);	// repeated calculation
            }

            // not rob parent, rob current node or not, return the max
            if (!parentRob) {
                int rob = node.val + helper(node.left, true) + helper(node.right, true);
                int notRob = helper(node.left, false) + helper(node.right, false);	// repeated calculation
                return Math.max(rob, notRob);
            }
            return 0;
        }
    }

    - repeated calculation:
    helper(node.left.left, False) is called inside helper(node.left, True), and also is called inside helper(node.left, False). It is calculated twice! We do not want that.
    We return the results of helper(node.left, True) and helper(node.left, False) in a single function: helper(node.left). Those two results can be stored in a two-element array.
    `
    function helper(node) {
        // return original [`helper(node.left, True)`, `helper(node.left, False)`]
        tackle basic case...
        left = helper(node.left)
        right = helper(node.right)
        some calculation...
        return [max_if_rob, max_if_not_rob]
    }
    `

    Algo2:(Best)
    class Solution {    // DP
        public int rob(TreeNode root) {
            int[] ans = helper(root);
            return Math.max(ans[0], ans[1]);
        }

        // return [rob this node, not rob this node]
        private int[] helper(TreeNode node) {
            // base case
            if (node == null) return new int[]{0, 0};

            int[] left = helper(node.left);
            int[] right = helper(node.right);

            // if we rob this node, we cannot rob its children
            int rob = node.val + left[1] + right[1];
            // else, we free to choose rob its children or not
            int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

            return new int[]{rob, notRob};
        }
    }

    Time:  O(N), since we visit all nodes once.
    Space: O(N), since we need stacks to do recursion, and the maximum depth of the recursion is the height of
           the tree, which is O(N) in the worst case and O(log(N)) in the best case.
     */
    class Solution1_DP {
        public int rob(TreeNode root) {
            int[] ans = helper(root);
            return Math.max(ans[0], ans[1]);
        }

        // return [rob this node, not rob this node]
        private int[] helper(TreeNode node) {
            // base case
            if (node == null) return new int[]{0, 0};

            int[] left = helper(node.left);
            int[] right = helper(node.right);

            // if we rob this node, we cannot rob its children
            int rob = node.val + left[1] + right[1];
            // else, we free to choose rob its children or not
            int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

            return new int[]{rob, notRob};
        }
    }
}
