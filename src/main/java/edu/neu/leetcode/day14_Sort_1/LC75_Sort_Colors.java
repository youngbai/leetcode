package edu.neu.leetcode.day14_Sort_1;

public class LC75_Sort_Colors {
    /*
    Thinking:
    - Counting Sort

    Time:  O(N)
    Space: O(1)
     */
    class Solution1_Counting_Sort {
        public void sortColors(int[] nums) {
            int count0 = 0, count1 = 0, count2 = 0;

            for (int num : nums) {  // do counting
                if (num == 0) count0++;
                if (num == 1) count1++;
                if (num == 2) count2++;
            }

            for (int i = 0; i < nums.length; i++) { // counting sort
                if (i < count0) nums[i] = 0;
                else if (i < count0 + count1) nums[i] = 1;
                else nums[i] = 2;
            }
        }
    }

    /*
    Thinking:
    - Dual pivot
    - sort like Quick Sort partition()

    Note:
    - after swap, we should keep i stay, because after swap, the element in current position is changed,
      we should check it again

    Time:  O(N)
    Space: O(1)
     */
    class Solution2_Dual_Pivot {
        public void sortColors(int[] nums) {
            int N = nums.length;
            int left = 0, right = N - 1;

            for (int i = 0; i < N; i++) {
                if (nums[i] == 0 && i > left) swap(nums, i--, left++);
                else if (nums[i] == 2 && i < right) swap(nums, i--, right--);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}
