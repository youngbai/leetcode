package edu.neu.leetcode.day11_Monotonic_Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC42_Trapping_Rain_Water {

    /*
    Thinking:
    - monotonic stack

    Intuition:
    - sink contains: left wall, bottom, right wall
      - left wall: decreasing height
      - right wall: increasing height
      - bottom: only consider 3 elements at a time, bottom is the middle one
      - operation: find left wall, bottom when meet right wall
    - compute unit of water
    - keep looking leftward to the further left wall
    - formula:
      h=min(left_wall, right_wall) - bottom = 1 - 0 = 1
      w=right_wall.index - left_wall.index - 1 = 4 - 2 - 1 = 1
      A=h*w = 1 * 1 = 1

    Example:
    height = [1 2 1 0 3 5]
    - notes:
      - sink 1: [1 0 3] [left, bottom, right]
      - sink 2: [2 1 3] [left, bottom, right]

    - index=0, h=1
    stack is empty, push 1
    stack=[1]

    - index=1, h=2
    2>1, pop 1, increasing height, so it is possible to form a sink, try to find the left wall by looking leftward further
    but stack is empty, so give up, push 2
    stack=[2]

    - index=2, h=1
    1<2, decreasing height, we have to wait until an increasing height is found
    push 1
    stack=[2 1]

    - index=3, h=0
    0<1, decreasing height, we only look for the left wall when we find the right wall
    push 0
    stack=[2 1 0]

    - index=4, h=3
    3>0, increasing height, we start to find the left, but ony consider 3 elements at a time
    pop 0 as bottom, stack=[2 1]
    peek 1 as left wall
    so left_wall=1, bottom=0, right_wall=3
    h=min(left_wall, right_wall) - bottom = 1 - 0 = 1
    w=right_wall.index - left_wall.index - 1 = 4 - 2 - 1 = 1
    A=h*w = 1 * 1 = 1
    then keep looking leftward further because 3 > 1(peek), it is still an increasing height

    pop 1 as bottom, stack=[2]
    peek 2 as left wall
    so left_wall=2, bottom=1, right_wall=3
    h=min(left_wall, right_wall) - bottom = 2 - 1 = 1
    w=right_wall.index - left_wall.index - 1 = 4 - 1 - 1 = 2
    A=h*w = 1 * 2 = 2
    then keep looking leftward further because 3 > 2(peek), it is still an increasing height

    pop 2 as bottom, stack=[]
    stack is empty, there is no left wall, so stop looking for more sink/left wall

    push current element 3

    Time:  O(n), walk through all elements
    Space: O(n), monotonic stack
     */
    class Solution1_stack {
        public int trap(int[] height) {
            Deque<Integer> stack = new ArrayDeque<>();
            int N = height.length, res = 0;
            for (int i = 0; i < N; i++) { // walk throught each element
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) { // meet an increasing height, find possible sinks
                    int bottomIndex = stack.pop();
                    if (stack.isEmpty()) break;
                    int leftIndex = stack.peek();

                    // computer space of the sink
                    int bottom = height[bottomIndex];
                    int h = Math.min(height[leftIndex], height[i]) - bottom;
                    int w = i - leftIndex - 1;
                    int A = h * w;
                    res += A;
                }
                stack.push(i);
            }
            return res;
        }
    }


    /*
    Thinking:
    - DP
    - sun shine

    Algo:
    - let the sun shine from the left and get some shadows
    - let the sun shine from the right and get some shadows
    - the common part of these two shadows are the possible sinks
    - go through each height, let the common shadows subtracted by the hidden rock(the height of the bar)
    - sum of these are the answer

    Time:  O(n), walk through all elements
    Space: O(n), left array, right array
     */
    class Solution2_DP {
        public int trap(int[] height) {
            if (height.length == 0) return 0;
            int res = 0, N = height.length;
            int[] left = new int[N], right = new int[N];
            left[0] = height[0];    // let the sun shine from the left and get some shadows
            right[N - 1] = height[N - 1]; // let the sun shine from the right and get some shadows
            for (int i = 1; i < N; i++) left[i] = Math.max(height[i], left[i - 1]);
            for (int i = N - 2; i >= 0; i--) right[i] = Math.max(height[i], right[i + 1]);
            for (int i = 0; i < N; i++) res += Math.min(left[i], right[i]) - height[i];
            return res;
        }
    }

}
