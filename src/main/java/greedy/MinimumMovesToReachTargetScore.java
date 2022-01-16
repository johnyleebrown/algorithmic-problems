package greedy;

/**
 * 2139
 */
public class MinimumMovesToReachTargetScore {

	/**
	 * Go backwards grabbing big by dividing and adding nu,ber of times we need to increment
	 * (divider).
	 */
	public static class Solution {

		public int minMoves(int t, int md) {
			int ans = 0;
			while (--md >= 0 && t > 1) {
				ans += 1 + t % 2;
				t /= 2;
			}
			return ans + t - 1;
		}
	}
}
