package edu.neu.leetcode.day5_2_List;

import edu.neu.leetcode.commonbean.ListNode;

public class LC234_Palindrome_Linked_List {

    /*
    Thinking: (Best solution)
    - slow and fast pointer, when fast comes to the end,
        slow is in the mid (end of the first half or start of the second half)
    - reverse the second half
    - compare
    - restore the list

    Note: this solution will restore the original list
     */
    class Solution1 {
        public boolean isPalindrome(ListNode head) {
            // base case
            if (head == null) return true;

            ListNode firstHalfEnd = endOfFirstHalf(head);
            ListNode secondHalfStart = reverse(firstHalfEnd.next);

            ListNode p1 = head;
            ListNode p2 = secondHalfStart;
            boolean result = true;
            while (result && p2 != null) {
                if (p1.val != p2.val) result = false;
                p1 = p1.next;
                p2 = p2.next;
            }

            // restore the original list
            firstHalfEnd.next = reverse(secondHalfStart);
            return result;
        }
        private ListNode endOfFirstHalf(ListNode head) {
            ListNode slow = head, fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
        private ListNode reverse(ListNode head) {
            ListNode pre = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        }
    }


    /*
    Thinking:
    - same idea with Solution1
    - without restoring the original list
     */
    class Solution2 {
        public boolean isPalindrome(ListNode head) {
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }   // then, slow is the mid or the start of 2nd half

            // if fast is NOT null, then odd nodes, slow is the mid
            if (fast != null) slow = slow.next;

            // reverse 2nd half
            slow = reverse(slow);
            fast = head;

            while (slow != null) {
                if (slow.val != fast.val) return false;
                slow = slow.next;
                fast = fast.next;
            }
            return true;
        }
        private ListNode reverse(ListNode head) {
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
