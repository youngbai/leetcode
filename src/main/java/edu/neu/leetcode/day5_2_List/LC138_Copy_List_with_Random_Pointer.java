package edu.neu.leetcode.day5_2_List;

import java.util.HashMap;
import java.util.Map;

public class LC138_Copy_List_with_Random_Pointer {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /*
    Thinking:
    - map: original node - copy node
    Time:  O(2n)
    Space: O(n) - map contains all nodes
     */
    class Solution1 {
        public Node copyRandomList(Node head) {
            Map<Node, Node> map = new HashMap<>();
            for (Node cur = head; cur != null; cur = cur.next)
                map.put(cur, new Node(cur.val));
            for (Node cur = head; cur != null; cur = cur.next) {
                map.get(cur).next = map.get(cur.next);
                map.get(cur).random = map.get(cur.random);
            }
            return map.get(head);
        }
    }


    /*
    Thinking:
    - 1-1'-2-2'-3-3'
    - insert copied node
    - deal with random relation
    - split the origin list and copied list

    Time:  O(3n)
    Space: O(1)
     */
    class Solution2 {
        public Node copyRandomList(Node head) {
            if (head == null) return null;

            // 1st loop, insert copied node
            for (Node cur = head; cur != null; cur = cur.next.next) {
                Node next = cur.next;
                Node copyNode = new Node(cur.val);
                cur.next = copyNode;
                copyNode.next = next;
            }

            // 2nd loop, deal with random relation
            for (Node cur = head; cur != null; cur = cur.next.next)
                if (cur.random != null) cur.next.random = cur.random.next;

//            for (Node cur = head; cur != null; cur = cur.next)
//                System.out.printf("%d-", cur.val);
//            System.out.printf("%n");

            // 3rd loop, split the origin list and copied list
            Node copyHead = head.next;
            for (Node cur = head, copyCur = copyHead; cur != null; cur = cur.next, copyCur = copyCur.next) {
                System.out.printf("(%d:%d)-", cur.val, copyCur.val);
                cur.next = cur.next.next;
                copyCur.next = (copyCur.next == null)? null: copyCur.next.next;
            }
            return copyHead;
        }
    }

}
