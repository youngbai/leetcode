package edu.neu.leetcode.day4StackQueue;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC155_MinStack {

    /**
     * Two stack
     * Time:  O(1)
     * Space: O(n)
     */
    class MinStack1 {

        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> minStack = new ArrayDeque<>();

        // O(1)
        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty() || x <= minStack.peek()) minStack.push(x);
        }

        // O(1)
        public void pop() {
            if (stack.isEmpty()) return;
            int top = stack.pop();
            if (top == minStack.peek()) minStack.pop();
        }

        // O(1)
        public int top() {
            return stack.peek();
        }

        // O(1)
        public int getMin() {
            return minStack.peek();
        }
    }

    @Test
    public void testMinStack1() {
        MinStack1 minStack = new MinStack1();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertEquals(-3, minStack.getMin()); // return -3
        minStack.pop();
        assertEquals(0, minStack.top());     // return 0
        assertEquals(-2, minStack.getMin()); // return -2
    }


    /**
     * Two stack Improvement:
     * min stack store [[element, count],  ]
     */
    class MinStack1_Improve {

        Deque<Integer> stack = new ArrayDeque<>();
        Deque<int[]> minStack = new ArrayDeque<>();

        // O(1)
        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty() || x < minStack.peek()[0])
                minStack.push(new int[]{x, 1});
            else if (x == minStack.peek()[0]) {
                minStack.peek()[1]++;
            }
        }

        // O(1)
        public void pop() {
            if (stack.isEmpty()) return;
            int top = stack.pop();
            if (top == minStack.peek()[0]) {
                if (minStack.peek()[1] > 1) minStack.peek()[1]--;
                else minStack.pop();
            }
        }

        // O(1)
        public int top() {
            return stack.peek();
        }

        // O(1)
        public int getMin() {
            return minStack.peek()[0];
        }
    }

    @Test
    public void testMinStack1_Improve() {
        MinStack1_Improve minStack = new MinStack1_Improve();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertEquals(-3, minStack.getMin()); // return -3
        minStack.pop();
        assertEquals(0, minStack.top());     // return 0
        assertEquals(-2, minStack.getMin()); // return -2
    }

    /**
     * One Stack
     */
    class MinStack2 {

        Deque<int[]> stack = new ArrayDeque<>();

        // O(1)
        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(new int[]{x, x});
                return;
            }
            int min = Math.min(stack.peek()[1], x);
            stack.push(new int[]{x, min});
        }

        // O(1)
        public void pop() {
            if (stack.isEmpty()) return;
            stack.pop();
        }

        // O(1)
        public int top() {
            return stack.peek()[0];
        }

        // O(1)
        public int getMin() {
            return stack.peek()[1];
        }

    }

    @Test
    public void testMinStack2() {
        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertEquals(-3, minStack.getMin()); // return -3
        minStack.pop();
        assertEquals(0, minStack.top());     // return 0
        assertEquals(-2, minStack.getMin()); // return -2
    }


}
