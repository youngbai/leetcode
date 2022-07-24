package edu.neu.leetcode.day7_BFS;

import edu.neu.leetcode.commonbean.TreeNode;

import java.util.*;

public class LC863_All_Nodes_Distance_K_in_Binary_Tree {

    /*
    Thinking:
    - BFS (like level traversal)
    - next level node could be parent, could be child
    - use Set to prevent going back

    Time:  O(N)
    Space: O(N)
    */
    class Solution1_BFS {
        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            List<Integer> res = new ArrayList<>();

            // build map(node->its parent node)
            Map<TreeNode, TreeNode> parent = new HashMap<>();
            dfs(root, null, parent);

            // BFS (like level traversal)
            Set<TreeNode> set = new HashSet<>();
            Queue<TreeNode> q = new LinkedList<>();

            q.add(target);      // start from target
            set.add(target);    // mark target visited

            int distance = 0;
            while (!q.isEmpty()) {
                // reach to the k distance/level
                if (distance == k) {
                    for (TreeNode node : q) res.add(node.val);
                    return res;
                }

                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();

                    // add unvisited parent
                    TreeNode curParent = parent.get(cur);
                    if (curParent != null && set.add(curParent)) q.offer(curParent);

                    // add unvisited children
                    if (cur.left != null && set.add(cur.left)) q.offer(cur.left);
                    if (cur.right != null && set.add(cur.right)) q.offer(cur.right);
                }
                distance++;
            }
            return res;
        }

        private void dfs(TreeNode root, TreeNode p, Map<TreeNode, TreeNode> parent) {
            if (root == null) return;
            parent.put(root, p);
            dfs(root.left, root, parent);
            dfs(root.right, root, parent);
        }
    }
}
