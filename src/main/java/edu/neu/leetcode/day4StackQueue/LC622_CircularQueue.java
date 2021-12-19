package edu.neu.leetcode.day4StackQueue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LC622_CircularQueue {

    /*
    Not thread-safe
     */
    class MyCircularQueue {

        int[] q;
        int front, rear, size;

        public MyCircularQueue(int k) {
            front = 0;
            rear = -1;
            q = new int[k];
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;

            rear = (rear + 1) % q.length;
            q[rear] = value;
            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;

            System.out.println("dequeue:" + q[front]);
            front = (front + 1) % q.length;
            size--;
            return true;
        }

        public int Front() {
            return isEmpty() ? -1 : q[front];
        }

        public int Rear() {
            return isEmpty() ? -1 : q[rear];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == q.length;
        }
    }


    @Test
    public void testMyCircularQueue() {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        assertTrue(myCircularQueue.enQueue(1)); // return True
        assertTrue(myCircularQueue.enQueue(2)); // return True
        assertTrue(myCircularQueue.enQueue(3)); // return True
        assertFalse(myCircularQueue.enQueue(4)); // return False
        assertEquals(3, myCircularQueue.Rear());     // return 3
        assertTrue(myCircularQueue.isFull());   // return True, dequeue 1
        assertTrue(myCircularQueue.deQueue());  // return True
        assertTrue(myCircularQueue.enQueue(4)); // return True
        assertEquals(4, myCircularQueue.Rear());     // return 4
        assertTrue(myCircularQueue.deQueue());  // return True, dequeue 2
    }

    /*
    Follow up question: How to make a thread-safe queue
    Java: lock, synchronized, Semaphore, volatile
    C++:  Mutex
     */

    /*
    Thread-safe Circular Queue
     */
    class MyCircularQueue_ThreadSafe {
        class Node {
            int value;
            Node next;

            Node(int value) {
                this.value = value;
            }
        }

        Node head, tail;
        int count, capacity;
        ReentrantLock lock = new ReentrantLock();

        public MyCircularQueue_ThreadSafe(int k) {
            this.capacity = k;
        }

        public boolean enQueue(int x) {
            try {
                lock.lock();

                if (count == capacity) return false;
                Node newNode = new Node(x);
                if (count == 0) {
                    head = tail = newNode;
                } else {
                    tail.next = newNode;
                    tail = newNode;
                }
                count++;
            } finally {
                lock.unlock();
            }
            return true;
        }
    }


}
