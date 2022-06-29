package edu.neu.leetcode.day24_tree.p3_BST;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC108_Convert_Sorted_Array_to_Binary_Search_Tree {

    /*
    Divide and Conquer
     */
    class Solution1_BST {
        public TreeNode sortedArrayToBST(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }

        private TreeNode helper(int[] nums, int start, int end) {
            // base case
            if (start > end) return null;
            int mid = start + (end - start) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = helper(nums, start, mid - 1);
            root.right = helper(nums, mid + 1, end);
            return root;
        }
    }
}
