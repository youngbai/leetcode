package edu.neu.leetcode.day8_DFS;

import java.util.List;
import java.util.ArrayList;

public class LC131_Palindrome_Partitioning {

    /*
    Thinking:
    - DFS, Backtracking
    - traverse all possible substring, and check if it is palindrome

    Time:  O(N*2^N)
    - 2^N: there could be 2^N possible substrings in the worst case
    - N: isPalindrome() takes O(N)
    Space: O(N), N is the maximum depth of the recursive tree

    Ref: https://leetcode.com/problems/palindrome-partitioning/solution/
     */
    class Solution1_Backtracking {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            dfs(res, s, new ArrayList<String>(), 0);
            //for (boolean[] r: dp) System.out.println(Arrays.toString(r));
            return res;
        }

        private void dfs(List<List<String>> res, String s, List<String> path, int start) {
            if (start >= s.length()) {
                res.add(new ArrayList<String>(path));
                return;
            }

            for (int end = start; end < s.length(); end++) {
                if (isPalindrome(s, start, end)) {
                    path.add(s.substring(start, end + 1));
                    dfs(res, s, path, end + 1);
                    path.remove(path.size() - 1);
                }
            }
        }

        private boolean isPalindrome(String s, int l , int h) { // O(N)
            while (l < h) {
                if (s.charAt(l++) != s.charAt(h--)) {
                    return false;
                }
            }
            return true;
        }
    }



    /*
    Thinking:
    - DFS, Backtracking
    - use DP to optimize isPalindrome()

    Example:
                                        abba
                     /				  |		 \		\
                   a|bba		    ab|ba	abb|a	abba
            /		  \		\
        a|b|ba		a|bb|a	a|bba
       /	  \		  |
    a|b|b|a	a|b|ba	a|bb|a

    Time Complexity: O(N*2^N), where N is the length of string s. In the worst case, there could be 2^N possible
        substrings and it will take O(N) to generate each substring using substr as in Approach 1. However, we are
        eliminating one additional iteration to check if substring is a palindrome or not.

    Space Complexity: O(N*N), where N is the length of the string s. The recursive call stack would require N space
        as in Approach 1. Additionally we also use 2 dimensional array dp of size N*N.
     */
    class Solution2_Backtracking_DP {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            boolean[][] dp = new boolean[s.length()][s.length()];
            dfs(res, s, new ArrayList<String>(), 0, dp);
            return res;
        }

        private void dfs(List<List<String>> res, String s, List<String> path, int start, boolean[][] dp) {
            if (start >= s.length()) {
                res.add(new ArrayList<String>(path));
                return;
            }

            for (int end = start; end < s.length(); end++) {
                if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                    dp[start][end] = true;
                    path.add(s.substring(start, end + 1));
                    dfs(res, s, path, end + 1, dp);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

}
