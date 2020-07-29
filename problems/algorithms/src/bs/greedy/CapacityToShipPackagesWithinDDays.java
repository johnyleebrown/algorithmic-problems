package bs.greedy;

/**
 * 1011
 *
 * ======
 *
 * Task.
 *
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 *
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship
 * with packages on the conveyor belt (in the order given by weights). We may not load more weight
 * than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor
 * belt being shipped within D days.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CapacityToShipPackagesWithinDDays {
	/**
	 * Try different ship's capacities using binary search.
	 */
	public static class Solution {
		public int shipWithinDays(int[] ar, int maxDays) {

			// get lo and hi
			int lo = 1;
			int sum = 0;
			for (int s : ar) {
				lo = Math.max(lo, s);
				sum += s;
			}
			int hi = sum;

			while (hi - lo >= 0) {
				int cap = lo + (hi - lo) / 2;

				// how many days it will take us to transfer all the cargo
				// with the ship's capacity cap
				int days = countDaysForCap(cap, ar);

				// good - can afford to reduce cap
				if (days <= maxDays) {
					hi = cap - 1;
				}
				// bad - need a bigger cap
				else {
					lo = cap + 1;
				}
			}

			return lo;
		}

		private int countDaysForCap(int cap, int[] ar) {
			int days = 1;
			int curLoad = 0;
			for (int w : ar) {
				curLoad += w;
				if (curLoad > cap) {
					days++;
					curLoad = w;
				}
			}
			return days;
		}
	}
}
