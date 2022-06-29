package edu.neu.leetcode.day9_Binary_Search;

public class LC704_Binary_Search {

    /*
    Recursive
     */
    class Solution1 {
        public int search(int[] nums, int target) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }

        private int binarySearch(int[] nums, int low, int high, int target) {
            if (low > high) return -1;
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) return binarySearch(nums, low, mid - 1, target);
            if (nums[mid] < target) return binarySearch(nums, mid + 1, high, target);
            return mid;
        }
    }

    /*
    Iterative
     */
    class Solution2 {
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == target) return mid;
                else if (nums[mid] > target) high = mid - 1;
                else low = mid + 1;
            } // high, low
            return -1;
        }
    }


}
