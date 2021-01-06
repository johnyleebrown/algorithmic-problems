package twoPointers.regular.other;

/**
 * 683
 *
 * ======
 *
 * You have n bulbs in a row numbered from 1 to n. Initially, all the bulbs are turned off. We turn
 * on exactly one bulb every day until all bulbs are on after n days.
 *
 * You are given an array bulbs of length n where bulbs[i] = x means that on the (i+1)th day, we
 * will turn on the bulb at position x where i is 0-indexed and x is 1-indexed.
 *
 * Given an integer k, return the minimum day number such that there exists two turned on bulbs that
 * have exactly k bulbs between them that are all turned off. If there isn't such day, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: bulbs = [1,3,2], k = 1
 * Output: 2
 * Explanation:
 * On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
 * On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
 * On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
 * We return 2 because on the second day, there were two on bulbs with one off bulb between them.
 * Example 2:
 *
 * Input: bulbs = [1,2,3], k = 1
 * Output: -1
 *
 *
 * Constraints:
 *
 * n == bulbs.length
 * 1 <= n <= 2 * 104
 * 1 <= bulbs[i] <= n
 * bulbs is a permutation of numbers from 1 to n.
 * 0 <= k <= 2 * 104
 *
 * ======
 *
 * https://leetcode.com/problems/k-empty-slots/
 */
public class KEmptySlots {
	/**
	 * https://www.notion.so/683-K-Empty-Slots-d779473d03c8405799c42a5cf51e23bb
	 */
	public static class Solution1 {
		public int kEmptySlots(int[] bulbs, int k) {

			int n = bulbs.length;
			int[] ar = new int[n];
			for (int i = 0; i < n; i++) {
				ar[bulbs[i] - 1] = i + 1;
			}

			int i = 1;
			int l = 0;
			int r = k + 1;
			int ans = Integer.MAX_VALUE;

			while (r < n) {
				if (i == r) {
					ans = Math.min(ans, Math.max(ar[l], ar[r]));
					l = i;
					r = i + k + 1;
				} else if (ar[l] > ar[i] || ar[r] > ar[i]) {
					l = i;
					r = i + k + 1;
				}
				i++;
			}

			return ans == Integer.MAX_VALUE ? -1 : ans;
		}
	}

	/**
	 * Optimized
	 *
	 * https://www.notion.so/683-K-Empty-Slots-d779473d03c8405799c42a5cf51e23bb
	 */
	public static class Solution2 {
		public int kEmptySlots(int[] bulbs, int k) {

			int n = bulbs.length;
			int[] ar = new int[n];
			for (int i = 0; i < n; i++) {
				ar[bulbs[i] - 1] = i + 1;
			}

			int l = 0;
			int r = k + 1;
			int ans = Integer.MAX_VALUE;

			while (r < n) {
				int j = l + 1;
				int m = r - 1;
				for (; j <= m; j++, m--) {
					if (ar[r] > ar[j] || ar[r] > ar[m] || ar[l] > ar[j] || ar[l] > ar[m]) {
						l = j;
						r = j + k + 1;
						break;
					}
				}
				if (j <= m) {
					continue;
				}

				ans = Math.min(ans, Math.max(ar[l], ar[r]));
				l = r;
				r = r + k + 1;
			}

			return ans == Integer.MAX_VALUE ? -1 : ans;
		}
	}
}