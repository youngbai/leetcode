package edu.neu.leetcode.day1Trie;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class LC212_WordSearchII {

    /*
    Method1: BruteFore (Without Trie)
    Algo:
        for (word: words):  // O(k), k:number of word
            for (M):        // O(M)
                for (N):    // O(N)
                    dfs();  // O(M*N)
    Time: O(k * M * N * (M*N)) = O(k(MN)^2)
    Space: O(M*N)
    Problems:
    1.boolean visited[M][N], Space: O(M*N)
    - solve: #/c
    2.repeated comparing for each word
    - solve: Trie
    */
    class Solution1 {
        Set<String> res;
        int M, N;

        public List<String> findWords(char[][] board, String[] words) {
            res = new HashSet<>();
            M = board.length;
            N = board[0].length;
            //System.out.printf("M=%d, N=%d%n", M, N);

            for (String word : words) {
                boolean[][] visited = new boolean[M][N];
                for (int x = 0; x < M; x++) {               // O(M)
                    for (int y = 0; y < N; y++) {           // O(N)
                        dfs(board, visited, word, 0, x, y); // O(4k)
                    }
                }
            }
            return new ArrayList<String>(res);
        }

        public void dfs(char[][] board, boolean[][] visited, String word, int pos, int x, int y) {

            if (x < 0 || x >= M || y < 0 || y >= N) return;

            if (visited[x][y]) return;

            if (word.charAt(pos) == board[x][y]) {     // char match
                //System.out.printf("c=%s, board[x][y]=%s%n", word.charAt(pos), board[x][y]);

                if (pos == (word.length() - 1)) {     // find word
                    res.add(word);
                    return;
                }

                // not last letter, continue finding
                visited[x][y] = true;
                dfs(board, visited, word, pos + 1, x - 1, y);
                dfs(board, visited, word, pos + 1, x + 1, y);
                dfs(board, visited, word, pos + 1, x, y - 1);
                dfs(board, visited, word, pos + 1, x, y + 1);
                visited[x][y] = false; // backtracking
            }
        }
    }

    /*
    Method2: With Trie (26-array tree)
    Feature: avoid repeated matching
    Algo:
        for (word: words) trie.addWord(word)  // O(k), k:number of word
        for (M):        // O(M)
            for (N):    // O(N)
                dfs();  // O(M*N)
    Time:  O(k + M * N * (M*N)) = O(k+(MN)^2)
    Space: O(26^max(length of each word)), Trie
    */
    class Solution2 {

        List<String> res;
        int M, N;

        public List<String> findWords(char[][] board, String[] words) {
            res = new ArrayList<>();
            M = board.length;
            N = board[0].length;

            TrieNode root = buildTrie(words);

            for (int i = 0; i < M; i++) {       // O(M)
                for (int j = 0; j < N; j++) {   // O(N)
                    dfs(board, i, j, root);
                }
            }
            return res;
        }

        public void dfs(char[][] board, int i, int j, TrieNode node) {
            // out of boundaries
            if (i < 0 || i >= M || j < 0 || j >= N) return;

            // visited || not found, return
            char c = board[i][j];
            if (c == '#' || node.children[c - 'a'] == null) return;

            // found this char
            // found a complete word
            node = node.children[c - 'a'];
            if (node.word != null) {
                res.add(node.word);
                node.word = null;   // de-duplicate
            }

            // try 4 directions and keep looking
            board[i][j] = '#';
            dfs(board, i + 1, j, node);
            dfs(board, i - 1, j, node);
            dfs(board, i, j + 1, node);
            dfs(board, i, j - 1, node);
            board[i][j] = c;  // backtracking
        }

        public TrieNode buildTrie(String[] words) {
            Trie trie = new Trie();
            for (String word : words) trie.addWord(word);
            return trie.root;
        }
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) node.children[c - 'a'] = new TrieNode();
                node = node.children[c - 'a'];
            }
            node.word = word;
        }
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }


    @Test
    public void test() {

        Solution2 sol = new Solution2();
        //test1
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};

        List<String> res = sol.findWords(board, words);
        System.out.println(res);

        //tes2
        board = new char[][]{
                {'a', 'a'}
        };
        words = new String[]{"aaa"};

        res = sol.findWords(board, words);
        System.out.println(res);
    }

}
