package edu.neu.leetcode.day5_1_List;

import commonbean.ListNode;

public class LC141_Linked_List_Cycle {

    // slow, fast pointers
    public class Solution {
        public boolean hasCycle(ListNode head) {
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) return true;
            }
            return false;
        }
    }
}
