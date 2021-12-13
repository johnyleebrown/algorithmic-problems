package math.operations;

/**
 * 50
 */
public class PowXN {

	interface Solution {

		public double myPow(double x, int n);
	}

	/**
	 * Using binary pow.
	 * http://e-maxx.ru/algo/binary_pow
	 *
	 * Use long.
	 */
	public static class Solution1 implements Solution {

		public double myPow(double x, int n) {
			return helper(x, (long) n);
		}

		private double helper(double x, long n) {
			if (n < 0) return helper(1 / x, -n);
			if (n == 0) return 1;
			if (n == 1) return x;
			double y = helper(x, n / 2);
			if (n % 2 == 0) return y * y;
				// or y*x and y = helper(x,n-1);
			else return y * y * x;
		}
	}

	/**
	 * No long.
	 */
	public static class Solution2 implements Solution {

		public double myPow(double x, int n) {
			if (n == Integer.MIN_VALUE) n = -Integer.MAX_VALUE;
			if (x < 0 && n < 0) return myPow(-x, -n);
			if (n < 0) return myPow(1 / x, -n);
			if (n == 0) return 1;
			if (n == 1) return x;
			double y = myPow(x, n / 2);
			if (n % 2 == 0) return y * y;
			else return y * y * x;
		}
	}

	/**
	 * Iterative.
	 *
	 * For example, lets say N = 9 = 2^3 + 2^0 = 1001 in binary.
	 * Then: x^9 = x^(2^3) * x^(2^0)
	 *
	 * We can see that every time we encounter a 1 in the binary representation of N, we
	 * need to multiply the answer with x^(2^i) where i is the ith bit of the exponent.
	 * Thus, we can keep a running total of repeatedly squaring x - (x, x^2, x^4, x^8, etc)
	 * and multiply it by the answer when we see a 1.
	 */
	public static class Solution3 implements Solution {

		public double myPow(double x, int n) {
			if (n == 0) return 1;
			if (n == 1) return x;
			long N = n;
			if (N < 0) {
				x = 1 / x;
				N = -N;
			}
			double ans = 1;
			while (N > 0) {
				if (N % 2 != 0) {
					//mult on set bits
					ans *= x;
					N--;
				} else {
					x *= x;
					N /= 2;
				}
			}
			return ans;
		}
	}
}
