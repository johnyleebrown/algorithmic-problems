package array.ksum;

import java.util.Arrays;

/**
 * 16
 *
 * ======
 *
 * Task.
 *
 * Given an array nums of n integers and an integer target, find three integers
 * in nums such that the sum is closest to target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ThreeSumClosest {
	/**
	 * Same as {@link ThreeSum} with light mods.
	 */
	public static class Solution {
		public int threeSumClosest(int[] ar, int x) {

			Arrays.sort(ar);
			int n = ar.length;
			int diff = Integer.MAX_VALUE;
			int ans = 0;

			for (int i1 = 0; i1 < n - 2; i1++) {

				int sum = x - ar[i1];
				int i2 = i1 + 1;
				int i3 = n - 1;

				while (i2 < i3) {
					int y = Math.abs(ar[i2] + ar[i3] - sum);
					if (y < diff) {
						diff = y;
						ans = ar[i2] + ar[i3] + ar[i1];
					}

					if (ar[i2] + ar[i3] == sum) {
						return ans;
					} else if (ar[i2] + ar[i3] < sum) {
						i2++;
					} else {
						i3--;
					}
				}
			}

			return ans;
		}
	}
}