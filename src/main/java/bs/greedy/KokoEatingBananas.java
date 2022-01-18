package bs.greedy;

/**
 * 875
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