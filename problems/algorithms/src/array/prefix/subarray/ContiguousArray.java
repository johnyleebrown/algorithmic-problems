package array.prefix.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * 525
 *
 * ======
 *
 * Task.
 *
 * Given a binary array, find the maximum length of a contiguous subarray with
 * equal number of 0 and 1.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ContiguousArray {
	/**
	 * @formatter:off
	 * In 325 as key we used prefix sum, here the key is the difference:
	 * a1 + x = a2; b1 + x = b2; a2 - b2 = (a1 + x) - (b1 + x)
	 * => a2 - b2 = a1 - b1. a2 and b2 is current count of 1 and 0, a1,b1 - previous.
	 * @formatter:on
	 */
	public static class Solution {
		public int findMaxLength(int[] ar) {
			Map<Integer, Integer> m = new HashMap<>();
			m.put(0, -1);

			int ans = 0;
			int pre0 = 0;
			int pre1 = 0;

			for (int i = 0; i < ar.length; i++) {
				if (ar[i] == 0) {
					pre0++;
				} else {
					pre1++;
				}

				int diff = pre0 - pre1;
				if (m.containsKey(diff)) {
					ans = Math.max(ans, i - m.get(diff));
				}
				m.putIfAbsent(diff, i);
			}

			return ans;
		}
	}
}