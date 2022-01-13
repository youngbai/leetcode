package edu.neu.leetcode.day8_DFS;

import java.util.Arrays;

public class LC1986_Minimum_Number_of_Work_Sessions_to_Finish_the_Tasks {

    /*
    Thinking:
    - DFS
    - DFS pruning
      - prune1: sort and start from the biggest job, so curMaxTime easily becomes larger than global result,
        so that it can pruned by prune2
      - prune2: already >= global result, so it is not good assignment, just return
    - like LC1723_Find_Minimum_Time_to_Finish_All_Jobs

    Worst Time: (k^N), but pruning makes it better
    Space: O(N), int[] sessions
     */
    class Solution1_DFS_Pruning {
        int res;
        int maxSessionTime;
        int[] tasks;
        int[] sessions;
        public int minSessions(int[] tasks, int sessionTime) {
            this.res = tasks.length;
            this.maxSessionTime = sessionTime;
            this.tasks = tasks;
            this.sessions = new int[tasks.length];

            Arrays.sort(tasks); // prune1
            dfs(tasks.length - 1, 0);
            return res;
        }

        private void dfs(int taskID, int sessionCount) {

            if (sessionCount >= res) return;  // prune2: already >= global result, so it is not good assignment, just return

            if (taskID < 0) {
                res = Math.min(res, sessionCount);
                return;
            }

            // try to put task into old session
            for (int i = 0; i < sessionCount; i++) {
                if (sessions[i] + tasks[taskID] <= maxSessionTime) {
                    sessions[i] += tasks[taskID];
                    dfs(taskID - 1, sessionCount);
                    sessions[i] -= tasks[taskID];
                }
            }

            // try to put task into new session
            sessions[sessionCount] += tasks[taskID];
            dfs(taskID - 1, sessionCount + 1);
            sessions[sessionCount] -= tasks[taskID];
        }
    }
}
