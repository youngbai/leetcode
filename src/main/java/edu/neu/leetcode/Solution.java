package edu.neu.leetcode;

public class Solution {
    public boolean isPalindrome(String s) {
        //1.filter out number & char; 2.reverse and equals
        String filteredS = _filterNonNumberAndChar(s);
        String reversedS = _reverseString(filteredS);
        return reversedS.equalsIgnoreCase(filteredS);
    }
    
    private String _reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }
    
    private String _filterNonNumberAndChar(String s) {
        return s.replaceAll("[^A-Za-z0-9]", "");
    }


    public static void main(String[] args) {
        new Solution().isPalindrome("Zeus was deified, saw Suez.");
    }
}


