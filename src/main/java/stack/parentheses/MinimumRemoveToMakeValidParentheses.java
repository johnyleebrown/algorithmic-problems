package stack.parentheses;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
public class MinimumRemoveToMakeValidParentheses {
	/**
	 * Keep count of the balance. Remove right brackets with no pair, then left.
	 */
	public static class Solution {
		public String minRemoveToMakeValid(String s) {
			StringBuilder sb = new StringBuilder(s);

			int i = 0;
			int left = 0;
			int right = 0;
			while (i < sb.length()) {
				char c = sb.charAt(i);
				if (c == ')' && left > 0) {
					left--;
				} else if (c == '(') {
					left++;
				}
				// no pair
				else if (c == ')') {
					right++;
					sb.deleteCharAt(i);
					// so we could stay at the same place
					i--;
				}
				i++;
			}

			i = sb.length() - 1;
			left = 0;
			right = 0;
			while (i >= 0) {
				char c = sb.charAt(i);
				if (c == '(' && right > 0) {
					right--;
				} else if (c == ')') {
					right++;
				}
				// no pair
				else if (c == '(') {
					left++;
					sb.deleteCharAt(i);
				}
				i--;
			}

			return sb.toString();
		}
	}
}