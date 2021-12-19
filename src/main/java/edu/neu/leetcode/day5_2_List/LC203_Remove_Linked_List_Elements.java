package edu.neu.leetcode.day5_2_List;

import edu.neu.leetcode.commonbean.ListNode;

public class LC203_Remove_Linked_List_Elements {


    /*
    Iteration
    - pre cur next
     */
    class Solution1 {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null) return null;
            ListNode dummy = new ListNode(0, head);
            ListNode pre = dummy, cur = head;

            while (cur != null) {
                ListNode next = cur.next;
                if (cur.val == val) {
                    pre.next = next;
                    cur = next;
                } else {
                    pre = cur;
                    cur = next;
                }
            }

            return dummy.next;
        }
    }

    /*
    Iteration:
    - always check cur.next, NOT cur
     */
    class Solution2 {
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(0, head);
            ListNode cur = dummy;
            while (cur.next != null) { // always check cur.next, NOT cur
                if (cur.next.val == val) cur.next = cur.next.next;
                else cur = cur.next;
            }
            return dummy.next;
        }
    }


    /*
    Recursion
     */
    class Solution3 {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null) return null;
            head.next = removeElements(head.next, val);
            return (head.val == val)? head.next : head;
        }
    }
}
