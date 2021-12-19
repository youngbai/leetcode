package edu.neu.leetcode.day1Trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/*
Why we need Trie?
Question: nums[...], len(nums)=N, x, find the max(x^nums[i])
1.Without Trie
for i=0 to n:       // O(N)
    max(x^nums[i])  // O(32)
Time: O(32N)

2.With Trie
for i=0 to n:       // O(n)
    trie.insert(nums[i)
trie.findMaxXor(x)  // O(32)
Time: O(N+32)
Note: Building Trie costs O(n), but it can be reused in constant time complexity O(32),
That's why we use Trie.
 */
public class LC421_MaxXOR {

    /*
    Thinking:
    Solution: try to find the opposite bit from high position,
    maximum feature: higher position is 1
    Method1: brute force
    Problem: too much repeated matching for every bit
        - Trie (2-array trie)

    Method1: brute force
    n=len(nums)
    for i=0 to n:                                      // O(n)
        for j=0 to n:                                  // O(n)
            res = max(nums[i]^nums[j]), where i != j   // O(32)
    Time:  O(32n^2)
    Space: O(1)

    Method2: Trie (2-array trie)
    for every num: trie.addNum(num)             // O(n)
    for every num:                              // O(n)
        res = max(res, trie.findMaxXor(num))    // O(32)

    Time:  O(2n+32)
    Space: O(2^(32+1) - 1),  2: each bit(0,1), 32: int 32bit
    */
    class Solution {
        public int findMaximumXOR(int[] nums) {
            int res = Integer.MIN_VALUE;
            TrieNode root = new TrieNode();
            for (int num : nums) root.addNum(root, num);
            for (int num : nums)
                res = Math.max(res, root.findMaxXor(root, num));
            return res;
        }
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[2];

        public void addNum(TrieNode root, int num) {
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) { // walk through each bit
                int bit = (num >> i) & 1;  // i'th bit
                if (node.children[bit] == null) node.children[bit] = new TrieNode();
                node = node.children[bit];
            }
        }

        public int findMaxXor(TrieNode root, int num) {
            int res = 0;
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                int oppoBit = bit ^ 1;
                if (node.children[oppoBit] != null) {
                    res += (1 << i);
                    node = node.children[oppoBit];
                } else {
                    node = node.children[bit];
                }
            }
            return res;
        }
    }


    @Test
    public void test() {
        Solution sol = new Solution();
        assertEquals(28, sol.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
        assertEquals(0, sol.findMaximumXOR(new int[]{0}));
        assertEquals(6, sol.findMaximumXOR(new int[]{2, 4}));
        assertEquals(10, sol.findMaximumXOR(new int[]{8, 10, 2}));
        assertEquals(127, sol.findMaximumXOR(new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70}));

    }

}
