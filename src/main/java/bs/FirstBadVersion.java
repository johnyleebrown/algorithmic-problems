package bs;

/**
 * 278
 */
public class FirstBadVersion {

	public static class Solution {

		public int firstBadVersion(int n) {
			int lo = 0;
			int hi = n == Integer.MAX_VALUE ? n : n + 1;
			while (hi - lo > 1) {
				int mid = lo + (hi - lo) / 2;
				if (!isBadVersion(mid)) {
					lo = mid;
				} else {
					hi = mid;
				}
			}
			return hi;
		}

		private boolean isBadVersion(int mid) {
			return false;
		}
	}
}