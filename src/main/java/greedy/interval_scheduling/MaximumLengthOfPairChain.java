package greedy.interval_scheduling;

import java.util.Arrays;

/**
 * 646
 */
public class MaximumLengthOfPairChain {

	/**
	 * Consider pairs as jobs, with [start time, end time],
	 * Then the problem is converted to ask the maximum jobs we can accomplish.
	 */
	public static class Solution {

		public int findLongestChain(int[][] pairs) {
			if (pairs == null || pairs.length == 0) return 0;
			Arrays.sort(pairs, (a, b) -> (a[1] - b[1]));
			int res = 1, end = pairs[0][1];
			for (int i = 1; i < pairs.length; i++) {
				if (pairs[i][0] > end) {
					res++;
					end = pairs[i][1];
				}
			}
			return res;
		}
	}
}
