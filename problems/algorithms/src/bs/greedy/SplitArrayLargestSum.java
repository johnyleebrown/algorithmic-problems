package bs.greedy;

/**
 * 410
 *
 * ======
 *
 * Task.
 *
 * Given an array which consists of non-negative integers and an integer m, you
 * can split the array into m non-empty continuous subarrays. Write an algorithm
 * to minimize the largest sum among these m subarrays.
 *
 * Note: If n is the length of array, assume the following constraints are
 * satisfied:
 *
 * 1 ≤ n ≤ 1000 1 ≤ m ≤ min(50, n)
 *
 * ======
 *
 * Source: Leetcode
 */
public class SplitArrayLargestSum {
    /**
     * Example:
     * [7,2,5,10,8]
     * 7,25
     * 9,23
     * 14,18 <--- ans (3 and 2)
     * 24,8
     *
     * - lo is max number because if m=n then biggest subarray sum will the
     * biggest element out there.
     * - hi is sum of elements because if we split to 1 subarray it will contain
     * all elements.
     * - algo
     * We guess the sum, then greedily count how many subarrays we can have
     * with this maximum sum. We can see from example that the answer we seek is
     * when the sums of subarrays are the closest to each other (14,18). So, one
     * subarray sum is big and others are almost the same as the biggest one. In
     * other words, we try to stack as much elements in one group as possible so
     * it doesn't overflow max sum.
     */
    public static class Solution {
        public int splitArray(int[] nums, int m) {
            int lo = getMax(nums);
            int hi = getSum(nums) + 1;
            while (hi - lo > 0) {
                int mid = lo + (hi - lo) / 2;
                int x = getSubarraysForMaxSum(nums, mid);
                if (x <= m) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            return lo;
        }

        private int getSum(int[] a) {
            int sum = 0;
            for (int i = 0; i < a.length; i++) {
                sum += a[i];
            }
            return sum;
        }

        private int getMax(int[] a) {
            int max = a[0];
            for (int i = 0; i < a.length; i++) {
                max = Math.max(max, a[i]);
            }
            return max;
        }

        private int getSubarraysForMaxSum(int[] a, int maxSum) {
            int sum = 0;
            int c = 1;
            for (int i = 0; i < a.length; i++) {
                sum += a[i];
                if (sum > maxSum) {
                    sum = a[i];
                    c++;
                }
            }
            return c;
        }
    }
}