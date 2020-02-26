package array.regular;

/**
 * 724
 *
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 */
public class FindPivotIndex {

    // O(n), O(1)
    class Solution {
        public int pivotIndex(int[] nums) {
            int sum = 0, leftsum = 0;
            for (int x: nums) sum += x;
            for (int i = 0; i < nums.length; ++i) {
                if (leftsum == sum - leftsum - nums[i]) return i;
                leftsum += nums[i];
            }
            return -1;
        }
    }
}
