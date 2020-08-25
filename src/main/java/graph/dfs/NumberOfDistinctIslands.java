package graph.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * 694
 */
public class NumberOfDistinctIslands {
	/**
	 * Create a path of dfs for each island with string builder, mark end of recursion as well.
	 */
	public static class Solution {
		private static final int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
		private int n, m;

		public int numDistinctIslands(int[][] ar) {
			n = ar.length;
			if (n == 0) return 0;
			m = ar[0].length;
			if (m == 0) return 0;
			Set<String> set = new HashSet<>();
			boolean[][] seen = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					StringBuilder sb = new StringBuilder();
					dfs(i, j, ar, sb, 4, seen); // start
					if (sb.length() > 0) set.add(sb.toString());
				}
			}
			return set.size();
		}

		private void dfs(int i, int j, int[][] ar, StringBuilder sb, int curDir, boolean[][] seen) {
			if (!isValid(i, j) || seen[i][j] || ar[i][j] != 1) {
				return;
			}
			sb.append(curDir);
			seen[i][j] = true;
			for (int dir = 0; dir < 4; dir++) {
				dfs(i + dirs[dir][0], j + dirs[dir][1], ar, sb, dir, seen);
			}
			sb.append(5); // end
		}

		private boolean isValid(int i, int j) {
			return i >= 0 && j >= 0 && i < n && j < m;
		}
	}
}