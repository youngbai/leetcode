package edu.neu.leetcode.day2DSU;

/*
Disjoint Set(Union Find)

Pseudocode:
DSU:
    def MakeSet(x):
        x.parent = x
    def find(x):        // O(Height of tree)
        if x.parent = x:
            return x
        else
            return find(x.parent)
    def union(x,y):     // O(Height of tree)
        xRoot = find(x)
        yRoot = find(y)
        xRoot.parent = yRoot
 */

import java.util.Arrays;

public class DSU {

    // Optimization: with path compression
    class DSU1 {
        int[] parent;

        public DSU1(int N) {
            parent = new int[N];
            for (int i = 0; i < N; i++) parent[i] = i;
        }

        /*
        search for the root of x, O(Height of tree)
        Problem: the larger/higher tree will be the subtree of smaller tree,
        which leads the whole tree much higher than before.

        Optimization1:
        O(size), size: size of tree
        Improve: let smaller tree attach to larger tree, so size of larger tree is not increased.
        See DSU2.java

        Optimization2:
        O(H), H: height of tree
        Improve: let lower tree attach to higher tree, so height of higher tree is not increased.
        See DSU3.java
         */
        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]); // path compression
            return parent[x];
        }

        /*
        O(Height of tree)
        Problem: the height of tree could be very high, because
         */
        public void union(int x, int y) {
            parent[find(x)] = find(y);  // path compression
        }
    }

    // Optimization: Improve with size(weighted)
    class DSU2 {
        int[] parent;
        int[] size;

        public DSU2(int N) {
            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) parent[i] = i;
            Arrays.fill(size, 1);
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        // smaller tree attach to larger tree
        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return;
            if (size[rootX] <= size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                parent[rootX] += size[rootY];
            }

        }
    }

    // Optimization: Improve with ranked
    class DSU3 {
        int[] parent;
        int[] rank;

        public DSU3(int N) {
            parent = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) parent[i] = i;
            Arrays.fill(rank, 1);
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        // lower tree attach to higher tree
        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return;
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else { // increase rank when rank of rootX equals to that of rootY
                parent[rootX] = rootY;
                rank[rootY]++;
            }
        }
    }
}

