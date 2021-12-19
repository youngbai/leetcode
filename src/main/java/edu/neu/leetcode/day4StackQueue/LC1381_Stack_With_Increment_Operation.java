package edu.neu.leetcode.day4StackQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC1381_Stack_With_Increment_Operation {

    /*
    Thinking: Lazy Operation
     */
    class CustomStack {

        int maxSize;
        int[] inc;
        Deque<Integer> stack = new ArrayDeque<>();

        public CustomStack(int maxSize) {
            this.maxSize = maxSize;
            inc = new int[maxSize];
        }

        public void push(int x) {
            if (stack.size() < maxSize) stack.push(x);
        }

        public int pop() {
            if (stack.isEmpty()) return -1;
            int i = stack.size() - 1;   // i - [0, size-1]
            int result = stack.pop() + inc[i];
            if (i > 0) inc[i - 1] += inc[i]; // update inc[i-1]
            inc[i] = 0;     // reset inc[i]
            return result;
        }

        public void increment(int k, int val) {
            int i = Math.min(k, stack.size()) - 1;
            if (i >= 0) inc[i] += val;
        }
    }

}
