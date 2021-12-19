package edu.neu.leetcode.day4StackQueue;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/*
Thinking:
The best algorithm depends on the situation.
Method1: is applicable for the situation where more pop, less push
Method2: is applicable for the situation where more push, less pop
 */
public class LC225_ImplStackUsingQueue {

    /*
    Method1:
    Time:
    push - O(n)
    pop  - O(1)

    Algo:
    See youdao notes: day4_stack_queue
     */
    class MyStack1 {

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        public void push(int x) {
            if (q1.isEmpty()) {
                q1.offer(x);
                return;
            }
            q2.offer(x);
            while (!q1.isEmpty()) q2.offer(q1.poll());
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
        }

        public int pop() {
            if (q1.isEmpty()) return -1;
            return q1.poll();
        }

        public int top() {
            return q1.peek();
        }

        public boolean empty() {
            return q1.isEmpty();
        }

    }

    @Test
    public void testMyStack1() {
        MyStack1 myStack = new MyStack1();
        myStack.push(1);
        myStack.push(2);
        assertEquals(2, myStack.top()); // return 2
        assertEquals(2, myStack.pop()); // return 2
        assertFalse(myStack.empty()); // return False
        myStack.push(3);
        assertEquals(3, myStack.pop()); // return 3
    }

    /*
    Method2:
    Time:
    push - O(1)
    pop  - O(n)

    Algo:
    See youdao notes: day4_stack_queue
     */
    class MyStack2 {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        int top;

        public void push(int x) {
            q1.offer(x);
            top = x;
        }

        public int pop() {
            while (q1.size() > 1) {
                top = q1.poll();
                q2.offer(top);
            }
            int value = q1.poll();
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return value;
        }

        public int top() {
            return top;
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }

    @Test
    public void testMyStack2() {
        MyStack2 myStack = new MyStack2();
        myStack.push(1);
        myStack.push(2);
        assertEquals(2, myStack.top()); // return 2
        assertEquals(2, myStack.pop()); // return 2
        assertFalse(myStack.empty()); // return False
        myStack.push(3);
        assertEquals(3, myStack.pop()); // return 3
    }


    /*
    Method3: all operation is O(1), but really depends on Java language,
    which might be in a follow up.
    Reason: LinkedList.addAll() is O(1)
     */
    class MyStack3 {
        Queue<Integer> queue;

        public MyStack3() {
            this.queue = new LinkedList<>();
        }

        /*
        O(1)
        1.create a empty queue with LinkedList, and put new element into it
        2.then add all elements from the old queue to new queue with addAll() which is O(1)
         */
        public void push(int x) {
            Queue<Integer> temp = new LinkedList<>();
            temp.add(x);                // O(1): add new element to the new queue
            temp.addAll(this.queue);    // O(1): all all elements from the old queue to new queue
            this.queue = temp;          // O(1): change reference
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }

    }

}
