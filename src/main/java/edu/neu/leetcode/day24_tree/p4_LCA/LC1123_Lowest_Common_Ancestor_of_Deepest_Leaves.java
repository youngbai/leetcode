package edu.neu.leetcode.day24_tree.p4_LCA;

import edu.neu.leetcode.commonbean.TreeNode;

public class LC1123_Lowest_Common_Ancestor_of_Deepest_Leaves {

    /*
    Time:  O(N^2)
        - lcaDeepestLeaves() is O(N)
        - height() is O(N)
    Space: O(logN), logN is height of tree
     */
    class Solution1_Recursion_Naive {
        // O(N)
        public TreeNode lcaDeepestLeaves(TreeNode root) {
            if (root == null) return null;
            int left = height(root.left);       // left
            int right = height(root.right);     // right

            // root
            if (left == right) return root;                             // case 1
            else if (left > right) return lcaDeepestLeaves(root.left);  // case 2
            else return lcaDeepestLeaves(root.right);                   // case 3
        }

        // O(N)
        private int height(TreeNode root) {
            if (root == null) return 0;
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }

    /*
    - pure recursion: from bottom to top
    - Postorder

    Time:  O(N), walk through each node ONLY ONCE
    Space: O(logN), height of tree
     */
    class Solution2_Pure_Recursion {
        public TreeNode lcaDeepestLeaves(TreeNode root) {
            Pair p = dfs(root, 0);
            return p.node;
        }

        private Pair dfs(TreeNode root, int d) {
            if (root == null) return new Pair(null, d);
            Pair l = dfs(root.left, d + 1);     // left
            Pair r = dfs(root.right, d + 1);    // right

            // root
            if (l.d == r.d) return new Pair(root, l.d); // case 1
            else if (l.d > r.d) return l;               // case 2
            else return r;                              // case 3
        }
    }

    class Pair {
        TreeNode node;      // node is the LCA
        int d;              // d is the depth of the leaves under the node
        Pair (TreeNode node, int d) {
            this.node = node;
            this.d = d;
        }
    }
}
