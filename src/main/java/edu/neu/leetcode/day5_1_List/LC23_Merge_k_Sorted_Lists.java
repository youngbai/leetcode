package edu.neu.leetcode.day5_1_List;

import edu.neu.leetcode.commonbean.ListNode;

import java.util.PriorityQueue;

public class LC23_Merge_k_Sorted_Lists {

    /*
    Thinking:
    Priority Queue (Min Heap)

    Time:  O(nlogk)
    - traverse each element, O(n)
    - offer and poll each element from PQ, O(logk), because PQ's size is k, its height is logk
    Space: O(k), because only k elements in PQ
    n - the number of all elements
    k - the number of linked list
     */
    class Solution1 {
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode dummy = new ListNode(-1);
            PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
            for (ListNode node: lists)
                // we only offer the head node
                if (node != null) pq.offer(node);   // corner case, list could be empty list

            ListNode tail = dummy;
            while (!pq.isEmpty()) {     // O(n)
                tail.next = pq.poll();  // O(logk)
                tail = tail.next;
                // optimization: when pq is empty, there is only 1 or 0 list left,
                // which is already be merged to the new list and it is sorted. So
                // we don't have to offer it to the heap again. Job is done, just break.
                if (pq.isEmpty()) break;
                if (tail.next != null) pq.offer(tail.next); // offer the next element of the top of the heap to the heap
            }
            return dummy.next;
        }
    }

    /*
    Thinking:
    Divide and Conquer, like MergeSort

    Time:  O(nlogk)
    - the recursion tree height is logk
    - each recursion layer, Time is O(n), because merge will traverse each element
    - so, Time is O(n * logk)

    Space: O(1), NOT considering recursive stack space
    Space: O(logk), the recursion tree height is logk,
        so the depth of recursion is logk, which costs logk memory stack
    n - the number of all elements
    k - the number of linked list
    logk - the height of recursion tree

    Summary:
    How to figure out Space Complexity of recursive algorithm?
    1. simple way - recursion tree
    The Space Complexity of recursive algorithm mainly depends on the depth of recursion tree
    2. Master theorem
    https://en.wikipedia.org/wiki/Master_theorem_(analysis_of_algorithms)
     */
    class Solution2 {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            return partition(lists, 0, lists.length - 1);
        }
        public ListNode partition(ListNode[] lists, int start, int end) {
            if (start == end) return lists[start];
            // start < end
            int mid = (start + end) / 2;
            ListNode l1 = partition(lists, start, mid);
            ListNode l2 = partition(lists, mid + 1, end);
            return merge(l1, l2);
            //System.out.printf("start:%d, end:%d", start, end);
        }

        // This function is from LC21_Merge_Two_Sorted_Lists
        public ListNode merge(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;

            if (l1.val <= l2.val) {
                l1.next = merge(l1.next, l2);
                return l1;
            } else {
                l2.next = merge(l1, l2.next);
                return l2;
            }
        }
    }

}
