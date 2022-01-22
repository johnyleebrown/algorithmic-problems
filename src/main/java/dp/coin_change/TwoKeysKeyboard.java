package dp.coin_change;

import java.util.HashMap;
import java.util.Map;

/**
 * 650
 */
public class TwoKeysKeyboard {

	/**
	 * DFS. Either we increment cur or change buffer. If we just changed buffer we don't
	 * change it again so there is no overflow.
	 */
	public static class Solution1 {

		public int minSteps(int n) {
			if (n == 1) return 0;
			return 1 + dfs(n, 1, 1, true);
		}

		int dfs(int target, int cur, int buffer, boolean justCopied) {
			if (cur > target) return 100_000;
			if (cur == target) return 0;
			int ans = 100_000;
			ans = Math.min(ans, dfs(target, cur + buffer, buffer, false));
			if (!justCopied) ans = Math.min(ans, dfs(target, cur, cur, true));
			return ans + 1;
		}
	}

	/**
	 * Added hash, but running time decreased smh.
	 */
	public static class Solution2 {

		Map<Integer, Integer> dp = new HashMap<>();

		public int minSteps(int n) {
			if (n == 1) return 0;
			return 1 + dfs(n, 1, 1, true);
		}

		int dfs(int target, int cur, int buffer, boolean justCopied) {
			if (cur > target) return 100_000;
			if (cur == target) return 0;
			int h = h(cur, buffer);
			if (dp.get(h) != null) {
				// System.out.println("a");
				return dp.get(h);
			}
			int ans = 100_000;
			ans = Math.min(ans, dfs(target, cur + buffer, buffer, false));
			if (!justCopied) ans = Math.min(ans, dfs(target, cur, cur, true));
			dp.put(h, ans + 1);
			return ans + 1;
		}

		int h(int c, int b) {
			return c + b * 10_000;
		}
	}
}
