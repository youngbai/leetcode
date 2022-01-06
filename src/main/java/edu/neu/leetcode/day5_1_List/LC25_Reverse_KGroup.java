package edu.neu.leetcode.day5_1_List;

import edu.neu.leetcode.commonbean.ListNode;

/*
Reverse Lined List Summary:
pre->cur->next
pre<-cur<-next
 */
public class LC25_Reverse_KGroup {

    /*
    Thinking:
    Algo:
        reverseKGroup(head, k):
            # reverse this group, return its new head
            move node to next group's head, then node -> next group's head
            pre = reverseKGroup(node, k), reverse next group, return its new head, which is pre
            return reverse(head, pre, k)

        reverse(cur, pre, k):
            while (k-- > 0):
                next = cur.next
                cur.next = pre
                pre = cur
                cur = next
            return pre

    Time:  O(n), traverse each node only once
    Space: O(n/k), recursion need n/k memory stack
     */
    class Solution1 {
        public ListNode reverseKGroup(ListNode head, int k) {
            // move node to next section's head
            ListNode node = head;
            int count = 0;
            while (count < k) {
                // this section is not enough to k, return head
                if (node == null) return head;
                node = node.next;
                count++;
            }
            ListNode pre = reverseKGroup(node, k);
            return reverse(head, pre, k);
        }

        public ListNode reverse(ListNode cur, ListNode pre, int count) {
            while (count-- > 0) {
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
    Almost same as Solution1, only difference is
    Firstly, reverse current group
    Secondly, reverse next group, and return its new head
    Thirdly, let current group last item point to new head of next group
     */
    class Solution2 {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode node = head;
            int count = 0;
            while (count < k) {
                if (node == null) return head;
                node = node.next;
                count++;
            }
            ListNode newHead = reverse(head, k);
            head.next = reverseKGroup(node, k); // head becomes the tail of current section
            return newHead;
        }
        public ListNode reverse(ListNode head, int k) {
            ListNode pre = null;
            ListNode cur = head;
            while (k-- > 0) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }

    /*
    Not the best solution
    Thinking:

    Time:  O(2n)
    Space: O(1)
     */
    class Solution3 {
        public ListNode reverseKGroup(ListNode head, int k) {
            int N = countLength(head);
            int i = 1;
            while (i + k <= N + 1) {
                head = reverseBetween(head, i , i+k -1);
                i += k;
            }
            return head;
        }
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode fakeHead = new ListNode(-1);
            fakeHead.next = head;
            ListNode pre = fakeHead;
            ListNode cur = head;
            int i = 1;
            while (i < m) {
                pre = cur;
                cur = cur.next;
                i++;
            }
            ListNode newHead = pre;
            while (i <= n) {
                ListNode next = cur.next;
                cur.next = newHead;
                newHead = cur;
                cur = next;
                i++;
            }
            // pre.next is the tail after reversing,
            // cur is the head of next group
            // newHead is the head of current group after reversing
            // e.g.
            //      pre:-1, pre.next:1, cur:3, newHead:2
            //      pre:1,  pre.next:3, cur:5, newHead:4
            System.out.printf("pre:%d, pre.next:%d, cur:%d, newHead:%d%n", pre.val, pre.next.val, cur.val, newHead.val);
            pre.next.next = cur;
            // pre -> newHead
            pre.next = newHead;
            return fakeHead.next;
        }

        private int countLength(ListNode cur) {
            int count = 0;
            while (cur != null) {
                cur = cur.next;
                count++;
            }
            return count;
        }
    }


}
