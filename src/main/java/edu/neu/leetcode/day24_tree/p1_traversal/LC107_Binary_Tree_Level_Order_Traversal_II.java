package edu.neu.leetcode.day24_tree.p1_traversal;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.*;

public class LC107_Binary_Tree_Level_Order_Traversal_II {

    /*
    Thinking:
    - BFS

    Time:  O(N)
    Space: O(N), Deque
     */
    class Solution1_Iteration {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
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

            Collections.reverse(res);   // only difference with LC102
            return res;
        }
    }


    /*
    Thinking:
    - DFS
     */
    class Solution2_Recursion {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(root, res, 0);
            Collections.reverse(res);   // difference with LC102
            return res;
        }

        private void dfs(TreeNode root, List<List<Integer>> res, int height) {
            if (root == null) return;
            if (height == res.size()) res.add(new ArrayList<Integer>());
            res.get(height).add(root.val);
            if (root.left != null) dfs(root.left, res, height + 1);
            if (root.right != null) dfs(root.right, res, height + 1);
        }
    }

}
