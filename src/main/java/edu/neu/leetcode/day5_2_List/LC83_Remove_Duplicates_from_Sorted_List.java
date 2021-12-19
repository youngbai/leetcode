package edu.neu.leetcode.day5_2_List;

import commonbean.ListNode;

public class LC83_Remove_Duplicates_from_Sorted_List {


    /*
    Iteration
     */
    class Solution1_1 {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) return null;
            ListNode cur = head;
            while (cur.next != null) {
                if (cur.val == cur.next.val) cur.next = cur.next.next;
                else cur = cur.next;
            }
            return head;
        }
    }

    /*
    Iteration
     */
    class Solution1_2 {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode cur = head;
            while (cur != null) {
                while (cur.next != null && cur.val == cur.next.val)
                    cur.next = cur.next.next;
                cur = cur.next;
            }
            return head;
        }
    }

    /*
    Recursion
     */
    class Solution2 {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            head.next = deleteDuplicates(head.next);
            return (head.val == head.next.val)? head.next : head;
        }
    }

}
