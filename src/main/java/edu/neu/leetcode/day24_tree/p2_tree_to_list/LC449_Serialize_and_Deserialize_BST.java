package edu.neu.leetcode.day24_tree.p2_tree_to_list;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class LC449_Serialize_and_Deserialize_BST {


    /*
    Tree - preorder
    lower bound, upper bound
     */
    public class Codec1_Preorder {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";
            String res = String.valueOf(root.val);                      // root
            if (root.left != null) res += "," + serialize(root.left);   // left
            if (root.right != null) res += "," + serialize(root.right); // right
            return res;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            Queue<String> q = new ArrayDeque<>(Arrays.asList(data.split(",")));
            return dfs(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private TreeNode dfs(Queue<String> q, int lower, int upper) {
            // base case
            if (q.isEmpty()) return null;
            int val = Integer.valueOf(q.peek());
            if (val < lower || val > upper) return null;    // this val is out of legal boundary

            q.poll();                                       // this val is in legal boundary, then use this val
            TreeNode root = new TreeNode(val);  // root
            root.left = dfs(q, lower, val);     // left
            root.right = dfs(q, val, upper);    // right
            return root;
        }
    }


    /*
    Tree - preorder
    only upper bound
     */
    public class Codec2_preorder {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            // base case
            if (root == null) return "";
            String res = String.valueOf(root.val);                      // root
            if (root.left != null) res += "," + serialize(root.left);   // left
            if (root.right != null) res += "," + serialize(root.right); // right
            return res;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            // corner case
            if (data == null || data.length() == 0) return null;

            int[] A = Arrays.stream(data.split(",")).mapToInt(Integer::valueOf).toArray();
            return dfs(A, Integer.MAX_VALUE);
        }

        int i = 0;
        private TreeNode dfs(int[] A, int upper) {
            // base case
            if (i == A.length || A[i] > upper) return null;

            TreeNode root = new TreeNode(A[i++]);   // root
            root.left = dfs(A, root.val);           // left
            root.right = dfs(A, upper);             // right
            return root;
        }
    }

}
