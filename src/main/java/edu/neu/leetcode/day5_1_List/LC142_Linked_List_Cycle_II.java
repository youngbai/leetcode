package edu.neu.leetcode.day5_1_List;

import edu.neu.leetcode.commonbean.ListNode;

public class LC142_Linked_List_Cycle_II {

    /*
     Thinking: slow, fast pointers

       m                n=x+y is a cycle
     ------|----
           |     \
         x |     |  y
            --|--
             meet
     f = 2s
     m + k*n + y = 2(m + y)
     m + k*n = 2m + y
     m = k*n - y
     m = k*n - (n-x)
     m = x + n(k - 1), where k>=1
     */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) { // make sure fast can take two steps
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {     // slow meets fast
                    fast = head;
                    while (slow != fast) {
                        slow = slow.next;
                        fast = fast.next;
                    }
                    return slow;
                }
            }
            return null;
        }
    }
}
