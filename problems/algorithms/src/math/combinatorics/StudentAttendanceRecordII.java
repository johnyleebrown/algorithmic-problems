package math.combinatorics;

import util.tester.Tester;

import java.util.Arrays;

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
 * https://codeforces.com/blog/entry/6221, https://algoprog.ru/material/module-17576
 *
 * ======
 *
 * Similar
 *
 * https://lectoriy.mipt.ru/lecture/CompTech-Informat-L03-Derbysh-141010.01#00:37:33
 *
 * ======
 *
 * Source: Leetcode
 */
public class StudentAttendanceRecordII {
	/**
	 * TODO finish math solution.
	 * https://leetcode.com/problems/student-attendance-record-ii/discuss/234026/Share-my-thought-of-combing-o(1)-space
	 */
	public static class Solution2 {
		private static final int MOD = 1_000_000_007;

		public int checkRecord(int n) {
			// total
			long t = (long) Math.pow(3, n);
			// count l's
			long l = 0;
			for (int k = n - 3; k <= n - 1; k++) {
				long pow = (1 << (n - k - 1));
				l += (n - k) * pow;
			}
			long[] fact = new long[n + 1];
			fact[0] = 1;
			for (int i = 1; i <= n; i++) {
				fact[i] = i * fact[i - 1];
			}
			// count a's
			long a = 0;
			for (int k = 2; k <= n; k++) {
				a += (fact[n] / fact[k] / fact[n - k]) * (1 << (n - k));
			}
			return (int) (t - l - a) % MOD;
		}

		public Solution2() {
		}
	}

	/**
	 * SF backtracking solution.
	 */
	public static class Solution1 {
		private long ans;
		private int n;
		private char[] b = new char[]{'A', 'L', 'P'};

		public int checkRecord(int n) {
			this.n = n;
			gen(0, new char[n], 0);
			int mod = 1_000_000_007;
			return (int) (ans % mod);
		}

		private void gen(int ind, char[] ca, int ac) {
			if (ind == n) {
				ans++;
			}
			else {
				for (int i = 0; i < 3; i++) {
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

	/**
	 * Backtracking -> dfs.
	 */
	public class Solution1_2 {
		private long ans;
		private int n;
		private char[] b = new char[]{'A', 'L', 'P'};

		public int checkRecord(int n) {
			this.n = n;
			long res = gen(0, new char[n], 0, 0);
			int mod = 1_000_000_007;
			return (int) (res % mod);
		}

		private long gen(int ind, char[] ca, int ac, int l) {
			if (l == 3) {
				return 0;
			}
			if (ac == 2) {
				return 0;
			}
			if (ind == n) {
				return 1;
			}

			long res = 0;
			for (int i = 0; i < 3; i++) {
				ca[ind] = b[i];
				long x = gen(ind + 1, ca, ca(i,ac), cl(ind,i,ca));
				res += x;
				ca[ind] = 0;
			}

			return res;
		}

		private int ca(int i, int ac) {
			return b[i] == 'A' ? ac + 1 : ac;
		}

		private int cl(int ind, int i, char[] ca) {
			if (b[i] == 'L') {
				if (ind > 0 && ca[ind - 1] == 'L') {
					if (ind > 1 && ca[ind - 2] == 'L') {
						return 3;
					}
					return 2;
				}
				return 1;
			}
			return 0;
		}
	}

	/**
	 * Backtracking -> dfs -> dfs_with_memo.
	 */
	public class Solution1_3 {
		private long ans;
		private int n;
		private char[] b = new char[]{'A', 'L', 'P'};
		int mod = 1_000_000_007;

		public int checkRecord(int n) {
			this.n = n;
			Long[][][] dp = new Long[n+1][2][3];// ind,ac,l
			long res = gen(0, new char[n], 0, 0,dp);
			// System.out.println(res % mod);
			return (int) (res % mod);
		}

		private long gen(int ind, char[] ca, int ac, int l,Long[][][] dp) {
			if (l == 3) {
				return 0;
			}
			if (ac == 2) {
				return 0;
			}
			if (ind == n) {
				return 1;
			}
			if (dp[ind][ac][l]!=null) {
				return dp[ind][ac][l];
			}

			long res = 0;
			for (int i = 0; i < 3; i++) {
				ca[ind] = b[i];
				long x = gen(ind + 1, ca, ca(i,ac), cl(ind,i,ca),dp);
				res += x;
				ca[ind] = 0;
			}

			dp[ind][ac][l]=res % mod;

			return res;
		}

		private int ca(int i, int ac) {
			return b[i] == 'A' ? ac + 1 : ac;
		}

		private int cl(int ind, int i, char[] ca) {
			if (b[i] == 'L') {
				if (ind > 0 && ca[ind - 1] == 'L') {
					if (ind > 1 && ca[ind - 2] == 'L') {
						return 3;
					}
					return 2;
				}
				return 1;
			}
			return 0;
		}
	}

	/**
	 * Had wrong trans table.
	 -----+---------------
	 INIT | A -- L -- P --
	 -----+---------------
	 A0L0 | A1L0 A0L1 A0L0
	 A0L1 | A1L0 A0L2 A0L0
	 A0L2 | A1L0 Done A0L0
	 A1L0 | Done A1L1 A1L0
	 A1L1 | Done A1L2 A1L0
	 A1L2 | Done Done A1L0
	 */
	public static class Solution3 {
		public int checkRecord(int n) {
			if (n == 1) {
				return 3;
			}
			//keep the last 2 answers,1st letter,2nd letter
			//a-0,l-1,p-2
			long[][][] dp = new long[2][3][3];
			//base
			for (int i = 0; i < 3; i++) {
				Arrays.fill(dp[0][i], 1);
			}
			dp[0][0][0] = 0;
			int k = 1;
			for (int i = 3; i <= n; i++, k = 1 - k) {
				dp[k][2][2] = dp[1 - k][2][2] + dp[1 - k][0][2] + dp[1 - k][1][2];//PP
				dp[k][2][0] = dp[1 - k][2][2] + dp[1 - k][1][2];//PA
				dp[k][2][1] = dp[1 - k][2][2] + dp[1 - k][1][2] + dp[1 - k][0][2];//PL
				dp[k][0][2] = dp[1 - k][2][0] + dp[1 - k][1][0];//AP
				dp[k][1][2] = dp[1 - k][2][1] + dp[1 - k][1][1] + dp[1 - k][0][1];//LP
				dp[k][1][1] = dp[1 - k][2][1] + dp[1 - k][0][1];//LL
				dp[k][1][0] = dp[1 - k][1][1] + dp[1 - k][2][1];//LA
				dp[k][0][1] = dp[1 - k][1][0] + dp[1 - k][2][0];//AL
			}
			long ans = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					ans += dp[1 - k][i][j];
				}
			}
			int MOD = 1_000_000_007;
			return (int) ans % MOD;
		}
	}

	public static void main(String[] args) {
		new Tester(new Solution2())
//				.add(1).expect(3)
//				.add(2).expect(8)
//				.add(3).expect(19)
//				.add(4).expect(43)
				.add(5).expect(94)
				.run();
	}
}