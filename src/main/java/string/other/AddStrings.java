package string.other;

/**
 * 415. Add Strings https://leetcode.com/problems/add-strings/
 */
public class AddStrings {
	public static class Solution {
		public String addStrings(String num1, String num2) {
			if (num1.length() == 1 && num1.charAt(0) == '0') {
				return num2;
			}
			if (num2.length() == 1 && num2.charAt(0) == '0') {
				return num1;
			}
			StringBuilder sb = new StringBuilder();
			int i = num1.length() - 1, j = num2.length() - 1;
			int leftover = 0;
			while (i >= 0 || j >= 0) {
				int iValue = i < 0 ? 0 : num1.charAt(i) - '0';
				int jValue = j < 0 ? 0 : num2.charAt(j) - '0';
				int sum = iValue + jValue + leftover;
				leftover = sum / 10;
				int res = sum % 10;
				sb.append(res);
				i--;
				j--;
			}
			if (leftover == 1) sb.append(leftover);
			return sb.reverse().toString();
		}
	}
}