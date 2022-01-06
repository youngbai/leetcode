package edu.neu.leetcode.day5_1_List;

import edu.neu.leetcode.commonbean.ListNode;

public class LC206_Reverse_Linked_List {

    // recursion
    class Solution1 {
        public ListNode reverseList(ListNode head) {
            // recursive solution
            return reverseListInt(null, head);
        }

        private ListNode reverseListInt(ListNode pre, ListNode cur) {
            if (cur == null) return pre;
            ListNode next = cur.next;
            cur.next = pre;
            return reverseListInt(cur, next);
        }
    }

    // iteration
    class Solution2 {
        public ListNode reverseList(ListNode head) {
            ListNode pre = null, cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }

}
