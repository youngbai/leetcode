package edu.neu.leetcode.day24_tree.p3_BST;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC95_Unique_Binary_Search_Trees_II {

    /*
     - like LC96
     - use DFS, not DP, because we wanna the all possible combination

     Time: Catalan number
     */
    class Solution1_DFS {
        public List<TreeNode> generateTrees(int n) {
            if (n == 0) return new ArrayList<TreeNode>();
            return dfs(1, n);
        }

        private List<TreeNode> dfs(int start, int end) {
            List<TreeNode> res = new ArrayList<>();
            // base case
            if (start > end) res.add(null);

            for (int i = start; i <= end; i++) {
                List<TreeNode> leftList = dfs(start, i - 1);
                List<TreeNode> rightList = dfs(i + 1, end);
                for (TreeNode left : leftList) {
                    for (TreeNode right : rightList) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }
            return res;
        }
    }
}
