package edu.neu.leetcode.day5_2_List;

import commonbean.ListNode;

public class LC19_Remove_Nth_Node_From_End_of_List {

    /*
    Thinking:
    - draw the ending situation
            dummy - 1 - 2 - 3 - 4 - 5
    INIT      s         f
    END                     s       f      # fast.next is null
     */
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head), slow = dummy, fast = dummy;
            for (int i = 0; i < n; i++) fast = fast.next;
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            } // if fast.next is null, then fast is the end of this list
            slow.next = slow.next.next;
            return dummy.next;
        }
    }

}
