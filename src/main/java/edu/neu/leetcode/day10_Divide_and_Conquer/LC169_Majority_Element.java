package edu.neu.leetcode.day10_Divide_and_Conquer;

import java.util.Arrays;

public class LC169_Majority_Element {

    /*
    Thinking:
    - Divide and Conquer

    Time:  O(nlogn), like merge sort
    Space: O(1)
     */
    class Solution1_Divide_Conquer {
        public int majorityElement(int[] nums) {
            return divide(nums, 0, nums.length - 1);
        }

        // return major element in section [left, right]
        private int divide(int[] nums, int left, int right) {
            if (left == right) return nums[left];
            int mid = left + (right - left) / 2;
            int leftMajorElem = divide(nums, left, mid);
            int rightMajorElem = divide(nums, mid + 1, right);
            if (leftMajorElem == rightMajorElem) return leftMajorElem;

            int leftCount = conquer(nums, leftMajorElem, left, right);
            int rightCount = conquer(nums, rightMajorElem, left, right);
            return leftCount > rightCount ? leftMajorElem : rightMajorElem;
        }

        // return count of target in section [left, right]
        private int conquer(int[] nums, int target, int left, int right) {
            int count = 0;
            for (int i = left; i <= right; i++) {
                if (nums[i] == target) count++;
            }
            return count;
        }
    }

    /*
    Thinking:
    - voting

    Ref: https://www.cs.utexas.edu/~moore/best-ideas/mjrty/
    https://www.cs.utexas.edu/~moore/best-ideas/mjrty/example.html

    Intuition:
    - The major element will get more than floor(n/2) votes,
    - Other elements decrease the major element votes
    - In the end, the candidate must be the answer

    Time:  O(n)
    Space: O(1)
     */
    class Solution2_Voting {
        public int majorityElement(int[] nums) {
            int count = 0, candidate = 0;
            for (int num : nums) {
                if (count == 0) candidate = num;
                count += (num == candidate)? 1 : -1;
            }
            return candidate;
        }
    }


    /*
    Thinking:
    - after array being sorted
    - the major element must be in the middle of array

    Time:  O(nlogn) - Arrays.sort()
    Space: O(1)
     */
    class Solution3_Sort {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }
}
