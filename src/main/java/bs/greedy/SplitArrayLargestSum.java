package bs.greedy;

import java.util.Arrays;

/**
 * 410
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
			int hi = getSum(nums);
			int result = lo;

			while (hi - lo >= 0) {
				int mid = lo + (hi - lo) / 2;
				int x = getSubarrayCountForMaxSum(nums, mid);
				if (x < m) {
					hi = mid - 1;
				} else if (x > m) {
					lo = mid + 1;
				}
				// we want still to continue search to get the minimum
				// even when we got m splits
				// but we want to record the current count (= mid) if
				// we would go out of m count
				else {
					result = mid;
					hi = mid - 1;
				}
			}

			return result;
		}

		private int getSum(int[] ar) {
			return Arrays.stream(ar).sum();
		}

		private int getMax(int[] ar) {
			return Arrays.stream(ar).max().getAsInt();
		}

		private int getSubarrayCountForMaxSum(int[] ar, int maxSum) {
			int sum = 0;
			int splitCount = 1;
			for (int num : ar) {
				sum += num;
				if (sum > maxSum) {
					sum = num;
					splitCount++;
				}
			}
			return splitCount;
		}
	}
}