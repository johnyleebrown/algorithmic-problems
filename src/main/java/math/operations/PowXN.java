package math.operations;

/**
 * 50
 */
public class PowXN {
	/**
	 * Using binary pow.
	 * http://e-maxx.ru/algo/binary_pow
	 */
	public static class Solution {
		public double myPow(double x, int n) {
			if (n == 0) {
				return 1;
			}
			if (n == 1) {
				return x;
			}
			if (n == -1) {
				return 1 / x;
			}
			if ((n & 1) == 0) {
				double half = myPow(x, n / 2);
				return half * half;
			} else {
				return myPow(x, n - 1) * x;
			}
		}
	}
}
