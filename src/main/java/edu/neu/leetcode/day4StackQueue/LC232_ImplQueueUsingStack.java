package edu.neu.leetcode.day4StackQueue;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LC232_ImplQueueUsingStack {

    /*
     Brute Force:
     e.g. insert 3
     s1: 2 1
     s2:

     pop 1, 2 from s1 and push them into s2
     s1: 3
     s2: 1 2

     pop 2, 1 from s2 and push them into s1
     s1: 3 2 1
     s2:

     Problem:
     push() - O(n)
     */
    class MyQueue1 {

        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();

        public void push(int x) {
            if (s1.isEmpty()) {
                s1.push(x);
                return;
            }

            // pop s1 and push to s2
            while (!s1.isEmpty()) s2.push(s1.pop());
            // push x to s1
            s1.push(x);
            // pop s2 and push to s1
            while (!s2.isEmpty()) s1.push(s2.pop());
        }

        public int pop() {
            // pop s1
            return s1.pop();
        }

        public int peek() {
            return s1.peek();
        }

        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }


    @Test
    public void testMyQueue1() {
        MyQueue1 myQueue = new MyQueue1();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        assertEquals(1, myQueue.peek()); // return 1
        assertEquals(1, myQueue.pop());  // return 1, queue is [2]
        assertFalse(myQueue.empty());             // return false

        myQueue.push(3); // queue is: [2, 3]
        myQueue.push(4); // queue is: [2, 3, 4]
        assertEquals(2, myQueue.pop());  // return 2, queue is [3, 4]
        assertEquals(3, myQueue.pop());  // return 3, queue is [4]
        assertEquals(4, myQueue.pop());  // return 4, queue is []
        assertTrue(myQueue.empty());              // return true
    }

    /**
     * Best solution
     * Thinking: Stack's feature is to reverse elements order, so we use
     * s2 (the second stack) to reverse the pop order, but don't push them
     * back to s1 (the first stack)
     *
     */
    class MyQueue2 {

        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();

        // O(1)
        public void push(int x) {
            s1.push(x);
        }

        // O(1): when we do n pop() operations, each element experienced 1 push to s2,
        // and 1 pop from s2
        public int pop() {
            // if s2 NOT empty, pop s2
            // else pop s1 and push to s2, then pop s2
            if (s2.isEmpty()) {
                while(!s1.isEmpty())
                    s2.push(s1.pop());
            }
            return s2.pop();
        }

        // O(1)
        public int peek() {
            // if s2 NOT empty, peek s2
            // else pop s1 and push to s2, then peek s2
            if (s2.isEmpty()) {
                while(!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.peek();
        }

        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }

    @Test
    public void testMyQueue2() {
        MyQueue2 myQueue = new MyQueue2();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        assertEquals(1, myQueue.peek()); // return 1
        assertEquals(1, myQueue.pop());  // return 1, queue is [2]
        assertFalse(myQueue.empty());             // return false

        myQueue.push(3); // queue is: [2, 3]
        myQueue.push(4); // queue is: [2, 3, 4]
        assertEquals(2, myQueue.pop());  // return 2, queue is [3, 4]
        assertEquals(3, myQueue.pop());  // return 3, queue is [4]
        assertEquals(4, myQueue.pop());  // return 4, queue is []
        assertTrue(myQueue.empty());              // return true
    }


}
