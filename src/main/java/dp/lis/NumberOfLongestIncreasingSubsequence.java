package dp.lis;

import java.util.Arrays;

/**
 * 673
 */
public class NumberOfLongestIncreasingSubsequence {

	/**
	 * Similar to LIS, - in addition keep the count of LIS ending at index.
	 */
	public static class Solution {

		public int findNumberOfLIS(int[] nums) {
			int n = nums.length, maxCnt = 0, maxLen = 0;

			// max length of IS ending at index i
			int[] dp = new int[n];
			Arrays.fill(dp, 1);

			// count of LIS ending at i
			int[] cnt = new int[n];
			cnt[0] = 1;

			for (int i = 0; i < n; i++) {
				for (int j = i - 1; j >= 0; j--) {
					if (nums[j] < nums[i]) {
						// if we record a new max length
						if (dp[j] + 1 > dp[i]) {
							// update length - from LIS problem
							dp[i] = dp[j] + 1;
							// if this is a new max length, take the cnt from j
							cnt[i] = cnt[j];
						}
						// if we encounter already max length
						else if (dp[j] + 1 == dp[i]) {
							// other lis of max length ending at i
							// just increment by cnt[j]
							cnt[i] += cnt[j];
						}
					}
				}

				// [2,2,2,2,2]
				// edge case - when we didn't find any IS ending at i
				if (cnt[i] == 0) {
					cnt[i] = 1;
				}

				// update max values
				if (dp[i] > maxLen) {
					maxLen = dp[i];
					maxCnt = cnt[i];
				} else if (dp[i] == maxLen) {
					maxCnt += cnt[i];
				}
			}

			return maxCnt;
		}
	}
}
