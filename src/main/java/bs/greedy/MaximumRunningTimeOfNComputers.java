package bs.greedy;

/**
 * 2141
 */
public class MaximumRunningTimeOfNComputers {

	/**
	 * Need to return the maximum number of minutes you can run all the n computers
	 * simultaneously.
	 * We are trying to guess the number with bs and check it adding battery capacities.
	 */
	public static class Solution {

		public long maxRunTime(int n, int[] bs) {
			int min = Integer.MAX_VALUE;
			long sum = 0;

			for (int b : bs) {
				min = Math.min(min, b);
				sum += b;
			}

			return bs(min, sum, n, bs);
		}

		/**
		 * Range binary search, where we need to find the last good element in array, which
		 * is maximum minutes. The first good would be zero, the last good would be lo in this
		 * case.
		 */
		private long bs(int min, long sum, int n, int[] bs) {
			long lo = min - 1, hi = sum / n + 1;
			while (hi - lo > 1) {
				long mid = lo + (hi - lo) / 2;
				if (isGood(mid, n, bs)) {
					lo = mid;
				} else {
					hi = mid;
				}
			}
			return lo;
		}

		/**
		 * Other version of binary search - regular. We take the last good mid.
		 */
		private long bs2(int min, long sum, int n, int[] bs) {
			long res = -1;
			long lo = min, hi = sum / n;
			while (hi - lo >= 0) {
				long mid = lo + (hi - lo) / 2;
				if (isGood(mid, n, bs)) {
					res = mid;
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
			return res;
		}

		private boolean isGood(long k, int n, int[] bs) {
			long total = k * n, sum = 0;
			for (int b : bs) {
				if (b > k) {
					sum += k;
				} else {
					sum += b;
				}
				if (sum >= total) {
					return true;
				}
			}
			return sum >= total;
		}
	}
}
