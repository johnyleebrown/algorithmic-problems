package math.combinatorics;

import util.tester.Tester;

import java.math.BigInteger;

/**
 * 552
 *
 * ======
 *
 * Task.
 *
 * Given a positive integer n, return the number of all possible attendance
 * records with length n, which will be regarded as rewardable. The answer may
 * be very large, return it after mod 109 + 7.
 *
 * A student attendance record is a string that only contains the following
 * three characters:
 *
 * 'A' : Absent. 'L' : Late. 'P' : Present.
 *
 * A record is regarded as rewardable if it doesn't contain more than one 'A'
 * (absent) or more than two continuous 'L' (late).
 *
 * https://codeforces.com/blog/entry/6221
 * https://algoprog.ru/material/module-17576
 *
 * ======
 *
 * Source: Leetcode
 */
public class StudentAttendanceRecordII
{
	/**
	 * TODO
	 * Math solution.
	 */
	public static class Solution2
	{
		private static final int MOD = 1_000_000_007;

		public int checkRecord(int n)
		{
			// total
			long t = (long) Math.pow(3, n);
			System.out.println("t: " + t);
			// count l's
			long l = 0;
			for (int k = n - 3; k <= n - 1; k++)
			{
				long pow = (1 << (n - k - 1));
				l += (n - k) * pow;
				BigInteger b = new BigInteger(String.valueOf(0));
				System.out.println(b.intValue());
			}
			System.out.println("l: " + l);
			long[] fact = new long[n + 1];
			fact[0] = 1;
			for (int i = 1; i <= n; i++)
			{
				fact[i] = i * fact[i - 1];
			}
			// count a's
			long a = 0;
			for (int k = 2; k <= n; k++)
			{
				a += (fact[n] / fact[k] / fact[n - k]) * (1 << (n - k));
			}
			System.out.println("a: " + a);
			return (int) (t - l - a) % MOD;
		}

		public Solution2()
		{
		}
	}

	/**
	 * SF backtracking solution.
	 */
	public static class Solution1
	{
		private long ans;
		private int n;
		private char[] b = new char[]{'A', 'L', 'P'};

		public int checkRecord(int n)
		{
			this.n = n;
			gen(0, new char[n], 0);
			int mod = 1_000_000_007;
			return (int) (ans % mod);
		}

		private void gen(int ind, char[] ca, int ac)
		{
			if (ind == n)
			{
				ans++;
			}
			else
			{
				for (int i = 0; i < 3; i++)
				{
					if (ind > 1 && ca[ind - 1] == 'L' && ca[ind - 2] == 'L' && b[i] == 'L')
						continue;
					if (b[i] == 'A' && ac == 1)
						continue;
					ca[ind] = b[i];
					gen(ind + 1, ca, b[i] == 'A' ? ac + 1 : ac);
					ca[ind] = 0;
				}
			}
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution2())
//				.add(1).expect(3)
//				.add(2).expect(8)
//				.add(3).expect(19)
//				.add(4).expect(43)
				.add(5).expect(94)
				.run();
	}
}