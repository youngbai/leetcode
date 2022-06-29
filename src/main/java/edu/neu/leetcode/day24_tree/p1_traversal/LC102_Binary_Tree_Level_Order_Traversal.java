package edu.neu.leetcode.day24_tree.p1_traversal;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.*;

public class LC102_Binary_Tree_Level_Order_Traversal {

    /*
    Thinking:
    - BFS

    Time:  O(N)
    Space: O(N), Deque
     */
    class Solution1_Iteration {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            Deque<TreeNode> q = new ArrayDeque<>();
            if (root != null) q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> level = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    level.add(cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                res.add(level);
            }
            return res;
        }
    }

    /*
    Thinking:
    - DFS

    Time : O(N)
    Space: O(logN), invoking stack
     */
    class Solution2_Recursion {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(root, res, 0);
            return res;
        }

        private void dfs(TreeNode root, List<List<Integer>> res, int height) {
            if (root == null) return ;
            if (height == res.size()) res.add(new ArrayList<>());
            res.get(height).add(root.val);
            if (root.left != null) dfs(root.left, res, height + 1);
            if (root.right != null) dfs(root.right, res, height + 1);
        }
    }


    /*
    Return one list after traverse all level
     */
    class Solution {
        public List<TreeNode> levelOrder(TreeNode root) {
            List<TreeNode> res = new ArrayList<>();
            int slow = 0;
            res.add(root);
            while (slow < res.size()) {
                TreeNode cur = res.get(slow++);
                if (cur.left != null) res.add(cur.left);
                if (cur.right != null) res.add(cur.right);
            }
            res.forEach(e -> System.out.println(e.val));
            return res;
        }
    }

}
