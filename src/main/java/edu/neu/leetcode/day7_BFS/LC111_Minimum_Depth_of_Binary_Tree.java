package edu.neu.leetcode.day7_BFS;

import commonbean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LC111_Minimum_Depth_of_Binary_Tree {

    /*
    Thinking:
    - BFS
    - Start at root and traverse the tree in level order until you hit the first leaf
     */
    class Solution1 {
        public int minDepth(TreeNode root) {
            if (root == null) return 0;
            Queue<TreeNode> q = new LinkedList<>();
            int depth = 1;
            q.offer(root);
            while (!q.isEmpty()) {  // traverse current layer
                int size = q.size();    // size: number of nodes of current layer
                for (int i = 0; i < size; i++) {
                    TreeNode t = q.poll();
                    if (t.left == null && t.right == null) return depth;
                    if (t.left != null) q.offer(t.left);
                    if (t.right != null) q.offer(t.right);
                }
                depth++;
            }
            return depth;  // In fact, we can never reach this code
        }
    }


    /*
    Thinking:
    - DFS, recursive
    - minDepth(root) = min(minDepth(root.left), minDepth(root.right)) + 1
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
