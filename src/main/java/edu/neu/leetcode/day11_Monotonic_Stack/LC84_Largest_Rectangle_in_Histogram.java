package edu.neu.leetcode.day11_Monotonic_Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LC84_Largest_Rectangle_in_Histogram {

    /*
    Thinking:
    - monotonic stack

    Intuition:
    - for each bar, if we can find its left wall, right wall, we are close to the answer
      - width = right_wall.index - left_wall.index
      - height = height of this bar
      - A = width * height
    - maintain an increasing monotonic stack, it means
      - for each bar in the stack, the previous bar is its left wall
      - if current bar is < top bar in the stack, it means top bar in the stack found its right wall,
        then pop the top bar, check the next top bar until it fails, therefore maintain the monotonic stack
    - optimization
      - if current bar is < top bar in the stack, we can directly calculate its Area instead of just storing its right wall
        see Solution2

    Time: O(2N)
    Space: O(2N), left array, right array
     */
    class Solution1_Monotonic_stack {
        public int largestRectangleArea(int[] heights) {
            int res = 0, N = heights.length;
            int[] left = new int[N];    // store the index of the left wall
            int[] right = new int[N];   // store the index of the right wall
            Arrays.fill(right, N);      // init all right wall with n

            // find the left wall, right wall
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {   // O(N)
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    right[stack.pop()] = i;
                }
                left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
                stack.push(i);
            }

            // calculate max area for each bar
            for (int i = 0; i < N; i++) {   // O(N)
                int A = (right[i] - left[i]) * heights[i];
                res = Math.max(res, A);
            }

            return res;
        }
    }


    class Solution2_Monotonic_stack {
        public int largestRectangleArea(int[] heights) {
            int res = 0, N = heights.length;
            Deque<Integer> stack = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                // current bar < top bar in stack: heights[i] < heights[stack.peek()]
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    int prebar = stack.pop();
                    int leftWall = stack.isEmpty() ? 0 : stack.peek() + 1;
                    int w = i - leftWall; // right wall is i
                    int A = w * heights[prebar];
                    res = Math.max(res, A);
                }
                stack.push(i);
            }

            // if stack is not empty
            // left wall of each bar is the previous bar in stack, because pre bar < current bar
            // right wall of each bar is the end of array - N, because current bar < all the following bar
            while (!stack.isEmpty()) {
                int h = heights[stack.pop()];
                int leftWall = stack.isEmpty() ? 0 : stack.peek() + 1;
                int w = N - leftWall;
                int A = w * h;
                res = Math.max(res, A);
            }
            return res;
        }
    }

    /*
    Thinking:
    - Divide and Conquer, like merge sort
    - Divide: use the shortest bar to divide the array, because shorted bar is the right wall of left part,
            left wall of left part
    - Conquer:
      - recursively calculate the answer of left part
      - recursively calculate the answer of right part
      - calculate the answer that fully includes the shorted bar which is the highest bar
    - Note:
        Time Limit Exceeded for this question

    Time:  O(nlogn)
    Space: O(1)
     */
    class Solution3_Divide_Conquer {
        public int largestRectangleArea(int[] heights) {
            return divide(heights, 0, heights.length);
        }

        private int divide(int[] heights, int start, int end) {
            if (start >= end) return 0;

            int minHeightIndex = start;
            for (int i = start; i < end; i++)
                if (heights[i] < heights[minHeightIndex])
                    minHeightIndex = i;

            int curA = heights[minHeightIndex] * (end - start);
            int left = divide(heights, start, minHeightIndex);
            int right = divide(heights, minHeightIndex + 1, end);
            return Math.max(curA, Math.max(left, right));
        }
    }

}
