package edu.neu.leetcode.day24_tree.p3_BST;

public class LC96_Unique_Binary_Search_Trees {


    /*
    - BST
    - DP

    - Transition Function
    1. G(n)=SUM(f(i,n))
    2. f(i,n)=G(i-1)*G(n-i)
    => G(n)=SUM(G(i-1)*G(n-i)), where 1<=i<=n

    e.g.
    G(3)=G(0)*G(2)+G(1)*(1)+G(2)*G(0)
    G(2)=G(0)*G(1)+G(1)*G(0)
    G(1)=1  | base case
    G(0)=1  | base case
     */
    class Solution1_Iteration {
        public int numTrees(int n) {
            int[] G = new int[n + 1];
            // base case
            G[0] = 1;
            G[1] = 1;

            for (int i = 2; i <= n; i++)
                for (int j = 1; j <= i; j++)
                    G[i] += G[j - 1] * G[i - j];

            return G[n];
        }
    }


    class Solution2_Recursion {
        public int numTrees(int n) {
            Integer[] mem = new Integer[n + 1];
            return dfs(n, mem);
        }

        private int dfs(int n, Integer[] mem) {
            // base case
            if (n <= 1) return 1;

            // hit mem success
            if (mem[n] != null) return mem[n];

            // hit mem fail
            int res = 0;
            for (int i = 1; i <= n; i++)
                res += dfs(i - 1, mem) * dfs(n - i, mem);
            mem[n] = res;

            return res;
        }
    }

}
