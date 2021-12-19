package edu.neu.leetcode.day5_2_List;

import commonbean.ListNode;

public class LC82_Remove_Duplicates_from_Sorted_List_II {

    /*
    Iteration
     */
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(0, head), pre = dummy, cur = head;
            while (cur != null && cur.next != null) {
                if (cur.val == cur.next.val) {
                    int num = cur.val;  // num is the duplicate number
                    while (cur != null && num == cur.val) cur = cur.next; // keep checking until find a distinct number
                    pre.next = cur; // now cur->a new distinct number
                } else {
                    pre = cur;
                    cur = cur.next;
                }
            }
            return dummy.next;
        }
    }

    /*
    Recursion

    Thinking:
    - treat the list as several sections, each section has duplicate number
    - use recursion on each section
    - so we need to find the beginning and the end of each section
     */
    class Solution2 {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            if (head.val != head.next.val) {
                head.next = deleteDuplicates(head.next);
                return head;
            } else {
                // keep checking until find the a new distinct number or a null,
                // this new distinct number is the beginning of next section
                while (head.next != null && head.val == head.next.val)
                    head = head.next;
                return deleteDuplicates(head.next); // now, head.next -> a new distinct number
            }
        }
    }


}
