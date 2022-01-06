package edu.neu.leetcode.day7_BFS;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC102_Binary_Tree_Level_Order_Traversal {


    /*
    Thinking:
    - BFS
     */
    class Solution1 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> level = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    TreeNode t = q.poll();
                    level.add(t.val);
                    if (t.left != null) q.offer(t.left);
                    if (t.right != null) q.offer(t.right);
                }
                res.add(level);
            }
            return res;
        }
    }

    /*
    Thinking:
    - DFS, recursive
     */
    class Solution2 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(root, res, 0);
            return res;
        }

        public void dfs(TreeNode root, List<List<Integer>> res, int height) {
            if (root == null) return;
            if (height == res.size()) res.add(new ArrayList<Integer>());
            res.get(height).add(root.val);
            if (root.left != null) dfs(root.left, res, height + 1);
            if (root.right != null) dfs(root.right, res, height + 1);
        }
    }
}
