package edu.neu.leetcode.day24_tree.p5_Backtracking_PureRecursion;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LC257_Binary_Tree_Paths {

    /*
    - DFS
    - Backtracking

    Time: O(N)
    Space: O(logN), height of tree
     */
    class Solution1_DFS_Backtracking {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            if (root != null) dfs(root, String.valueOf(root.val), res);
            return res;
        }

        private void dfs(TreeNode root, String path, List<String> res) {
            // base case
            if (root.left == null && root.right == null) {  // leaf node
                res.add(path);
                return;
            }

            if (root.left != null) dfs(root.left, path + "->" + root.left.val, res);
            if (root.right != null) dfs(root.right, path + "->" + root.right.val, res);
        }
    }


    /*
    - BFS
    - two Queue
      - store nodes
      - store according path
     */
    class Solution2_BFS {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            if (root == null) return res;

            Deque<TreeNode> q = new ArrayDeque<>();
            Deque<String> path = new ArrayDeque<>();
            q.offer(root);
            path.offer(String.valueOf(root.val));
            while (!q.isEmpty()) {
                root = q.poll();
                String curPath = path.poll();

                if (root.left == null && root.right == null) res.add(curPath);
                if (root.left != null) {
                    q.offer(root.left);
                    path.offer(curPath + "->" + String.valueOf(root.left.val));
                }
                if (root.right != null) {
                    q.offer(root.right);
                    path.offer(curPath + "->" + String.valueOf(root.right.val));
                }
            }
            return res;
        }
    }
}
