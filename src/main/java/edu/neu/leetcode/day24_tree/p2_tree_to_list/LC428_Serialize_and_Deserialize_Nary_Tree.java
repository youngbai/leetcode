package edu.neu.leetcode.day24_tree.p2_tree_to_list;

import java.util.*;

public class LC428_Serialize_and_Deserialize_Nary_Tree {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };


    /*
    Tree - preorder
    [val, num of children, val, num of children, ... ]
     */
    class Codec1_preorder {
        // Encodes a tree to a single string.
        public String serialize(Node root) {
            List<String> res = new ArrayList<>();
            dfs(root, res);
            return String.join(",", res);       // NOTE: String.join(delimiter, array or list)
        }

        // preorder
        private void dfs(Node root, List<String> res) {
            // base case
            if (root == null) return;

            res.add(String.valueOf(root.val));               // root
            res.add(String.valueOf(root.children.size()));   // number of children
            for (Node child: root.children) dfs(child, res); // dfs children
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if ("".equals(data)) return null;
            Queue<String> q = new ArrayDeque<>(Arrays.asList(data.split(",")));
            return dfs(q);
        }

        private Node dfs(Queue<String> q) {
            String s = q.poll();                        // root
            int size = Integer.valueOf(q.poll());       // number of children
            Node root = new Node(Integer.valueOf(s), new ArrayList<>(size));
            for (int i = 0; i < size; i++)
                root.children.add(dfs(q));
            return root;
        }

    }

}
