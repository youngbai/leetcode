package edu.neu.leetcode.day1Trie;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LC1707_MaxXORFromArray {

    /*
    Thinking:
    Method1: brute force
    M=len(num), N=len(queries)
    for xi, mi in queries:      // O(N)
        arr = num[...] <= mi    // Time:O(M), Space:O(M)
        for e in arr:           // O(M)
            res[i] = max(res[i], e^xi) // O(32), problem: too much repeated bit comparing
    Time:  O(N * (M + M * 32)) = O(N * 33M)
    Space: O(M)

    Prob:
        - for each xi and e, do e^xi // O(N*M*32), problem: too much repeated bit comparing
        x1 XOR nums // O(M*32)
        x2 XOR nums // O(M*32)
        ...
        - Fix: bitwise Trie, put nums into Trie, so `x1 XOR nums` -> O(32), not O(M*32)
        - Note: nums is not ordered, queries is not ordered, so sort them first
     Prob:
        - after sorting queries on mi, we need to put final answer to its original position
        - Fix:
        original : [(x1,m1),   (x2,m2),   ..., (xi,mi),   ...]
        add index: [(x1,m1,1), (x2,m2,2), ..., (xi,mi,i), ...]
        sorted:    [(x5,m5,5), (x3,m3,3), ..., (xi,mi,i), ...]

    Method2: bitwise Trie
    Algo:
        orderedQueries = add index to queries, (xi,mi)->(xi,mi,i)   // Time:O(N), Space:O(N)
        sort(nums)  // O(MlogM)
        sort(orderedQueries by mi)  // O(NlogN)
        Trie trie = new Trie()
        j = 0
        for xi, mi, idx in orderedQueries:  // O(N)
            // build trie
            for j<M; j++:                   // total O(M), because walk through each element once
                if nums[j] <= mi:
                    trie.addNum(nums[j])
            // find max XOR, put answer to res[idx]
            res[idx] = trie.findMaxXor(xi)  // O(32)

        def findMaxXor(num):
            for i=31 to 0:
                try to find oppoBit for each bit of num
    */
    class Solution {
        public int[] maximizeXor(int[] nums, int[][] queries) {
            int[] res = new int[queries.length];
            for (int i = 0; i < res.length; i++) res[i] = -1;
            int[][] orderedQueries = new int[queries.length][3];
            for (int i = 0; i < queries.length; i++) {
                orderedQueries[i][0] = queries[i][0];   // xi
                orderedQueries[i][1] = queries[i][1];   // mi
                orderedQueries[i][2] = i;               // i
            }

            Arrays.sort(nums);
            Arrays.sort(orderedQueries, (a, b) -> a[1] - b[1]);

            Trie trie = new Trie();
            int j = 0;
            for (int i = 0; i < orderedQueries.length; i++) {
                int x = orderedQueries[i][0], m = orderedQueries[i][1], idx = orderedQueries[i][2];
                while (j < nums.length && nums[j] <= m) {
                    trie.insert(nums[j]);
                    j++;
                }
                res[idx] = trie.findMaxXor(x);
            }
            return res;
        }
    }

    class Trie {
        TrieNode root = new TrieNode();

        class TrieNode {
            TrieNode[] children = new TrieNode[2];
        }

        public void insert(int n) {
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (n >> i) & 1;
                if (node.children[bit] == null) node.children[bit] = new TrieNode();
                node = node.children[bit];
            }
        }

        public int findMaxXor(int n) {
            int res = 0;
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (n >> i) & 1;
                int oppBit = bit ^ 1;
                if (node.children[oppBit] != null) {
                    res += (1 << i);
                    node = node.children[oppBit];
                } else {
                    node = node.children[bit];
                }
                if (node == null) return -1;
            }
            return res;
        }
    }


    @Test
    public void test() {

        int[] nums = new int[]{0, 1, 2, 3, 4};
        int[][] queries = new int[][]{
                {3, 1}, {1, 3}, {5, 6}
        };

        Solution sol = new Solution();
        int[] res = sol.maximizeXor(nums, queries);
        System.out.println(Arrays.toString(res));

        nums = new int[]{5, 2, 4, 6, 6, 3};
        queries = new int[][]{
                {12, 4}, {8, 1}, {6, 3}
        };
        res = sol.maximizeXor(nums, queries);
        System.out.println(Arrays.toString(res));


    }


}
