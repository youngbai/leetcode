package edu.neu.leetcode.day9_Binary_Search;

public class LC278_First_Bad_Version {

    class VersionControl {
        boolean isBadVersion(int n) {
            return false;
        }
    }

    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int left = 0, right = n;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (!isBadVersion(mid)) left = mid + 1; // if mid is Good, move left to mid + 1
                else right = mid - 1;
            }
            // mid is the last good, next left is bad, so return left
            return left;
        }
    }
}
