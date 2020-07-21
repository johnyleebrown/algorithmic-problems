package math.numberTheory;

/**
 * 43
 *
 * ======
 *
 * Task.
 *
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1
 * and num2, also represented as a string.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MultiplyStrings {
	/**
	 * Vstolbik - summarizing with each new row(i)
	 * @formatter:off
	 *     9111
	 *     8111
	 *  -------
	 *     9111
	 *    9111
	 *   9111
	 * 56888
	 * --------
	 * 57899321
	 * @formatter:on
	 * k - offset from the end of the result string - with each row(i) it increases by 1
	 * t - offset for each new digit of the row when multiplying
	 */
	public static class Solution {
		public String multiply(String num1, String num2) {
			if ("0".equals(num1) || "0".equals(num2)) return "0";
			return num1.length() >= num2.length() ? h(num1, num2) : h(num2, num1);
		}

		String h(String num1, String num2) {
			StringBuilder ans = new StringBuilder();
			int rem2 = 0, rem = 0;
			for (int i = num2.length() - 1, k = 0; i >= 0; i--, k++, rem = rem2 = 0) {
				int cur2 = getint(num2.charAt(i));
				for (int j = num1.length() - 1, t = 0; j >= 0; j--, t++) {
					int cur1 = getint(num1.charAt(j));
					int x = cur1 * cur2 + rem;
					rem = x / 10;
					int ip = ans.length() - 1 - k - t;
					int val = x % 10 + rem2;
					if (ip < 0) {
						ans.insert(0, tochar(val % 10));
					} else {
						val += getint(ans.charAt(ip));
						ans.setCharAt(ip, tochar(val % 10));
					}
					rem2 = val / 10;
				}
				if (rem2 + rem != 0) ans.insert(0, tochar(rem2 + rem));
			}
			return ans.toString();
		}

		char tochar(int i) {
			return (char) (i + '0');
		}

		int getint(char c) {
			return c - '0';
		}
	}
}