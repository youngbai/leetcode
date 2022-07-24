package edu.neu.leetcode.day24_tree.p4_LCA;

import java.util.HashSet;
import java.util.Set;

public class LC1650_Lowest_Common_Ancestor_of_a_Binary_Tree_III {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }


    /*
    - if set add fail, then it is the LCA
     */
    class Solution1_Set {
        public Node lowestCommonAncestor(Node p, Node q) {
            Set<Node> set = new HashSet<>();
            while (true) {
                if (p != null && !set.add(p)) return p;     // add p
                if (q != null && !set.add(q)) return q;     // add q
                if (p != null) p = p.parent;
                if (q != null) q = q.parent;
            }
        }
    }


    /*
    - same as 'finding the joint of two linked list'
    - Ref LC160
     */
    class Solution2_Find_Joint_Two_LinkedList {
        public Node lowestCommonAncestor(Node p, Node q) {
            Node a = p, b = q;
            while (a != b) {
                if (a == null) a = q;
                else a = a.parent;
                if (b == null) b = p;
                else b = b.parent;
            }
            return a;
        }
    }
}
