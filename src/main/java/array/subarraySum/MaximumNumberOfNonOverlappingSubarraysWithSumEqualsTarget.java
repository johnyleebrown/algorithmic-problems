package array.subarraySum;

import java.util.HashMap;
import java.util.Map;

/**
 * 1546
 *
 * ======
 *
 * Task.
 *
 * Given an array nums and an integer target.
 *
 * Return the maximum number of non-empty non-overlapping subarrays such that the sum of values in
 * each subarray is equal to target.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget {
	/**
	 * 560 + greedy 'maximum number of non-overlapping intervals', in which we sort by end of
	 * intervals and each time we choose the first interval that will not overlap the last that
	 * we chose.
	 */
	public static class Solution {
		public int maxNonOverlapping(int[] ar, int x) {
			Map<Integer, Integer> map = new HashMap<>();
			map.put(0, -1);
			int prefixSum = 0;
			int ans = 0;
			int prevEndIndex = -1;

			for (int i = 0; i < ar.length; i++) {
				prefixSum += ar[i];

				if (map.containsKey(prefixSum - x) && map.get(prefixSum - x) >= prevEndIndex) {
					ans++;
					prevEndIndex = i;
				}

				map.put(prefixSum, i);
			}

			return ans;
		}
	}
}