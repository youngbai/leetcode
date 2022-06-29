package edu.neu.leetcode.day24_tree.p2_tree_to_list;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LC105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {

    /*
    - Tree, preorder, inorder
    - preorder: first element is the root of all tree
    - inorder: find the index of the root, then dfs left, and dfs right

    Time:  O(N^2)
    Space: O(logN)
     */
    class Solution1_preorder {
        int[] preorder;
        int[] inorder;
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            this.inorder = inorder;
            return dfs(0, 0, preorder.length - 1);  // O(N)
        }

        private TreeNode dfs(int preorderIndex, int inStart, int inEnd) {
            // base case
            if (inStart > inEnd) return null;

            int rootVal = preorder[preorderIndex];                                                          // root
            int rootIndexInorder = 0;  // root index in inorder
            for (int i = inStart; i <= inEnd; i++)              // O(N)
                if (inorder[i] == rootVal) rootIndexInorder = i;
            int leftTreeSize = rootIndexInorder - inStart;

            TreeNode root = new TreeNode(rootVal);
            root.left = dfs(preorderIndex + 1, inStart, rootIndexInorder - 1);                  // left
            root.right = dfs(preorderIndex + 1 + leftTreeSize, rootIndexInorder + 1, inEnd);   // right
            return root;
        }
    }


    /*
    - Tree, preorder, inorder
    - preorder: first element is the root of all tree
    - inorder: find the index of the root, then dfs left, and dfs right

    Time:  O(N), inorderMap.get(root.val) --> O(1)
    Space: O(N), map
     */
    class Solution2_preorder {
        int[] preorder;
        int[] inorder;
        int preorderIndex;
        Map<Integer, Integer> inorderMap = new HashMap<>(); // inorder value -> inorder index
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            this.inorder = inorder;
            this.preorderIndex = 0;
            int N = preorder.length;
            for (int i = 0; i < N; i++) inorderMap.put(inorder[i], i);
            return dfs(0, N - 1);       // O(N)
        }

        private TreeNode dfs(int inStart, int inEnd) {
            // base case
            if (inStart > inEnd) return null;

            TreeNode root = new TreeNode(preorder[preorderIndex++]);    // root
            int rootIndexInorder = inorderMap.get(root.val);    // O(1)
            root.left = dfs(inStart, rootIndexInorder - 1);       // left
            root.right = dfs(rootIndexInorder + 1, inEnd);       // right
            return root;
        }
    }

    /*
    Thinking:
    - same algorithm, but better understanding

     */
    class Solution3 {
        int[] pre;
        int[] in;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.pre = preorder;
            this.in = inorder;
            return dfs(0, preorder.length - 1, 0, inorder.length - 1);
        }

        private TreeNode dfs(int prestart, int preend, int instart, int inend) {
            // base case
            if (prestart > preend || instart > inend) return null;

            // root index in inorder
            int rootIndexInOrder = 0;
            for (int i = instart; i <= inend; i++) {
                if (pre[prestart] == in[i]) rootIndexInOrder = i;
            }

            // left sub tree length, right sub tree length
            int leftSubtreeLength = rootIndexInOrder - instart;
            int rightSubtreeLength = inend - rootIndexInOrder;
            int leftprestart = prestart + 1, leftpreend = prestart + leftSubtreeLength;
            int leftinstart = instart, leftinend = rootIndexInOrder - 1;
            int rightprestart = leftpreend + 1, rightpreend = rightprestart + rightSubtreeLength - 1;
            int rightinstart = rootIndexInOrder + 1, rightinend = inend;

            TreeNode root = new TreeNode(pre[prestart]);
            // System.out.println("root:"+ pre[prestart]);
            // System.out.println("left:--->" + leftprestart + "," + leftpreend + "," + leftinstart + "," + leftinend);
            root.left = dfs(leftprestart, leftpreend, leftinstart, leftinend);
            // System.out.println("right:--->" + rightprestart + "," + rightpreend + "," + rightinstart + "," + rightinend);
            root.right = dfs(rightprestart, rightpreend, rightinstart, rightinend);
            return root;
        }
    }
}
