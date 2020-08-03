package backtracking.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 22
 */
public class GenerateParentheses {
	/**
	 * Time O(2^2n)
	 */
	private static class Solution {
		List<String> ans;
		int n, m;

		public List<String> generateParenthesis(int n) {
			this.n = n;
			m = n * 2;
			ans = new ArrayList<>();
			if (n == 0) return ans;
			gen(0, new char[m], 0, 0);
			return ans;
		}

		private void gen(int ind, char[] cur, int l, int r) {
			if (ind == m) {
				ans.add(String.valueOf(cur));
			} else {
				if (l + 1 <= n) {
					cur[ind] = '(';
					gen(ind + 1, cur, l + 1, r);
				}
				if (r + 1 <= l) {
					cur[ind] = ')';
					gen(ind + 1, cur, l, r + 1);
				}
			}
		}
	}
}