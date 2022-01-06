package edu.neu.leetcode.day13_Sliding_Window;

public class LC1248_Count_Number_of_Nice_Subarrays {

    /*
    Goals
    - nice - k odd nums
    - num of nice subarray

    Formula
    exact(k) = atMost(k) - atMost(k-1)

    Example
    nums = [1,1,2,1,1], k = 3
    atMost(3)
    num         k=3
    1           2>=0   k--, len=1, [1]
    11          1>=0   k--, len=2, [1] [11]
    112         1>=0   len=3, [2] [12] [112]
    1121        0>=0   k--, len=4, [1] [21] [121] [1121]
    11211       -1<0   k--, need shrink left
    1211        0>=0   k++, shrink left, len=4, [1] [11] [211] [1211]
    [1] [1] [1] [2] [1] 5


    atMost(2)
    num         k=2
    1           1>=0   k--, len=1, [1]
    11          0>=0   k--, len=2, [1] [11]
    112         0>=0   len=3, [2] [12] [112]
    1121        -1<0   k--, need shrink left
    121         0>=0   k++, shrink left, len=3, [1] [21] [121]
    1211        -1<0   k--, need shrink left
    211         0>=0   k++, shrink left, len=3, [1] [11] [211]

    exact(3)=atMost(3)-atMost(2)
    [1121] [1211]


    Time:  O(N)
    Space: O(1)
    */
    class Solution1_Sliding_Window {
        public int numberOfSubarrays(int[] nums, int k) {
            return atMost(nums, k) - atMost(nums, k - 1);
        }

        private int atMost(int[] nums, int k) {
            int res = 0, left = 0;
            for (int i = 0; i < nums.length; i++) {
                // enter window
                if (nums[i] % 2 == 1) k--;
                // shrink left
                while (k < 0)
                    if (nums[left++] % 2 == 1) k++;
                // calculate result
                res += i - left + 1;
            }
            return res;
        }

        // other way to implement atMost
        private int atMost1(int[] nums, int k) {
            int res = 0, left = 0;
            for (int i = 0; i < nums.length; i++) {
                // enter window
                k -= nums[i] % 2;
                // shrink left
                while (k < 0) k += nums[left++] % 2;
                // calculate result
                res += i - left + 1;
            }
            return res;
        }


    }
}
