package backtracking;

/**
 * 2151
 */
public class MaximumGoodPeopleBasedOnStatements {

	/**
	 * 70/82
	 */
	public static class Solution {

		int ans = 0, temp = 0;

		public int maximumGood(int[][] st) {
			for (int i = 0; i < st.length; i++) {
				Boolean[] x = new Boolean[st.length];
				x[i] = true;
				boolean[] seen = new boolean[st.length];
				seen[i] = true;
				// System.out.println("===== i="+i);
				if (dfs(i, seen, st, x, 1)) {
					ans = Math.max(ans, temp);
				}
				temp = 0;
			}
			return ans;
		}

		private boolean dfs(int i, boolean[] seen,
				int[][] st, Boolean[] x, int c) {
			for (int j = 0; j < st.length; j++) {
				if (i == j) continue;
				if (st[i][j] == 2) continue;
				boolean good = st[i][j] == 1;
				if (seen[j]) {
					if (x[j] != good) {
						return false;
					}
				} else {
					seen[j] = true;
					x[j] = good;
					if (good) {
						boolean res = dfs(j, seen, st, x, c + 1);
						if (!res) return res;
					}
				}
			}
			temp = Math.max(temp, c);
			return true;
		}
	}
}
