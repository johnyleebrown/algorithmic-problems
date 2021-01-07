package hashtable;

import java.util.TreeSet;

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
 *
 * ======
 *
 * https://www.notion.so/683-K-Empty-Slots-d779473d03c8405799c42a5cf51e23bb
 */
public class KEmptySlots {
	/**
	 * Not optimized
	 */
	public static class Solution1 {
		public int kEmptySlots(int[] ar, int k) {
			TreeSet<Integer> set = new TreeSet<>();
			for (int i = 0; i < ar.length; i++) {
				Integer left = set.lower(ar[i]);
				if (left != null) {
					int between = ar[i] - left - 1;
					if (between == k) {
						return i + 1;
					}
				}
				Integer right = set.higher(ar[i]);
				if (right != null) {
					int between = right - ar[i] - 1;
					if (between == k) {
						return i + 1;
					}
				}
				set.add(ar[i]);
			}
			return -1;
		}
	}

	/**
	 * Optimized
	 */
	public static class Solution2 {
		public int kEmptySlots(int[] ar, int k) {
			TreeSet<Integer> set = new TreeSet<>();
			int countBigger = 1;
			for (int i = 0; i < ar.length; i++) {
				Integer left = set.lower(ar[i]);
				int emptyBulbsToLeft = left == null ? ar[i] - 1 : ar[i] - left - 1;
				if (left != null) {
					if (emptyBulbsToLeft == k) {
						return i + 1;
					}
				}
				Integer right = set.higher(ar[i]);
				int emptyBulbsToRight = right == null ? ar.length - ar[i] : right - ar[i] - 1;
				if (right != null) {
					if (emptyBulbsToRight == k) {
						return i + 1;
					}
				}
				//not == k cuz it would have been the answer already
				if (emptyBulbsToLeft + emptyBulbsToRight > k) {
					countBigger--;
				}

				if (emptyBulbsToLeft > k) {
					countBigger++;
				}

				if (emptyBulbsToRight > k) {
					countBigger++;
				}

				if (countBigger <= 0) return -1;

				set.add(ar[i]);
			}
			return -1;
		}
	}
}