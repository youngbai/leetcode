package edu.neu.leetcode.day4StackQueue;

import java.util.*;

/*
Interview: linkedin
 */
public class LC716_MaxStack {

    /*
    Thinking:
    - two stack

    Time: O(N)
    Space: O(N)
     */
    class MaxStack1_Two_Stack {

        Deque<Integer> stack = new LinkedList<>();
        Deque<Integer> maxStack = new LinkedList<>();

        public MaxStack1_Two_Stack() {

        }

        public void push(int x) {
            stack.push(x);
            if (maxStack.isEmpty()) {
                maxStack.push(x);
            } else {
                int max = (maxStack.peek() <= x) ? x : maxStack.peek();
                maxStack.push(max);
            }
        }

        public int pop() {
            maxStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        public int popMax() {
            int max = peekMax();
            Deque<Integer> buffer = new LinkedList<>();
            while (stack.peek() != max) buffer.push(pop());
            pop();
            while (!buffer.isEmpty()) push(buffer.pop());
            return max;
        }
    }


    /*
    Thinking:
    - double linked list as stack
    - TreeMap<Value, List<Node>>

    TreeMap
    - firstKey()
    - lastKey()
    - floorKey()
    - ceilingKey()
    - higherKey()
    - lowerKey()

     */
    class MaxStack1_DoubleLinedList_TreeMap {

        DoubleLinkedList dll;
        TreeMap<Integer, List<Node>> map;

        public MaxStack1_DoubleLinedList_TreeMap() {
            dll = new DoubleLinkedList();
            map = new TreeMap<>();
        }

        public void push(int x) {
            Node node = dll.add(x);
            map.computeIfAbsent(x, key -> new ArrayList<>()).add(node);
        }

        public int pop() {
            int val = dll.pop();
            List<Node> list = map.get(val);
            list.remove(list.size() - 1);
            if (list.isEmpty()) map.remove(val);
            return val;
        }

        public int top() {
            return dll.peek();
        }

        public int peekMax() {
            return map.lastKey();
        }       // Note: TreeMap.lastKey()

        public int popMax() {
            int max = peekMax();
            List<Node> list = map.get(max);
            Node node = list.remove(list.size() - 1);
            dll.remove(node);
            if (list.isEmpty()) map.remove(max);
            return max;
        }

        class DoubleLinkedList {
            Node head, tail;

            public DoubleLinkedList() {
                head = new Node(0);
                tail = new Node(0);
                head.next = tail;
                tail.pre = head;
            }

            public Node add(int x) {
                Node cur = new Node(x);
                Node pre = tail.pre;
                pre.next = cur;
                cur.pre = pre;
                cur.next = tail;
                tail.pre = cur;
                return cur;
            }

            public int pop() {
                return remove(tail.pre).val;
            }

            public int peek() {
                return tail.pre.val;
            }

            public Node remove(Node node) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                return node;
            }
        }

        class Node {
            int val;
            Node pre, next;
            Node(int val) {
                this.val = val;
            }
        }

    }


}

