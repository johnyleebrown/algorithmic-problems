package bs.regular;

/**
 * 69
 */
public class Sqrt {
	class Solution {
		public int mySqrt(int x) {
			if (x == 0) {
				return 0;
			}
			long lo = 1;
			long hi = x;
			while (lo <= hi) {
				long mid = lo + (hi - lo) / 2;
				long val = mid * mid;
				if (val == x) {
					return (int) mid;
				}
				else if (val > x) {
					hi = mid - 1;
				}
				else {
					lo = mid + 1;
				}
			}
			return (int) hi;
		}
	}
}
