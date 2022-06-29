package edu.neu.leetcode.day24_tree.p2_tree_to_list;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /*
    - inorder
    - iteration
     */
    class Solution1_Inorder_Iteration {
        public Node treeToDoublyList(Node root) {
            if (root == null) return null;
            Deque<Node> stack = new ArrayDeque<>();
            Node head = null, pre = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {      // go to the very left
                    stack.push(root);
                    root = root.left;
                }

                root = stack.pop();         // root
                if (head == null) head = root;
                if (pre != null) {   // connect pre<--> root(current node)
                    pre.right = root;
                    root.left = pre;
                }
                pre = root;          // root(current node) becomes pre
                root = root.right;          // right
            }

            // connect head <--> pre(tail)
            head.left = pre;
            pre.right = head;
            return head;
        }
    }

    /*
    - inorder
    - recursion
     */
    class Solution2_Inorder_Recursion {

        Node head = null, pre = null;
        public Node treeToDoublyList(Node root) {
            if (root == null) return null;
            dfs(root);
            head.left = pre;
            pre.right = head;
            return head;
        }

        private void dfs(Node root) {
            // base case
            if (root == null) return;
            dfs(root.left);                 // left

            if (head == null) head = root;  // root: set head
            if (pre != null) {              // connect pre <--> root
                pre.right = root;
                root.left = pre;
            }
            pre = root;

            dfs(root.right);                // right
        }
    }

    /*
    - inorder
    - recursion
     */
    class Solution3_Inorder_Recursion {
        public Node treeToDoublyList(Node root) {
            if (root == null) return null;
            Node[] res = dfs(root);
            res[0].left = res[1];   // res[0] is the head
            res[1].right = res[0];  // res[1] is the tail
            return res[0];
        }

        // return [min node, max node]
        private Node[] dfs(Node root) {
            // base case
            if (root == null) return null;

            Node[] leftRes = dfs(root.left);
            Node[] rightRes = dfs(root.right);

            Node min = null, max = null;
            if (leftRes == null) min = root;
            else min = leftRes[0];
            if (rightRes == null) max = root;
            else max = rightRes[1];

            // build list
            if (leftRes != null) {
                leftRes[1].right = root;
                root.left = leftRes[1];
            }
            if (rightRes != null) {
                root.right = rightRes[0];
                rightRes[0].left = root;
            }

            return new Node[]{min, max};
        }
    }


}
