package edu.neu.leetcode.day5_2_List;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LC426_BST_to_Sorted_Doubly_Linked_List {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /*
    Thinking:
    - inorder traverse the BST, Left->Root->Right
    - iteration
     */
    class Solution1 {

        public Node treeToDoublyList(Node root) {
            // base case
            if (root == null) return null;

            // inorder
            Node first = null, pre = null;
            Deque<Node> stack = new ArrayDeque<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (first == null) first = root;
                if (pre != null) {
                    pre.right = root;
                    root.left = pre;
                }
                pre = root;
                root = root.right;
            }   // in the end, pre will be the last element, root will be null

            // connect first element and last element
            first.left = pre;
            pre.right = first;
            return first;
        }
    }

    /*
    Thinking:
    - inorder traverse
    - recursion
     */
    class Solution2 {
        Node head = null, pre = null;
        public Node treeToDoublyList(Node root) {
            if (root == null) return null;
            inorderTraverse(root);
            head.left = pre;
            pre.right = head;
            return head;
        }
        private void inorderTraverse(Node root) {
            if (root == null) return;
            inorderTraverse(root.left);     // left

            if (head == null) head = root;  // find head

            if (pre != null) {              // root
                pre.right = root;
                root.left = pre;
            }
            pre = root;

            inorderTraverse(root.right);    // right
        }
    }

    @Test
    public void test1() {
        int[] array = new int[]{4, 2, 5, 1, 3};
        testAlgo(array);

        array = new int[]{2, 1, 3};
        testAlgo(array);

        array = new int[]{};
        Node root = BST(array);
        Solution1 s = new Solution1();
        Node head = s.treeToDoublyList(root);
        assertNull(head);
    }

    private void testAlgo(int[] array) {
        Node root = BST(array);
        printBST(root);
        Solution1 s = new Solution1();
        Node head = s.treeToDoublyList(root);
        Arrays.sort(array);
        assertTrue(checkEquals(head, array));
    }

    private void printBST(Node root) {
        if (root == null) return;
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            sb.append(cur.val).append(",");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        System.out.println(sb);
    }

    private boolean checkEquals(Node cur, int[] array) {
        // traverse in order
        for (int e : array) {
            if (e != cur.val) return false;
            cur = cur.right;
        }
        if (cur.val != array[0]) return false;

        // traverse reversely
        cur = cur.left;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != cur.val) return false;
            cur = cur.left;
        }
        return true;
    }

    private Node BST(int[] array) {
        if (array == null || array.length == 0) return null;
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(array[0], null, null);
        queue.offer(root);
        int i = 0;
        while (!queue.isEmpty() && i < array.length) {
            Node cur = queue.poll();
            if (++i < array.length)  {
                cur.left = new Node(array[i], null, null);
                queue.offer(cur.left);
            }
            if (++i < array.length) {
                cur.right = new Node(array[i], null, null);
                queue.offer(cur.right);
            }
        }
        return root;
    }


}
