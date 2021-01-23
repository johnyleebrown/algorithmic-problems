package dp.knapsack01;

/**
 * Given a set of positive numbers, determine if there exists a subset whose sum is equal to a
 * given number ‘S’.
 */
public class SubsetSum {
	interface Solution {
		boolean canPartition(int[] num, int sum);
	}

	/**
	 * Iterative
	 *
	 * Subproblem - is it true of false that [0,i] has a subset which sum is equal to sum
	 * Recurrence - either we take the number into the subset and see if we can form a subset for
	 * a remaining sum or we dont take the number
	 * Parameters - index and sum => 2d array
	 */
	public static class Solution1 implements Solution {
		public boolean canPartition(int[] ar, int sum) {
			int n = ar.length;
			boolean[][] dp = new boolean[n][sum + 1];
			for (int i = 0; i < n; i++)
				dp[i][0] = true;
			for (int i = 1; i < sum + 1; i++) {
				dp[0][i] = ar[0] == i;
			}
			for (int i = 1; i < n; i++) {
				for (int j = 1; j <= sum; j++) {
					if (j - ar[i] >= 0) {
						dp[i][j] = dp[i - 1][j - ar[i]] || dp[i - 1][j];
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
			return dp[n - 1][sum];
		}
	}

	/**
	 * Recursive + Memo
	 */
	public static class Solution2 implements Solution {
		public boolean canPartition(int[] ar, int sum) {
			return dfs(0, sum, new Boolean[ar.length + 1][sum + 1], ar);
		}

		private boolean dfs(int i, int sum, Boolean[][] dp, int[] ar) {
			if (sum == 0) {
				return true;
			}
			if (i == ar.length) {
				return false;
			}
			if (dp[i][sum] != null) {
				return dp[i][sum];
			}

			if (sum - ar[i] >= 0) {
				dp[i][sum] = dfs(i + 1, sum - ar[i], dp, ar) || dfs(i + 1, sum, dp, ar);
			} else {
				dp[i][sum] = dfs(i + 1, sum, dp, ar);
			}

			return dp[i][sum];
		}
	}

	public static class Solution3 implements Solution {
		public boolean canPartition(int[] num, int sum) {

			boolean[][] dp = new boolean[num.length][sum + 1];

			// populate the sum=0 columns, as we can always form '0' sum with an empty set
			for (int i = 0; i < num.length; i++)
				dp[i][0] = true;

			// with only one number, we can form a subset only when the required sum is
			// equal to its value
			for (int s = 1; s <= sum; s++) {
				dp[0][s] = num[0] == s;
			}

			// process all subsets for all sums
			for (int i = 1; i < num.length; i++) {
				for (int s = 1; s <= sum; s++) {
					// if we can get the sum 's' without the number at index 'i'
					if (dp[i - 1][s]) {
						dp[i][s] = dp[i - 1][s];
					} else if (s >= num[i]) {
						// else include the number and see if we can find a subset to get the remaining
						// sum
						dp[i][s] = dp[i - 1][s - num[i]];
					}
				}
			}

			// the bottom-right corner will have our answer.
			return dp[num.length - 1][sum];
		}
	}
}