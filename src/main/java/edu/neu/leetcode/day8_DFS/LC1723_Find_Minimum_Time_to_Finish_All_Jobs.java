package edu.neu.leetcode.day8_DFS;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class LC1723_Find_Minimum_Time_to_Finish_All_Jobs {

    /*
    Thinking:
    - DFS
    - DFS pruning
      - prune1: sort and start from the biggest job, so curMaxTime easily becomes larger than global result,
        so that it can pruned by prune2
      - prune2: already >= global result, so it is not good assignment, just return
      - prune3: if two worker with same workload sum[i], only try the first one, ignore others

    Worst Time: (k^N), but pruning makes it better
    Space: O(N), hashset to store the used path
     */
    class Solution1_DFS_Pruning {
        int res = Integer.MAX_VALUE;
        public int minimumTimeRequired(int[] jobs, int k) {
            Arrays.sort(jobs);  // prune1: sort and start from the biggest job, so curMaxTime easily becomes larger than global result, so that it can pruned by prune2
            int[] sum = new int[k]; // workload for each worker
            dfs(jobs, jobs.length - 1, sum);
            return res;
        }

        private void dfs(int[] jobs, int index, int[] sum) {
            int curMaxTime = Arrays.stream(sum).max().getAsInt();   // current max time

            // assigned all jobs, get result
            if (index < 0) {
                res = Math.min(res, curMaxTime);
                return;
            }

            // prune2: already >= global result, so it is not good assignment, just return
            if (curMaxTime >= res) return;

            // try different assignment by backtracking
            Set<Integer> used = new HashSet<>();
            for (int i = 0; i < sum.length; i++) {
                // prune3: if two workers with same workload sum[i], only try the first one, ignore others
                if (!used.add(sum[i])) continue;
                sum[i] += jobs[index];
                dfs(jobs, index - 1, sum);
                sum[i] -= jobs[index];
            }

        }
    }
}
