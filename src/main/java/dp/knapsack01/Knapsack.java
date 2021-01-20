package dp.knapsack01;

/**
 * ### **Introduction[#](https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/RM1BDv71V60#introduction)**
 *
 * Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack that
 * has a capacity ‘C’. The goal is to get the maximum profit from the items in the knapsack. Each
 * item can only be selected once, as we don’t have multiple quantities of any item.
 *
 * Let’s take Merry’s example, who wants to carry some fruits in the knapsack to get maximum
 * profit.
 * Here are the weights and profits of the fruits:
 *
 * **Items:**{ Apple, Orange, Banana, Melon }
 *
 * **Weights:**{ 2, 3, 1, 4 }
 *
 * **Profits:**{ 4, 5, 3, 7 }
 *
 * **Knapsack capacity:**5
 *
 * Let’s try to put different combinations of fruits in the knapsack, such that their total weight
 * is not more than 5:
 *
 * Apple + Orange (total weight 5) => 9 profit
 *
 * Apple + Banana (total weight 3) => 7 profit
 *
 * Orange + Banana (total weight 4) => 8 profit
 *
 * Banana + Melon (total weight 5) => 10 profit
 *
 * This shows that**Banana + Melon**is the best combination, as it gives us the maximum profit and
 * the total weight does not exceed the capacity.
 *
 * ### **Problem Statement[#](https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/RM1BDv71V60#problem-statement)**
 *
 * Given two integer arrays to represent weights and profits of ‘N’ items, we need to find a subset
 * of these items which will give us maximum profit such that their cumulative weight is not more
 * than a given number ‘C’. Each item can only be selected once, which means either we put an item
 * in the knapsack or skip it.
 */
public class Knapsack {
	public interface Solution {
		int solveKnapsack(int[] profits, int[] weights, int capacity);
	}

	/**
	 * Brute force
	 * Either we take item or not
	 */
	public static class Solution1 implements Solution {
		public int solveKnapsack(int[] profits, int[] weights, int capacity) {
			return dfs(profits, weights, capacity, 0);
		}

		private int dfs(int[] profits, int[] weights, int capacity, int j) {

			if (j >= profits.length) {
				return 0;
			}

			int ans1 = capacity - weights[j] < 0
			           ? 0
			           : dfs(profits, weights, capacity - weights[j], j + 1) + profits[j];
			int ans2 = dfs(profits, weights, capacity, j + 1);

			return Math.max(ans1, ans2);
		}
	}

	/**
	 * Backtracking
	 */
	public static class Solution2 implements Solution {
		public int solveKnapsack(int[] profits, int[] weights, int capacity) {
			return dfs(profits, weights, capacity, 0, 0);
		}

		private int dfs(int[] profits, int[] weights, int capacity, int w, int j) {

			int ans = Integer.MIN_VALUE;

			for (int i = j; i < profits.length; i++) {
				if (w + weights[i] > capacity) {
					continue;
				}
				int x = dfs(profits, weights, capacity, w + weights[i], i + 1);
				ans = Math.max(ans, x + profits[i]);
			}

			return ans == Integer.MIN_VALUE ? 0 : ans;
		}
	}

	/**
	 * Backtracking + Memo
	 */
	public static class Solution3 implements Solution {
		public int solveKnapsack(int[] profits, int[] weights, int capacity) {
			return dfs(profits, weights, capacity, 0, new Integer[capacity + 1][profits.length + 1]);
		}

		private int dfs(int[] profits, int[] weights, int capacity, int j, Integer[][] dp) {

			if (j >= profits.length) {
				return 0;
			}

			if (dp[capacity][j] != null) {
				return dp[capacity][j];
			}

			int ans = Integer.MIN_VALUE;

			for (int i = j; i < profits.length; i++) {
				if (capacity - weights[i] < 0) {
					continue;
				}
				int x = dfs(profits, weights, capacity - weights[i], i + 1, dp);
				ans = Math.max(ans, x + profits[i]);
			}

			if (ans == Integer.MIN_VALUE)
				ans = 0;
			dp[capacity][j] = ans;
			return ans;
		}
	}

	/**
	 * Improved Backtracking+Memo
	 * In each iteration either we take the item or not
	 */
	public static class Solution4 implements Solution {
		public int solveKnapsack(int[] profits, int[] weights, int capacity) {
			return dfs(profits, weights, capacity, 0, new Integer[capacity + 1][profits.length + 1]);
		}

		private int dfs(int[] profits, int[] weights, int capacity, int j, Integer[][] dp) {
			if (j >= profits.length) {
				return 0;
			}

			if (dp[capacity][j] != null) {
				return dp[capacity][j];
			}

			int ans1 = capacity - weights[j] < 0 ? 0 : dfs(profits, weights, capacity - weights[j],
			j + 1, dp) + profits[j];
			int ans2 = dfs(profits, weights, capacity, j + 1, dp);

			dp[capacity][j] = Math.max(ans1, ans2);
			return dp[capacity][j];
		}
	}

	/**
	 * Bottom-Up, Iterative
	 *
	 * Its clear now what kind of recurrent relation is it, either we take the current item or not.
	 * We have 2 parameters: filledCapacity and currentIndex
	 * Subproblem is: what is the max profit for items[0..currentIndex] for
	 * capacity[1..filledCapacity]
	 */
	public static class Solution5 implements Solution {
		public int solveKnapsack(int[] profits, int[] weights, int capacity) {
			int n = weights.length;
			int[][] dp = new int[n][capacity + 1];

			// prefill - 2 question, one for each of the axis
			// what is the max profit if we can fit weights[0] in cap from 1 to capacity?
			// what is the max profit if we can fit weights[i] from i=0 to i=n-1 into a bag of
			// weight 0?
			// start from 1 because aint nothing fitting in cap=0 duh
			for (int cap = 1; cap <= capacity; cap++) {
				if (weights[0] <= cap) {
					dp[0][cap] = weights[0];
				}
			}

			for (int i = 1; i < n; i++) {
				for (int cap = 1; cap <= capacity; cap++) {
					// either we take the previous result
					dp[i][cap] = dp[i - 1][cap];

					// or we pick the current item
					if (cap - weights[i] >= 0) {
						dp[i][cap] = Math.max(dp[i][cap], dp[i - 1][cap - weights[i]] + profits[i]);
					}
				}
			}

			return dp[n - 1][capacity];
		}
	}
}
