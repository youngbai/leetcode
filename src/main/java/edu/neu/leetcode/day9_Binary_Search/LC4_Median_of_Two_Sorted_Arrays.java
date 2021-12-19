package edu.neu.leetcode.day9_Binary_Search;

public class LC4_Median_of_Two_Sorted_Arrays {

    /*
    Thinking:
    - median: is used for dividing a set into two equal length subsets,
      that one subset is always greater than the other.
    - For X = [..|......]
          Y = [......|..]
      If we can ensure:
      1) len(left_X) + len(left_Y) == len(right_X) + len(right_Y)
       => partitionX + partitionY = (len(X) + len(Y) + 1) / 2
      2) max(left_X) <= min(right_Y)
      && max(left_Y) <= min(right_X)
       => left(X,Y) <= right(X,Y)
      Then,
        - if len(X,Y) is Even
          median = (max(left_X, left_Y) + min(right_X, right_Y))/2
        - if len(X,Y) is Odd
          median = max(left_X, left_Y)

    - Algo:
    low = 0, high = len(X)
    while (low <= high):
        partitionX = (low + high) / 2
        partitionY = (len(X) + len(Y) + 1) / 2 - partitionX
        if max(left_X) <= min(right_Y) && max(left_Y) <= min(right_X):
            then we found median
            if len(X,Y) is Even:
                median = (max(left_X, left_Y) + min(right_X, right_Y))/2
            if len(X,Y) is Odd:
                median = max(left_X, left_Y)
        else if max(left_X) > min(right_Y):
            we should move partitionX left
            high = partitionX - 1
        else // max(left_Y) > min(right_X)
            we should move partitionX to right
            low = partitionX + 1

    https://www.youtube.com/watch?v=LPFhl65R7ww
    https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn)))-solution-with-explanation
     */
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // if nums length is greater than nums2,
            // switch them so that nums1 is always smaller than nums2
            if (nums1.length > nums2.length)
                return findMedianSortedArrays(nums2, nums1);
            int x = nums1.length, y = nums2.length;
            int low = 0, high = x;
            while (low <= high) {
                int partX = (low + high) / 2;
                int partY = (x + y + 1) / 2 - partX;

                // if partX is 0, it means nothing is there on left side. Use -INF for maxLeftX
                // if partX is length of nums1, then there is nothing on right side. Use +INF for minRightX
                int maxLeftX = (partX == 0)? Integer.MIN_VALUE: nums1[partX - 1];
                int minRightX = (partX == x)? Integer.MAX_VALUE: nums1[partX];
                int maxLeftY = (partY == 0)? Integer.MIN_VALUE: nums2[partY - 1];
                int minRightY = (partY == y)? Integer.MAX_VALUE: nums2[partY];
                System.out.printf("low=%d, high=%d, partX=%d, partY=%d, maxLeftX=%d, minRightX=%d, maxLeftY=%d, minRightY=%d%n", low, high, partX, partY, maxLeftX, minRightX, maxLeftY, minRightY);

                if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                    // We have partitioned array at correct place
                    if ((x + y) % 2 == 0) // Even numbers of elements
                        return (double)(Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                    else                  // Odd numbers of elements
                        return (double)Math.max(maxLeftX, maxLeftY);
                } else if (maxLeftX > minRightY)
                    // move partX to the left
                    high = partX - 1;
                else
                    // move partX to the right
                    low = partX + 1;
            }
            // Only we can come here is if input arrays were not sorted.
            // Throw Exception in that scenario.
            throw new IllegalArgumentException();
        }
    }

}
