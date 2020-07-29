package graph.dfs;

/**
 * 547
 *
 * ======
 *
 * Task.
 *
 * There are N students in a class. Some of them are friends, while some are not. Their friendship
 * is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of
 * C, then A is an indirect friend of C. And we defined a friend circle is a group of students who
 * are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class. If
 * M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And
 * you have to output the total number of friend circles among all the students.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FriendCircles {
	/**
	 * Calc cc.
	 */
	public static class Solution {
		public int findCircleNum(int[][] ar) {
			int n = ar.length;
			boolean[] seen = new boolean[n];
			int ans = 0;
			for (int i = 0; i < n; i++) {
				if (!seen[i]) {
					dfs(i, ar, seen);
					ans++;
				}
			}
			return ans;
		}

		private void dfs(int v, int[][] ar, boolean[] seen) {
			seen[v] = true;
			for (int w = 0; w < ar.length; w++) {
				if (w == v) continue;
				if (seen[w]) continue;
				if (ar[v][w] == 1) {
					dfs(w, ar, seen);
				}
			}
		}
	}
}