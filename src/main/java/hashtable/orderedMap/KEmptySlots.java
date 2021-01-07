package hashtable.orderedMap;

import java.util.Map;
import java.util.TreeMap;

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

			int n = ar.length;
			TreeMap<Integer, Integer> m = new TreeMap<>();

			// in the beginning all the lamps are turned off
			m.put(0, n - 1);

			for (int i = 0; i < n; i++) {

				// current lamp index
				int curInd = ar[i] - 1;

				// get the segment that contains index curInd
				Map.Entry<Integer, Integer> curSegment = m.floorEntry(curInd);

				// start of the segment
				int start = curSegment.getKey();

				// end of the segment
				int end = curSegment.getValue();

				// if any of the new segments size are equal to k then finish
				// except the segments that start with 0 or end with n - 1
				if ((curInd - start == k && start != 0) || (end - curInd == k && end != n - 1)) {
					return i + 1;
				}

				// replace old segment with new one
				m.put(start, curInd - 1);

				// add secondnew segment
				m.put(curInd + 1, end);
			}

			return -1;
		}
	}

	/**
	 * Optimized
	 */
	public static class Solution2 {
		public int kEmptySlots(int[] ar, int k) {

			int n = ar.length;
			TreeMap<Integer, Integer> m = new TreeMap<>();

			// in the beginning all the lamps are turned off
			m.put(0, n - 1);

			// in the beginning there is 1 segment that has length > k
			int countBigger = 1;

			for (int i = 0; i < n; i++) {

				// current lamp index
				int curInd = ar[i] - 1;

				// get the segment that contains index curInd
				Map.Entry<Integer, Integer> curSegment = m.floorEntry(curInd);

				// start of the segment
				int start = curSegment.getKey();

				// end of the segment
				int end = curSegment.getValue();

				// if any of the new segments size are equal to k then finish
				// except the segments that start with 0 or end with n - 1
				if ((curInd - start == k && start != 0) || (end - curInd == k && end != n - 1)) {
					return i + 1;
				}

				// if the segment that we are splitting might be good
				// (might contains segment of length k)
				if (end - start + 1 > k) countBigger--;

				// if the first new segment might be good
				// (might contains segment of length k)
				if (curInd - start > k) countBigger++;

				// if the second new segment might be good
				// (might contains segment of length k)
				if (end - curInd > k) countBigger++;

				// if we have no more good segments
				// (that might contains segment of length k)
				if (countBigger <= 0) return -1;

				// replace old segment with new one
				m.put(start, curInd - 1);

				// add second new segment
				m.put(curInd + 1, end);
			}

			return -1;
		}
	}
}