package edu.neu.leetcode.day14_Sort_1;

import edu.neu.leetcode.commonbean.ListNode;

public class LC148_Sort_List {

    /*
    Thinking
    - like merge sort

    Time:  O(NlogN)
    Space: O(1)
     */
    class Solution1_Merge_Sort {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode mid = findMid(head);           // find the middle node
            ListNode right = sortList(mid.next);    // sort the right linked list
            mid.next = null;
            ListNode left = sortList(head);         // sort the left linked list
            return merge(left, right);              // merge two linked list
        }

        private ListNode findMid(ListNode head) {
            ListNode slow = head, fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        private ListNode merge(ListNode left, ListNode right) {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            while (left != null && right != null) {
                if (left.val <= right.val) {
                    cur.next = left;
                    left = left.next;
                } else {
                    cur.next = right;
                    right = right.next;
                }
                cur = cur.next;
            }

            if (left == null) cur.next = right;
            else cur.next = left;
            // if (left != null) cur.next = left;
            // if (right != null) cur.next = right;
            return dummy.next;
        }
    }
}
