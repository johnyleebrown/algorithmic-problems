package dp;

import util.tester.Tester;

/**
 * 1388
 *
 * ======
 *
 * Task.
 *
 * There is a pizza with 3n slices of varying size, you and your friends will
 * take slices of pizza as follows:
 *
 * You will pick any pizza slice. Your friend Alice will pick next slice in anti
 * clockwise direction of your pick. Your friend Bob will pick next slice in
 * clockwise direction of your pick. Repeat until there are no more slices of
 * pizzas.
 *
 * Sizes of Pizza slices is represented by circular array slices in clockwise
 * direction.
 *
 * Return the maximum possible sum of slice sizes which you can have.
 *
 * ======
 *
 * Similar: $INSERT_SIMILAR.
 *
 * ======
 *
 * Source: Leetcode
 */
public class PizzaWith3nSlices
{
	/**
	 * The idea here is to remove what was picked.
	 */
	public static class Solution {
		int[] dp;
		int n;
		public int maxSizeSlices(int[] a) {
			n = a.length;
			dp = new int[n + 1];

			for (int i = 0; i <= n; i++) {
				dp[i]=;
			}

			int res = -1;
			// "you will pick any slice"
			for (int i = 0; i < n; i++) {
				dp[] = ;
				res = Math.max(res, );
			}
			return res;
		}
		int dfs(int cur, int[] a){
			if (dp[] != 0) {
				return dp[];
			}
		
			return dp[];
		}
		int g(int a, int b){
			int x = a + b;
			if (x == n + 2) {
				return 1;
			} else if (x == n + 1) {
				return 0;
			} else if (x == -1) {
				return n - 1;
			} else if (x == -2) {
				return n - 2;
			}
			return x;
		}
	}

	public static void main(String[] args) {
		new Tester(new Solution())
				.add(new int[]{1,2,3,4,5,6}).expect(10)
				.run();
	}
}
