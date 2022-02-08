package tree.validate;

/**
 * 1361
 */
public class ValidateBinaryTreeNodes {

	/**
	 * SF. Tree traversal.
	 */
	public static class Solution {

		int n;

		public boolean validateBinaryTreeNodes(int n, int[] l, int[] r) {
			this.n = n;
			int i = 0;
			for (; i < n; i++) {
				if (l[i] == 0 || r[i] == 0)
					break;
			}
			return dfs(i == n ? 0 : i, l, r, new boolean[n]) && this.n == 0;
		}

		private boolean dfs(int cur, int[] l, int[] r, boolean[] seen) {
			if (cur == -1)
				return true;
			if (seen[cur])
				return false;
			n--;
			seen[cur] = true;
			boolean left = dfs(l[cur], l, r, seen);
			boolean right = dfs(r[cur], l, r, seen);
			return left && right;
		}
	}
}
