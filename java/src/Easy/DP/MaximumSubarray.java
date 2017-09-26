package Easy.DP;

/**
 * 53
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 */
public class MaximumSubarray {

    // Kadane's Algorithm
    class Solution{
        public int maxSubArray(int[] A) {
            int maxSoFar = A[0], maxEndingHere = A[0];
            for (int i = 1; i < A.length; ++i) {
                maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
                maxSoFar = Math.max(maxSoFar, maxEndingHere);
            }
            return maxSoFar;
        }
    }

    class Solution2 {
        public int maxSubArray(int[] A) {
            int max = Integer.MIN_VALUE, sum = 0;
            for (int i = 0; i < A.length; i++) {
                if (sum < 0) sum = A[i];
                else sum += A[i];
                if (sum > max) max = sum;
            }
            return max;
        }
    }

    // O(n^3)
    class Solution3 {
        public int maxSubArray(int[] nums) {
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= nums.length; i++) {
                for (int j = 0; j < nums.length && j + i <= nums.length; j++) {
                    int temp = 0;
                    for (int k = j; k < j + i; k++) temp += nums[k];
                    max = Math.max(max, temp);
                }
            }
            return max;
        }
    }
}
