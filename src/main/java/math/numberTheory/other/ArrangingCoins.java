package math.numberTheory.other;

/**
 * 441
 */
public class ArrangingCoins {

	/**
	 * We want to count the sum of arithmetic progression
	 * 1+2+3+...+x = n - we want to find x
	 * because it corresponds to the level
	 * the sum = (a1 + an) * n / 2
	 * in our case s = (1 + x) * x / 2
	 * as the result we have x = -0.5 + sqrt(2n+0.25)
	 */
	public static class Solution1 {

		public int arrangeCoins(int n) {
			return (int) Math.floor(-0.5 + Math.sqrt((double) 2 * n + 0.25));
		}
	}

	public static class Solution2 {

		public int arrangeCoins(int n) {
			if (n == 0) return 0;
			if (n == 1) return 1;
			int ans = -1;
			int sum = 0;
			int i = 1;
			while (sum <= n) {
				ans++;
				sum += i++;
			}
			return ans;
		}
	}
}