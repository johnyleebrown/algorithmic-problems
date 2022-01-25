package stack.parentheses;

import java.util.Stack;

/**
 * 1047
 */
public class RemoveAllAdjacentDuplicatesInString {

	/**
	 * SF with a stack
	 */
	public static class Solution1 {

		public String removeDuplicates(String s) {
			StringBuilder sb = new StringBuilder();
			for (char c : s.toCharArray()) {
				if (!(sb.length() == 0) && sb.charAt(sb.length() - 1) == c) {
					sb.deleteCharAt(sb.length() - 1);
				} else {
					sb.append(c);
				}
			}
			return sb.toString();
		}
	}

	class Solution2 {

		public String removeDuplicates(String s) {
			Stack<Character> st = new Stack<>();
			for (char c : s.toCharArray()) {
				if (!st.isEmpty() && st.peek() == c) {
					st.pop();
				} else {
					st.push(c);
				}
			}
			StringBuilder sb = new StringBuilder();
			for (char c : st) {
				sb.append(c);
			}
			return sb.toString();
		}
	}

	/**
	 * Move the possible duplicate closer to i. Then check if there is a dup.
	 */
	class Solution3 {

		public String removeDuplicates(String s) {
			int i = 0, n = s.length();
			char[] res = s.toCharArray();
			for (int j = 0; j < n; ++j, ++i) {
				res[i] = res[j];
				if (i > 0 && res[i - 1] == res[i]) i -= 2;
			}
			return new String(res, 0, i);
		}
	}
}