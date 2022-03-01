package backtracking.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 131
 */
public class PalindromePartitioning {

	/**
	 * Check different lengths starting from L to R.
	 */
	static class Solution {

		List<List<String>> ans = new ArrayList<>();

		public List<List<String>> partition(String s) {
			gen(0, s.toCharArray(), new ArrayList<>());
			return ans;
		}

		private void gen(int i, char[] ar, List<String> cur) {
			if (i >= ar.length) {
				ans.add(new ArrayList<>(cur));
			} else {
				for (int k = 1; k <= ar.length - i; k++) {
					if (!isPali(ar, i, i + k - 1)) continue;
					cur.add(String.valueOf(ar, i, k));
					gen(i + k, ar, cur);
					cur.remove(cur.size() - 1);
				}
			}
		}

		private boolean isPali(char[] ar, int i, int j) {
			while (i < j) {
				if (ar[i++] != ar[j--]) return false;
			}
			return true;
		}
	}
}