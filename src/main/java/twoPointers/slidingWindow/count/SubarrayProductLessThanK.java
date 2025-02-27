package twoPointers.slidingWindow.count;

/**
 * 713
 *
 * ======
 *
 * Task.
 *
 * Your are given an array of positive integers nums. Count and print the number
 * of (contiguous) subarrays where the product of all the elements in the
 * subarray is less than k.
 */
public class SubarrayProductLessThanK {
    public static class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k <= 1) {
                return 0;
            }

            int result = 0;
            int l = 0;
            int product = 1;

            for (int r = 0; r < nums.length; r++) {
                product *= nums[r];

                while (product >= k) {
                    product /= nums[l];
                    l++;
                }

                result += r - l + 1;
            }

            return result;
        }
    }
}
