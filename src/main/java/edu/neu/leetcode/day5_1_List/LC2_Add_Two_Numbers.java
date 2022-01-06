package edu.neu.leetcode.day5_1_List;

import edu.neu.leetcode.commonbean.ListNode;

public class LC2_Add_Two_Numbers {

    /*
    Thinking:
    Iterative:
    Corner Case:
    1.l1 and l2 are not in the same length
    2.when come to the end of l1 and l2, carry probably is not 0
     */
    class Solution1 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            int carry = 0;
            while (l1 != null || l2 != null) {
                int v1 = (l1 == null)? 0: l1.val;
                int v2 = (l2 == null)? 0: l2.val;
                int sum = v1 + v2 + carry;
                tail.next = new ListNode(sum % 10);

                carry = sum / 10;
                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
                tail = tail.next;
            }
            if (carry != 0) tail.next = new ListNode(carry);
            return dummy.next;
        }
    }

    /*
    Recursive
     */
    class Solution2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            helper(dummy, l1, l2, 0);   // 0 is the carry
            return dummy.next;
        }

        public void helper(ListNode tail, ListNode l1, ListNode l2,  int carry) {
            if (l1 == null && l2 == null) {
                if (carry > 0) tail.next = new ListNode(carry);
                return;
            }

            int v1 = (l1 == null)? 0: l1.val;
            int v2 = (l2 == null)? 0: l2.val;
            int sum = v1 + v2 + carry;
            tail.next = new ListNode(sum % 10);

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            helper(tail.next, l1, l2, sum / 10);
        }
    }
}
