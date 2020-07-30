package bs.regular.numbers;

/**
 * 69
 */
public class Sqrt {
	/**
	 * Let's think of it this way:
	 * numbers | 123456789
	 * sqrt's  | 111222223
	 * power   | 111444449
	 * so, we want to take the max on the left side, hence lo
	 * good   | bad
	 * 1114(4)|4449
	 */
	public static class Solution {
		public int mySqrt(int x) {
			if (x == 1) return 1;
			long lo = 0;
			long hi = x / 2 + 1;

			while (hi - lo > 1) {
				long mid = lo + (hi - lo) / 2;
				long xx = mid * mid;
				if (xx <= x) {
					lo = mid;
				} else {
					hi = mid;
				}
			}
			return (int) lo;
		}
	}
}