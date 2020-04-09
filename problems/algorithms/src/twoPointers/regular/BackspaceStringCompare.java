package twoPointers.regular;

/**
 * 844
 *
 * ======
 *
 * Task.
 *
 * Given two strings S and T, return if they are equal when both are typed into
 * empty text editors. # means a backspace character.
 */
public class BackspaceStringCompare {
	public static class Solution {
		public boolean backspaceCompare(String S, String T) {
			int sn = S.length();
			int tn = T.length();
			int s = sn - 1;
			int t = tn - 1;

			while (s >= 0 || t >= 0) {
				if (s >= 0) {
					s = upd(S, s);
				}
				if (t >= 0) {
					t = upd(T, t);
				}

				if (s < 0 && t < 0) {
					return true;
				}
				if (s < 0 || t < 0 || S.charAt(s) != T.charAt(t)) {
					return false;
				}

				s--;
				t--;
			}

			return s < 0 && t < 0;
		}

		// find the next readable char from S
		private int upd(String S, int s) {
			int leftOver = 0;

			while (s >= 0 && (leftOver > 0 || S.charAt(s) == '#')) {
				if (S.charAt(s) == '#') {
					leftOver++;
				}
				else {
					leftOver--;
				}

				s--;
			}

			return s;
		}
	}
}
