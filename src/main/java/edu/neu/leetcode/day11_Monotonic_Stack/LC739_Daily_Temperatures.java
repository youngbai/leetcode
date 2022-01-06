package edu.neu.leetcode.day11_Monotonic_Stack;

import java.util.Deque;
import java.util.ArrayDeque;

public class LC739_Daily_Temperatures {

    /*
    Thinking:
    - monotonic stack
    - stack does not store the next greater value, but store the index of next greater value

    Example:
    - nums = [80,60,70,90]
    - go through all elements from n-1 to 0, maintain the monotonic stack
    - process:
    	element  	stack(index)	res(distance to next greater value)
 i=3	  90		    3		 	    0	stack isEmpty
 i=2	  70		   23		 	    1	3(90)-2=1
 i=1	  60		  123		 	    1	2(70)-1=1
 i=0	  80		   03		 	    3	3(90)-0=3

    Time:  O(n), walk through all elements
    Space: O(n), monotonic stack
     */
    class Solution1_monotonic_stack {
        public int[] dailyTemperatures(int[] tmp) {
            int n = tmp.length;
            int[] res = new int[n];

            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && tmp[i] >= tmp[stack.peek()]) stack.pop();
                res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
                stack.push(i);
            }

            return res;
        }
    }

}
