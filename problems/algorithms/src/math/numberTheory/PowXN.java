package math.numberTheory;

import util.test.Tester;

/**
 * 50
 */
public class PowXN
{
	private static class Solution
	{
		public double myPow(double x, int n)
		{
			if (n == 0) return 1;
			double half = myPow(x, n / 2);
			if (Math.abs(n) % 2 == 1)
				return n < 0 ? 1 / x * half * half : x * half * half;
			return half * half;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(2.00000, 10).expect(1024.0)
				.add(0.00001, 2147483647).expect(0.0)
				.run();
	}
}
