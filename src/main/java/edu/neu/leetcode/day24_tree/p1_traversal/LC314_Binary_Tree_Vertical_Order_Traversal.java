package edu.neu.leetcode.day24_tree.p1_traversal;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.*;

public class LC314_Binary_Tree_Vertical_Order_Traversal {

    /*
    Thinking:
    - BFS

    Algo:
    - 1.BFS each node, which guarantees the order of nodes in the same column
    - 2.when BFS, maintain two Map(col->elements of this column), Map(node->col)
    - 3.Map(col->elements of this column) -> result
     */
    class Solution1_BFS {
        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            Map<Integer, List<Integer>> cols = new HashMap<>(); // col -> nodes in this col
            Map<TreeNode, Integer> colNums = new HashMap<>();   // node -> col

            Deque<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            colNums.put(root, 0);
            int min = 0;
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                int colNum = colNums.get(cur);
                cols.computeIfAbsent(colNum, x -> new ArrayList<Integer>()).add(cur.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                    colNums.put(cur.left, colNum - 1);
                    min = Math.min(min, colNum - 1);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                    colNums.put(cur.right, colNum + 1);
                }
            }

            // map to list (because the col number is continuous from min until cols.containsKey() return null)
            while (cols.containsKey(min)) res.add(cols.get(min++));
            return res;
        }
    }


    // DFS + TreeMap
    class Solution2_DFS {
        public List<List<Integer>> verticalOrder(TreeNode root) {
            Map<Integer, List<int[]>> cols = new TreeMap<>(); // colNum - list of nodes(depth, node)
            dfs(root, cols, 0, 0);
            List<List<Integer>> res = new ArrayList<>();
            for (List<int[]> nodes : cols.values()) {
                nodes.sort((node1, node2) -> node1[0] - node2[0]);
                List<Integer> tmp = new ArrayList<>();
                for (int[] node: nodes) tmp.add(node[1]);
                res.add(tmp);
            }
            return res;
        }

        private void dfs(TreeNode root, Map<Integer, List<int[]>> cols, int depth, int colNum) {
            // base case
            if (root == null) return;

            // if (!cols.containsKey(colNum)) cols.put(colNum, new ArrayList<int[]>());
            // cols.get(colNum).add(new int[]{depth, root.val});
            cols.computeIfAbsent(colNum, x -> new ArrayList<int[]>()).add(new int[]{depth, root.val}); // the same as up code

            if (root.left != null) dfs(root.left, cols, depth + 1, colNum - 1);
            if (root.right != null) dfs(root.right, cols, depth + 1, colNum + 1);
        }

    }


}
