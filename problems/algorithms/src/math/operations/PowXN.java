package math.operations;

/**
 * 50
 */
public class PowXN {
	public static class Solution {
		public double myPow(double x, int n) {
			if (n == 0) return 1;
			if (x == 1) return 1;
			if (x == -1) {
				if (n % 2 == 0) return 1;
				else return -1;
			}
			if (n == Integer.MIN_VALUE) return 0;
			if (n < 0) {
				n = -n;
				x = 1 / x;
			}
			double ret = 1.0;
			while (n > 0) {
				if ((n & 1) != 0)
					ret *= x;
				x = x * x;
				n = n >> 1;
			}
			return ret;
		}
	}
}
