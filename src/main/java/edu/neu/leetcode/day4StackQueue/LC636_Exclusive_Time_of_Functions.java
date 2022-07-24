package edu.neu.leetcode.day4StackQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC636_Exclusive_Time_of_Functions {

    /*
    Thinking:
    - stack

    Time:  O(N)
    Space: O(N)
     */
    class Solution1_Stack {
        public int[] exclusiveTime(int n, List<String> logs) {
            int[] res = new int[n];
            Deque<Log> stack = new LinkedList<>();
            for (String logStr : logs) {
                Log log = new Log(logStr);
                if (log.isStart) stack.push(log);
                else {
                    Log start = stack.pop();
                    int timeCost = log.time - start.time + 1;
                    res[start.id] += timeCost;
                    if (!stack.isEmpty()) {
                        Log parent = stack.peek();
                        res[parent.id] -= timeCost;
                    }
                }
            }
            return res;
        }

        class Log {
            int id;
            boolean isStart;
            int time;

            Log (String logStr) {
                String[] arr = logStr.split(":");
                this.id = Integer.valueOf(arr[0]);
                this.isStart = "start".equals(arr[1]);
                this.time = Integer.valueOf(arr[2]);
            }
        }
    }
}
