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

	/**
	 * new version - dfs 2
	 */
	public static class Solution2 {

		private int ans = 0, temp = 0;

		public int maximumGood(int[][] s) {
			int n = s.length;
			for (int i = 0; i < n; i++) {
				Boolean[] good = new Boolean[n];
				good[i] = true;
				// check 1s
				boolean res = dfs1(s, i, good, 1);
				if (!res) {
					continue;
				}
				// check 2s
				// for (int j = 0; j < n; j++) {
				//     if (good[j] == null) {
				//         good[j] = true;
				//         boolean x = dfs2(s, j, good, temp);
				//         if (!x) {
				//             good[j] = false;
				//         }
				//     }
				// }
				ans = Math.max(ans, temp);
				temp = 0;
			}
			return ans;
		}

		boolean dfs1(int[][] s, int i, Boolean[] good, int count) {
			for (int j = 0; j < s.length; j++) {
				if (s[i][j] == 2) continue;
				boolean guess = s[i][j] == 1;
				// go back to start - bad guess
				if (good[j] != null && good[j] != guess) {
					return false;
				}
				if (good[j] == null) {
					// guess j is true
					// go into 1
					if (guess) {
						good[j] = true;
						boolean ans = dfs1(s, j, good, count + 1);
						// bad guess
						if (!ans) {
							return ans;
						}
					}
					// guess j is false
					else {
						good[j] = false;
					}
				}
			}
			temp = Math.max(temp, count);
			return true;
		}

		//     boolean dfs2(int[][] s, int i, Boolean[] good, int count, boolean[] seen) {
		//         if () {

		//         } else {
		//             for (int j = 0; j < s.length; j++) {
		//                 if (s[i][j] == 2) continue;
		//                 boolean guess = s[i][j] == 1;
		//                 if (good[j] != null && good[j] != guess) {
		//                     return false;
		//                 }
		//                 if (guess && !seen[j]) {
		//                     seen[j] = true;
		//                     good[j] = true;
		//                     boolean ans = dfs1(s, j, good, count + 1, seen);
		//                     if (!ans) {
		//                         good[j] = false;
		//                         return ans;
		//                     }
		//                 }
		//             }
		//             temp = Math.max(temp, count);
		//             return true;
		//         }
		//     }
	}
}
