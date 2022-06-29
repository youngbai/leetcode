package edu.neu.leetcode.day2DSU;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LC128_Longest_Consecutive_Sequence {

    /*
    Disjoint Set
    Thinking:
    1.a consecutive elements form a disjoint set -> Disjoint Set Data Structure
    2.our goal is find the max size of Disjoint Set, so DSU needs size[]
    3.for each element in nums, we need to check if nums[i]+1, nums[i]-1 exist,
        if exist, we union their indexes, DSU.union(nums[i].index, (nums[i]+1).index)
        else pass
    4.to speed up step 3, use HashMap<num, index> to fetch num's index
    5.final answer is the max size of size[],
      the root contains the max size
     */
    class Solution1_DSU {
        public int longestConsecutive(int[] nums) {
            DSU dsu = new DSU(nums.length);
            //      number   index
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) continue; // prevent duplicate number
                map.put(nums[i], i);
                if (map.containsKey(nums[i] + 1)) dsu.union(i, map.get(nums[i] + 1));
                if (map.containsKey(nums[i] - 1)) dsu.union(i, map.get(nums[i] - 1));
            }
            return dsu.findMax();
        }

        class DSU {
            int[] parent;
            int[] size;

            public DSU(int N) {
                parent = new int[N];
                size = new int[N];
                for (int i = 0; i < N; i++) parent[i] = i;
                Arrays.fill(size, 1);
            }

            public int find(int x) {
                if (parent[x] != x) parent[x] = find(parent[x]);
                return parent[x];
            }

            public void union(int x, int y) {
                int rootX = find(x), rootY = find(y);
                if (size[rootX] <= size[rootY]) {
                    parent[rootX] = rootY;
                    size[rootY] += size[rootX];
                } else {
                    parent[rootY] = rootX;
                    size[rootX] += size[rootY];
                }
            }

            public int findMax() {
                int max = 0;
                for (int s : size) max = Math.max(max, s);
                return max;
            }
        }
    }

    /*
    HashSet
    Feature: contains(element) O(n), because HashSet is implemented with HashMap

    Thinking:
    First turn the input into a set of numbers. That takes O(n) and then we can ask in O(1)
    whether we have a certain number. Then go through the numbers. If the number x is the
    start of a streak (i.e., x-1 is not in the set), then test y = x+1, x+2, x+3, ... and
    stop at the first number y not in the set. The length of the streak is then simply y-x
    and we update our global best with that. Since we check each streak only once, this is
    overall O(n). This ran in 44 ms on the OJ, one of the fastest Python submissions.

    In short, check each num to find the head of a consecutive sequence, then search for its
    tail until fail, then we got the size of the sequence.
     */
    class Solution2_HashSet {
        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int n : nums) set.add(n);  // O(n)

            int best = 0;
            for (int n : nums) {    // O(n)
                if (!set.contains(n - 1)) {  // see if n is the head of a sequence
                    int m = n + 1;
                    while (set.contains(m)) {
                        m++;
                    }
                    best = Math.max(best, m - n);
                }
            }
            return best;
        }
    }

}
