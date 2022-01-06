package edu.neu.leetcode.day5_1_List;

import edu.neu.leetcode.commonbean.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC445_Add_Two_NumbersII {

    /*
    Thinking:
    Reverse l1, l2 firstly, then add them.
    In the end, reverse l3

    Time:  O(3N)
    Space: O(1)
     */
    class Solution1 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // main Algo
            l1 = reverse(l1);
            l2 = reverse(l2);
            ListNode l3 = add(l1, l2);
            return reverse(l3);
        }

        private ListNode add(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            int carry = 0;
            while (l1 != null || l2 != null) {
                int v1 = (l1 == null)? 0 : l1.val;
                int v2 = (l2 == null)? 0 : l2.val;
                System.out.println(v1 + "," + v2);
                int sum = v1 + v2 + carry;
                tail.next = new ListNode(sum % 10);

                carry = sum / 10;
                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
                tail = tail.next;
            }
            if (carry > 0) tail.next = new ListNode(carry);
            return dummy.next;
        }

        private ListNode reverse(ListNode cur) {
            ListNode pre = null;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

    }

    /*
    Thinking:
    Stack can reverse the order of all items
    Algo:
        push l1, l2 to stack1, stack2
        while pop stack1, stack2, add them

     Time:  O(2N)
     Space: O(2N)
     */
    class Solution2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            Deque<Integer> stack1 = new ArrayDeque<>();
            Deque<Integer> stack2 = new ArrayDeque<>();

            // push l1 to stack1, l2 to stack2
            while (l1 != null) {
                stack1.push(l1.val);
                l1 = l1.next;
            }
            while (l2 != null) {
                stack2.push(l2.val);
                l2 = l2.next;
            }

            // pop stack1, stack2, and them to head
            ListNode head = null;  // the new list
            int carry = 0;
            while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
                int v1 = stack1.isEmpty()? 0 : stack1.pop();
                int v2 = stack2.isEmpty()? 0 : stack2.pop();
                int sum = v1 + v2 + carry;
                ListNode newHead = new ListNode(sum % 10);
                newHead.next = head;
                head = newHead;
                carry = sum / 10;
            }
            return head;
        }
    }
}
