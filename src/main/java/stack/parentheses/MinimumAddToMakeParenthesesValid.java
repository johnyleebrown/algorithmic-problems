package stack.parentheses;

import java.util.Stack;

/**
 * 921. Minimum Add to Make Parentheses Valid
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 */
public class MinimumAddToMakeParenthesesValid {

	/**
	 * SF
	 */
	public static class Solution1 {
		public int minAddToMakeValid(String s) {
			Stack<Character> st = new Stack<>();
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == ')' && !st.empty() && st.peek() == '(') {
					st.pop();
				} else {
					st.push(c);
				}
			}
			return st.size();
		}
	}

	/**
	 * Space O(1)
	 * If we have spare left brackets & we see right bracket - we can decrease count.
	 */
	public static class Solution2 {
		public int minAddToMakeValid(String s) {
			int left = 0, right = 0;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == ')' && left > 0) {
					left--;
				} else if (c == '(') {
					left++;
				} else {
					right++;
				}
			}
			return left + right;
		}
	}
}