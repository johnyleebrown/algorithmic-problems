package string.other;

/**
 * 1427
 *
 * ======
 *
 * Task.
 *
 * You are given a string s containing lowercase English letters, and a matrix
 * shift, where shift[i] = [direction, amount]:
 *
 * direction can be 0 (for left shift) or 1 (for right shift). amount is the
 * amount by which string s is to be shifted. A left shift by 1 means remove the
 * first character of s and append it to the end. Similarly, a right shift by 1
 * means remove the last character of s and add it to the beginning.
 *
 * Return the final string after all operations.
 *
 * ======
 *
 * Source: Leetcode
 */
public class PerformStringShifts {
	/**
	 * Optimized with Math.abs(sh) > n / 2 part.
	 */
	public static class Solution {
		public String stringShift(String s, int[][] ar) {
			int sh = 0;
			int n = s.length();
			for (int i = 0; i < ar.length; i++) {
				if (ar[i][0] == 0) sh -= ar[i][1];
				else sh += ar[i][1];
			}
			sh %= n;
			if (sh == 0) {
				return s;
			}
			if (Math.abs(sh) > n / 2) {
				sh = sh > 0 ? -(n - sh) : n + sh;
			}
			char[] ch = new char[n];
			if (sh > 0) {
				for (int i = 0; i < sh; i++) {
					ch[i] = s.charAt(n - sh + i);
				}
				for (int i = sh, k = 0; i < n; i++, k++) {
					ch[i] = s.charAt(k);
				}
			} else {
				sh *= -1;
				for (int i = 0; i < n - sh; i++) {
					ch[i] = s.charAt(sh + i);
				}
				for (int i = n - sh, k = 0; i < n; i++, k++) {
					ch[i] = s.charAt(k);
				}
			}
			return String.valueOf(ch);
		}
	}
}