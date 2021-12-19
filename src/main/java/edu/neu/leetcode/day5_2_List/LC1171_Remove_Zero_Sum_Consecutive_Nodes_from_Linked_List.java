package edu.neu.leetcode.day5_2_List;

import commonbean.ListNode;

import java.util.HashMap;
import java.util.Map;

public class LC1171_Remove_Zero_Sum_Consecutive_Nodes_from_Linked_List {

    /*
    Thinking:
    - One pass
    - prefix sum problem

    eg. [section1] [section2] [section3]
               n1          n2
    - section1: prefix sum is NOT 0, last node is n1
    - section2: sum(section2) is 0, last node is n2
    - section3: unchecked elements
    So, prefix(n1) equals to prefix(n2), because sum(section2) is 0.
    How to get the hat of section2? (hat.next is head)
    - maintain a map(prefix, node)
    - map.get(prefix of n2) is the hat which is n1

    Ref: https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/discuss/366319/JavaC%2B%2BPython-Greedily-Skip-with-HashMap

    Caution (corner case):
    - Initially, cur must point to dummy, because the prefix sum might be 0.
        e.g dummy -> 1 -> -1
        Then cur -> -1, the prefix is 0, we need to map.get(prefix)
    - The inner while loop is used to remove the cached prefix mapping, because the between nodes are removed
     */
    class Solution1_1 {
        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode dummy = new ListNode(0, head), cur = dummy;
            int prefix = 0;
            Map<Integer, ListNode> m = new HashMap<>();
            while (cur != null) {
                prefix += cur.val;
                if (m.containsKey(prefix)) {
                    cur = m.get(prefix).next;   // now cur is the head of section2
                    int p = prefix + cur.val;
                    while (p != prefix) {   // when p equals prefix, cur is the tail of section2
                        m.remove(p);        // remove the cached mapping
                        cur = cur.next;
                        p += cur.val;
                    }
                    m.get(prefix).next = cur.next;  // remove section2
                } else {
                    m.put(prefix, cur);
                }
                cur = cur.next;
            }
            return dummy.next;
        }
    }

    /*
    Thinking:
    - same algo with Solution1_1
    - inner while loop is more readable

    Ref: https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/discuss/366319/JavaC%2B%2BPython-Greedily-Skip-with-HashMap
     */
    class Solution1_2 {
        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode dummy = new ListNode(0, head), cur = dummy;
            int prefix = 0;
            Map<Integer, ListNode> map = new HashMap<>();
            while (cur != null) {
                prefix += cur.val;
                if (map.containsKey(prefix)) {
                    // n1 - tail of section1
                    // n2 - tail of section2
                    // Goal - remove the cached mapping,
                    //      - then remove section2
                    ListNode n1 = map.get(prefix), n2 = cur; cur = n1.next;
                    int p = prefix;
                    while (cur != n2) { // when cur equals n2, quit the loop
                        p += cur.val;
                        map.remove(p);  // remove the cached mapping
                        cur = cur.next;
                    }
                    n1.next = n2.next;  // remove section2
                    cur = n2.next;
                } else {
                    map.put(prefix, cur);
                    cur = cur.next;
                }
            }
            return dummy.next;
        }
    }


    /*
    Thinking: Two pass, prefix sum problem
    - first pass, cached all prefix sum
    - second pass, skip the section whose sum is 0
     */
    class Solution2 {
        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode dummy = new ListNode(0, head);
            Map<Integer, ListNode> map = new HashMap<>();

            // first pass, cached all prefix sum
            int prefix = 0;
            for (ListNode i = dummy; i != null; i = i.next) {
                prefix += i.val;
                map.put(prefix, i); // if prefix is same, keep the last one
            }

            // second pass, skip the section whose sum is 0
            prefix = 0;
            for (ListNode i = dummy; i != null; i = i.next) {
                prefix += i.val;
                i.next = map.get(prefix).next;
            }
            return dummy.next;
        }
    }

}
