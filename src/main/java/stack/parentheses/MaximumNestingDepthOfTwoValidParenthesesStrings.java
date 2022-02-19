package stack.parentheses;

/**
 * 1111
 */
public class MaximumNestingDepthOfTwoValidParenthesesStrings {

	/**
	 * Find largest depth, half it - values below the half should be A, above should be B.
	 */
	public static class Solution {

		public int[] maxDepthAfterSplit(String s) {
			int n = s.length();
			if (n == 1) return new int[]{0};

			// find largest depth
			int[] cc = new int[n];
			int count = 0, max = 0;
			for (int i = 0; i < n; i++) {
				if (s.charAt(i) == '(') {
					count++;
					cc[i] = count;
				} else {
					cc[i] = count;
					count--;
				}
				max = Math.max(max, count);
			}

			int half = max / 2;
			for (int i = 0; i < n; i++) {
				if (cc[i] <= half) {
					cc[i] = 0;
				} else {
					cc[i] = 1;
				}
			}
			return cc;
		}
	}
}
