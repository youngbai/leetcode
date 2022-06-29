package edu.neu.leetcode.day24_tree.p2_tree_to_list;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class LC297_Serialize_and_Deserialize_Binary_Tree {

    /*
    Tree - preorder

    Time:  O(N)
    Space: O(N), Queue
     */
    public class Codec1_Preorder {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "#";    // base case
            return root.val + "," + serialize(root.left) + "," + serialize(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            System.out.println(data);
            Queue<String> q = new ArrayDeque<>(Arrays.asList(data.split(",")));     // NOTE:Arrays.asList(),   ArrayDeque<>(List)
            return helper(q);
        }

        private TreeNode helper(Queue<String> q) {
            String s = q.poll();
            if (s.equals("#")) return null;
            TreeNode root = new TreeNode(Integer.valueOf(s));
            root.left = helper(q);
            root.right = helper(q);
            return root;
        }
    }
}
