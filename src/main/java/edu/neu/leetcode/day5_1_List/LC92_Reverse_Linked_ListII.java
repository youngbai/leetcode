package edu.neu.leetcode.day5_1_List;

import edu.neu.leetcode.commonbean.ListNode;

public class LC92_Reverse_Linked_ListII {

    /*
    example: reverse [2-4]
        1 - 2 - 3 - 4 - 5
        1 - 4 - 3 - 2 - 5
     */
    class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode fakeHead = new ListNode(-1);
            fakeHead.next = head;
            ListNode pre = fakeHead, cur = head;
            // move right
            int i = 1;
            while (i < m) {
                pre = cur;
                cur = cur.next;
                i++;
            } // in the end, i = m, pre->1, cur->2
            ListNode node = pre;    // node.next is the head of the segment linked list
            while (i++ <= n) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            } // in the end, pre->4, cur->5
            // connect the reversed linked list
            node.next.next = cur; // 2->5
            node.next = pre;      // 1->4
            return fakeHead.next;
        }
    }
}
