package edu.neu.leetcode.day1Trie;

public class LC211_AddSearchWords {

    class WordDictionary {
        TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) node.children[c - 'a'] = new TrieNode();
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }

        public boolean search(String word) {
            return helper(word, 0, root);
        }

        // see if word[pos] is in node.children
        public boolean helper(String word, int pos, TrieNode node) {
            if (pos == word.length()) return node.isWord;
            char c = word.charAt(pos);
            if (c != '.') {
                if (node.children[c - 'a'] == null) return false;
                return helper(word, pos + 1, node.children[c - 'a']);
            } else {
                for (int i = 0; i < 26; i++)
                    if (node.children[i] != null && helper(word, pos + 1, node.children[i]))
                        return true;
                return false;
            }
        }
    }

    class TrieNode {
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }
}
