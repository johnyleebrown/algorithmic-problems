package string.parseString.parenthesis;

/**
 * 678
 */
public class ValidParenthesisString {

	/**
	 * Go from l to r and from r to l. Check if it is enough of each of type of
	 * bracket and asterisks, if sum < 0 then return false.
	 */
	public static class Solution {

		public boolean checkValidString(String s) {
			int n = s.length();

			int l = 0;
			for (int i = 0; i < n; i++) {
				if (s.charAt(i) == '(' || s.charAt(i) == '*') {
					l++;
				} else {
					l--;
				}
				if (l < 0) {
					return false;
				}
			}
			if (l == 0) {
				return true;
			}

			int r = 0;
			for (int i = n - 1; i >= 0; i--) {
				if (s.charAt(i) == ')' || s.charAt(i) == '*') {
					r++;
				} else {
					r--;
				}
				if (r < 0) {
					return false;
				}
			}

			return true;
		}
	}
}
