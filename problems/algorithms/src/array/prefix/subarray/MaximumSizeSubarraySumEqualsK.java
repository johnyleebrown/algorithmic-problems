package array.prefix.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * 325
 */
public class MaximumSizeSubarraySumEqualsK {
	/**
	 * @formatter:off
	 * calculating prefix to find if we have a subarray with sum:
	 * pre[cur]-pre[l]==k
	 *
	 * we just keep all pre[i] in map so we could search for it quickly:
	 * pre[l]=pre[cur]-k
	 * @formatter:on
	 */
	public static class Solution {
		public int maxSubArrayLen(int[] a, int sum) {
			Map<Integer, Integer> m = new HashMap<>();
			m.put(0, -1); // base case

			int pre = 0;
			int ans = 0;

			for (int i = 0; i < a.length; i++) {
				pre += a[i];

				if (m.containsKey(pre - sum)) {
					ans = Math.max(ans, i - m.get(pre - sum));
				}

				//since we want the max length - put only once
				m.putIfAbsent(pre, i);
			}

			return ans;
		}
	}
}
