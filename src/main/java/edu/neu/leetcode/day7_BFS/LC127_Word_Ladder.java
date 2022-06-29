package edu.neu.leetcode.day7_BFS;

import java.util.*;

public class LC127_Word_Ladder {

    /*
    Thinking: BFS
    - problem -> Graph traversal
    - shorted path -> BFS
    - count levels

    Time:  O(M^2 * N)
        - traverse all words in wordlist, O(N)
        - neighbors() O(M^2)
        - M is the length of each word
        - N is the total number of words in the input word list
    Space: O(M * N), Set words
     */
    class Solution1_0 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> set = new HashSet<>(wordList);      // NOTE: list to set
            Queue<String> q = new LinkedList<>();

            set.remove(beginWord);  // visit source vertex
            q.offer(beginWord);     // offer source vertex to q

            int step = 1, N = beginWord.length();
            while (!q.isEmpty()) {
                // traverse this level
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();
                    if (cur.equals(endWord)) return step;

                    // offer its unvisited neighbors to Queue
                    for (int j = 0; j < N; j++) {
                        for (char letter = 'a'; letter <= 'z'; letter++) {
                            StringBuilder next = new StringBuilder(cur);    // NOTE: String -> StringBuilder, and setCharAt(index, char)
                            next.setCharAt(j, letter);                      // NOTE: setCharAt()
                            String nextWord = next.toString();              // NOTE: StringBuilder -> String
                            if (set.contains(nextWord)) {
                                if (nextWord.equals(endWord)) return step + 1;
                                set.remove(nextWord);   // visit(neighbor)
                                q.offer(nextWord);      // offer neighbor to Queue
                            }
                        }
                    }
                }
                step++;
            }
            return 0;
        }
    }

    // same as Solution1_0
    class Solution1_1 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> words = new HashSet<>(wordList); // is used to avoid repeated access to same word
            Queue<String> q = new LinkedList<>();
            words.remove(beginWord);
            q.offer(beginWord);
            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                level++;
                for (int i = 0; i < size; i++) { // traverse this level
                    String cur = q.poll();
                    if (cur.equals(endWord)) return level;
                    for (String neigh : neighbors(cur)) {
                        if (words.contains(neigh)) {
                            words.remove(neigh);
                            q.offer(neigh);
                        }
                    }
                }
            }
            return 0;
        }

        public List<String> neighbors(String word) {
            List<String> res = new ArrayList<>();
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    res.add(new String(chars));
                }
                chars[i] = temp;
            }
            return res;
        }
    }


    /*
    Thinking: Bidirectional BFS
    - Time Complexity increases exponentially along with level
    - cut down Time Complexity, Bidirectional BFS (begin->end, end->begin)

    Time:  O(M^2 * N)
        - traverse all words in wordlist, O(N)
        - neighbors() O(M^2)
    Space: O(M * N), beginSet, endSet, words set
     */
    class Solution2 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> words = new HashSet<>(wordList);
            if (!words.contains(endWord)) return 0;
            Set<String> beginSet = new HashSet<>();
            Set<String> endSet = new HashSet<>();
            beginSet.add(beginWord);
            endSet.add(endWord);
            int length = 1;

            while (!beginSet.isEmpty() && !endSet.isEmpty()) {

                // optimize: start from the set with less words
                if (beginSet.size() > endSet.size()) {
                    Set<String> temp = beginSet;
                    beginSet = endSet;
                    endSet = temp;
                }

                // traverse all words in beginSet,
                // check if their neighbors exists in endSet.
                // if in endSet, beginSet and endSet meet
                Set<String> newBeginSet = new HashSet<>();
                for (String word: beginSet) {
                    for (String neigh : neighbors(word)) {
                        if (endSet.contains(neigh)) return length + 1;
                        if (words.contains(neigh)) {
                            words.remove(neigh);
                            newBeginSet.add(neigh);
                        }
                    }
                }
                beginSet = newBeginSet;
                length++;
            }
            return 0;
        }

        public List<String> neighbors(String word) {
            List<String> res = new ArrayList<>();
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    res.add(new String(chars));
                }
                chars[i] =  temp;
            }
            return res;
        }

    }

}
