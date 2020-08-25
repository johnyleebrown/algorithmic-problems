package bs.greedy;

/**
 * 774
 *
 * ======
 *
 * Task.
 *
 * On a horizontal number line, we have gas stations at positions stations[0],
 * stations[1], ..., stations[N-1], where N = stations.length.
 *
 * Now, we add K more gas stations so that D, the maximum distance between
 * adjacent gas stations, is minimized.
 *
 * Return the smallest possible value of D.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MinimizeMaxDistanceToGasStation {
	/**
	 * We operate with number of gas stations. Good condition - we put >= k gas
	 * stations it means that dist is too low => need to increase it, lo = mid +
	 * 1.
	 */
	public static class Solution {
		public double minmaxGasDist(int[] a, int k) {
			double eps = 1e-6;
			double lo = 0;
			double hi = 1e8;

			while (hi - lo > eps) {
				double mid = lo + (hi - lo) / 2;
				int countStations = countStationsForMaxDist(a, mid);

				// dist < x
				if (countStations <= k) {
					hi = mid;
				}

				// dist >= x
				else {
					lo = mid;
				}
			}

			return lo;
		}

		/**
		 * Count of stations we can insert.
		 */
		private int countStationsForMaxDist(int[] a, double maxDist) {
			int count = 0;
			for (int i = 0; i < a.length - 1; i++) {
				count += Math.ceil((a[i + 1] - a[i]) / maxDist) - 1;
			}
			return count;
		}
	}
}