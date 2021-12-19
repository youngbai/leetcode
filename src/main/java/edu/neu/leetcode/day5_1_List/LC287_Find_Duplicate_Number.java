package edu.neu.leetcode.day5_1_List;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC287_Find_Duplicate_Number {

    /*
    Modify the origin array

    Time:  O(nlogn) - Arrays.sort()
    Space: O(1)
     */
    class Solution1 {
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++)
                if (nums[i - 1] == nums[i]) return nums[i];
            return -1;
        }
    }

    /*
    Without modifying the origin array

    Time:  O(n) - traverse each element
    Space: O(n) - Set<Integer>
     */
    class Solution2 {
        public int findDuplicate(int[] nums) {
            Set<Integer> seen = new HashSet<>();
            for (int n : nums)
                if (!seen.add(n)) return n;
            return -1;
        }
    }

    /*
    Thinking:
    - use the value as index
    - the same index will modify the nums[index] twice

    Modify the origin array

    Time:  O(n) - traverse each element
    Space: O(1)
     */
    class Solution3 {
        public int findDuplicate(int[] nums) {
            for (int i = 0; i < nums.length; i++) {     // O(n)
                int index = Math.abs(nums[i]);
                if (nums[index] < 0) return index;
                else nums[index] = - nums[index];
            }
            return -1;
        }
    }

    /*
    Thinking:
    - use binary search
    - consider the density of each side, because duplicate number increase the density

    e.g
    low mid high
    - if the count of numbers(<=mid) <= mid, then duplicate number must be in [mid+1, high] this section
    - else duplicate number must be in [low, mid]
    - when low equals high, that means we found the duplicate number, which is low or high

    Without modifying the origin array

    Time:  O(nlogn) - binary search, and traverse each element
    Space: O(1)
     */
    class Solution4 {
        public int findDuplicate(int[] nums) {
            int low = 0, high = nums.length - 1;
            while (low < high) {    // binary search, O(n)
                int mid = low + (high - low) / 2;
                int cnt = 0;
                for (int n : nums) if (n <= mid) cnt++; // traverse each element, O(n)
                if (cnt <= mid) low = mid + 1;
                else high = mid;
            }
            return low;
        }
    }

    /*
    Thinking:
    - treat each element as an index pointing to another element, so this array becomes an linked list,
        but this linked list has a cycle. The cycle entrance point is the duplicate number
    - this problem can be transformed int LC142_Linked_List_Cycle_II
        which try to find the cycle entrance point

    Without modifying the origin array

    Time:  O(n) - traverse each element
    Space: O(1)
     */
    class Solution5_1 {
        public int findDuplicate(int[] nums) {
            int slow = 0, fast = 0, N = nums.length;
            while (fast < N && nums[fast] < N) {
                slow = nums[slow];
                fast = nums[nums[fast]];
                if (slow == fast) {
                    fast = 0;
                    while (slow != fast) {
                        slow = nums[slow];
                        fast = nums[fast];
                    }
                    return slow;
                }
            }
            return -1;
        }
    }

    /*
    Another way to implementation of Solution5_1.
    Difference:
    - Solution5_1 still works when the length of array is 1,
      and it can figure out if the duplicate number exists.
    - Solution5_2 can NOT work when the lengths of array is 1, and it only works if length > 1.
      it can NOT figure out if the duplicate number does NOT exist.
     */
    class Solution5_2 {
        public int findDuplicate(int[] nums) {
            int slow = 0, fast = 0;
            do {
                slow = nums[slow];
                fast = nums[nums[fast]];
            } while (slow != fast);
            fast = 0;
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }
    }
}
