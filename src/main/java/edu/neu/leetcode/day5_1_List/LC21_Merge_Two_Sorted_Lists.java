package edu.neu.leetcode.day5_1_List;

import edu.neu.leetcode.commonbean.ListNode;

public class LC21_Merge_Two_Sorted_Lists {

    /*
    Solution: Iteration
    Time:  O(M+N)
    Space: O(1)
    */
    class Solution1 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    tail.next = l1;
                    l1 = l1.next;
                } else {
                    tail.next = l2;
                    l2 = l2.next;
                }
                tail = tail.next;
            }
            // if (l1 != null) tail.next = l1;
            // if (l2 != null) tail.next = l2;
            tail.next = (l1 == null)? l2 : l1;
            return dummy.next;
        }
    }

    /*
    Solution: Recursion

    Algo: (Recursion Formula)
        merge(l1,l2) = winner(l1,l2) + merge(l1 remaining elements, l2 remaining elements)

    Time:  O(M+N)
    Space: O(M+N) - recursive memory stack
    */
    class Solution2 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;

            if (l1.val <= l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }


}
