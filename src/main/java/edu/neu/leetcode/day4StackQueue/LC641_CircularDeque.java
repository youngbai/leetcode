package edu.neu.leetcode.day4StackQueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LC641_CircularDeque {

    /*
    Not thread-safe
     */
    class MyCircularDeque1 {

        int[] q;
        int front = 0, rear = -1, size = 0, capacity;

        public MyCircularDeque1(int k) {
            q = new int[k];
            capacity = k;
        }

        // corner case: full, empty
        public boolean insertFront(int value) {
            if (isFull()) return false;
            if (isEmpty()) {    // when queue is empty, reset front = rear = 0
                front = rear = 0;
            } else {
                if (front == 0) front = capacity - 1;
                else front--;
            }
            size++;
            q[front] = value;
            return true;
        }

        // corner case: full, empty
        public boolean insertLast(int value) {
            if (isFull()) return false;
            if (isEmpty()) {    // when queue is empty, reset front = rear = 0
                front = rear = 0;
            } else {
                rear = (rear + 1) % capacity;
            }
            size++;
            q[rear] = value;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) return false;
            System.out.println("delete front=" + q[front]);
            front = (front + 1) % capacity;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) return false;
            System.out.println("delete rear=" + q[rear]);
            if (--rear < 0) rear += capacity;
            size--;
            return true;
        }

        public int getFront() {
            return isEmpty() ? -1 : q[front];
        }

        public int getRear() {
            return isEmpty() ? -1 : q[rear];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

    }


    @Test
    public void testMyCircularDeque1() {
        MyCircularDeque1 circularDeque = new MyCircularDeque1(3); // set the size to be 3
        assertTrue(circularDeque.insertLast(1));            // return true
        assertTrue(circularDeque.insertLast(2));            // return true
        assertTrue(circularDeque.insertFront(3));           // return true
        assertFalse(circularDeque.insertFront(4));          // return false, the queue is full
        assertEquals(2, circularDeque.getRear());        // return 2
        assertTrue(circularDeque.isFull());                // return true
        assertTrue(circularDeque.deleteLast());            // return true
        assertTrue(circularDeque.insertFront(4));                // return true
        assertEquals(4, circularDeque.getFront());            // return 4
    }

}
