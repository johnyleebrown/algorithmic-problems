package Hard.Array;

import java.util.Arrays;

/**
 * 719
 * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.
 */
public class FindKthSmallestPairDistance {

    // O(NlogW + NlogN)
    class Solution {
        public int smallestDistancePair(int[] nums, int k) {
            Arrays.sort(nums);

            int lo = 0;
            int hi = nums[nums.length - 1] - nums[0];
            while (lo < hi) {
                int mi = (lo + hi) / 2;
                int count = 0, left = 0;
                for (int right = 0; right < nums.length; ++right) {
                    while (nums[right] - nums[left] > mi) left++;
                    count += right - left;
                }
                //count = number of pairs with distance <= mi
                if (count >= k) hi = mi;
                else lo = mi + 1;
            }
            return lo;
        }
    }
}
