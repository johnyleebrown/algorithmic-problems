package dp.subset;

/**
 * 2140
 */
public class SolvingQuestionsWithBrainpower {

	/**
	 * Either use current or not and jump q[i][1] steps.
	 */
	public static class Solution {

		long[] map;

		public long mostPoints(int[][] q) {
			map = new long[q.length + 1]; return dfs(0, q);
		}

		long dfs(int i, int[][] q) {
			if (i >= q.length) return 0; if (map[i] != 0) {
				return map[i];
			} long res = 0; res = Math.max(res, q[i][0] + dfs(i + q[i][1] + 1, q));
			res = Math.max(res, dfs(i + 1, q)); map[i] = res; return res;
		}
	}
}
