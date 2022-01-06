package edu.neu.leetcode.day5_2_List;

import edu.neu.leetcode.commonbean.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LC160_Intersection_of_Two_Linked_Lists {

    /*
    Thinking:
    - save A into set
    - traverse B to see if it is visited

    Time:  O(m+n), traverse each element in A, B
    Space: O(m), save A in a set
     */
    public class Solution1 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // save A into set
            Set<ListNode> set = new HashSet<>();
            while (headA != null) {
                set.add(headA);
                headA = headA.next;
            }
            while (headB != null) { // traverse B, check if it is visited
                if (set.contains(headB)) return headB;
                else headB = headB.next;
            }
            return null;
        }
    }

    /*
    Thinking:
    - compute length of A, B
    - skip a few elements, then traverse A, B neck and neck

    Time:  O(m+n)
    Space: O(1)
     */
    public class Solution2 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int lenA = length(headA), lenB = length(headB);
            while (lenA < lenB) {
                headB = headB.next;
                lenB--;
            }
            while (lenA > lenB) {
                headA = headA.next;
                lenA--;
            }

            // complex to implement, but more readable
            while (headA != null & headB != null) {
                if (headA == headB) return headA;
                headA = headA.next;
                headB = headB.next;
            }
            return null;
            // simpler way to implement the comparison, because (null==null) is true
            // while (headA != headB) {
            //     headA = headA.next;
            //     headB = headB.next;
            // }
            // return headA;
        }
        private int length(ListNode head) {
            int len = 0;
            while (head != null) {
                len++;
                head = head.next;
            }
            return len;
        }
    }


    /*
    Thinking: (best solution)
    - formula: a + c + b = b + c + a
    - corner case: A, B has no intersection.
        In the end, both a, b are null, but a also equals b, because (null==null) is true

    Time:  O(m+n)
    Space: O(1)
     */
    public class Solution3 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode a = headA, b = headB;
            while (a != b) {
                a = (a == null)? headB: a.next;
                b = (b == null)? headA: b.next;
            }
            return a;
        }
    }

}
