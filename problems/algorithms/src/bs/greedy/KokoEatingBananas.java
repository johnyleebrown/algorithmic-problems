package bs.greedy;

/**
 * 875
 *
 * ======
 *
 * Task.
 *
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has
 * piles[i] bananas.  The guards have gone and will come back in H hours.
 *
 * Koko can decide her bananas-per-hour eating speed of K.  Each hour, she
 * chooses some pile of bananas, and eats K bananas from that pile.  If the pile
 * has less than K bananas, she eats all of them instead, and won't eat any more
 * bananas during this hour.
 *
 * Koko likes to eat slowly, but still wants to finish eating all the bananas
 * before the guards come back.
 *
 * Return the minimum integer K such that she can eat all the bananas within H
 * hours.
 *
 * ======
 *
 * Source: Leetcode
 */
public class KokoEatingBananas {
	/**
	 * Using regular binary search without equality operation. We know that if
	 * cur <= h - if we spent less hours than needed it means that the speed is
	 * too high and we need to reduce it, so hi = mid - 1, or else.
	 *
	 * Use different version of binary search because we don't discard mid, when
	 * we change hi.
	 */
	public static class Solution {
		public int minEatingSpeed(int[] a, int timeLimit) {
			int lo = 1;
			int hi = 1_000_000_001;
			while (hi - lo >= 0) {
				int speed = lo + (hi - lo) / 2;
				int spentHours = getHoursSpentForSpeed(a, speed);
				// good
				if (spentHours <= timeLimit) {
					hi = speed - 1;
				}
				// bad
				else {
					lo = speed + 1;
				}
			}
			return lo;
		}

		private int getHoursSpentForSpeed(int[] a, int speed) {
			int c = 0;
			for (int value : a) {
				c++;
				if (value != speed) {
					c += value / speed;
				}
			}
			return c;
		}
	}
}