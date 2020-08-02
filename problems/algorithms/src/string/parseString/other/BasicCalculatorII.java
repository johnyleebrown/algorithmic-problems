package string.parseString.other;

/**
 * 227
 *
 * ======
 *
 * Task.
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, /
 * operators and empty spaces . The integer division should truncate toward
 * zero.
 *
 * ======
 *
 * Source: Leetcode
 */
public class BasicCalculatorII {
	/**
	 * Helper approach - like if we had an task with brackets, we would go in
	 * recursion every bracket to calculate whatever there is in brackets.
	 *
	 * We split equation like so: helper() + helper() + ... helper()
	 * We go from ('+' or '-') to ('+' or '-')
	 */
	public static class Solution {
		static int i;

		public int calculate(String s) {
			i = 0;
			char[] ca = s.toCharArray();
			int ret = 0;
			boolean pos = true;
			while (i < ca.length) {
				int ans = helper(ca);
				if (!pos) ret -= ans;
				else ret += ans;
				if (i >= ca.length) break;
				pos = ca[i] != '-';
				i++;
			}
			return ret;
		}

		private int helper(char[] ca) {
			int ans = 1;
			char sign = '*'; // default sign
			int num = 0;
			while (i < ca.length && ca[i] != '+' && ca[i] != '-') {
				if (Character.isDigit(ca[i])) {
					num = num * 10 + ca[i] - '0';
				} else if (ca[i] == '*' || ca[i] == '/') {
					if (sign == '*') ans *= num;
					else ans /= num;
					sign = ca[i];
					num = 0;
				}
				i++;
			}
			if (sign == '*') ans *= num;
			else ans /= num;
			return ans;
		}
	}
}