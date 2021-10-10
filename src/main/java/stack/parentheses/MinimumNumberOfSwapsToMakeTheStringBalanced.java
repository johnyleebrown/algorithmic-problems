package stack.parentheses;

import java.util.Stack;

/**
 * 1963. Minimum Number of Swaps to Make the String Balanced
 * https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
 */
public class MinimumNumberOfSwapsToMakeTheStringBalanced {
	/**
	 * When we encounter disbalance ']' - swap with bracket '[' closest to the end.
	 * Seems like the most optimal way to find balance.
	 */
	public static class Solution1 {
		public int minSwaps(String s) {
			int left = 0, right = 0;
			// right pointer
			int rp = s.length() - 1;
			int res = 0;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == ']' && left > 0) {
					left--;
				} else if (c == '[') {
					left++;
				} else {
					right++;
				}
				// disbalance
				if (right == 1) {
					while (rp >= 0 && s.charAt(rp) != '[') {
						rp--;
					}
					if (rp >= 0) {
						res++;
						// balance
						right--;
						// increment replacement
						left++;
					}
				}
			}
			return res;
		}
	}

	/**
	 * First, lets get rid of all matching brackets. We can do using a stack, and every time we have
	 * a ], we pop a [ that we have seen.
	 *
	 * At the end, we're always left with a sequence of close brackets followed by open brackets,
	 * e.g ]]]..[[[..
	 *
	 * Now, whats the best strategy to minimize swaps for that kind of string? We can notice that in
	 * the best swap, we can match two sets of brackets! e.g ]][[ can match to [][] with one swap!
	 * Thus, for every swap, we can match two brackets, which gives us the answer of
	 * unmatched_brackets / 2
	 */
	public static class Solution2 {
		public int minSwaps(String s) {
			Stack<Integer> stack = new Stack();
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '[') {
					stack.push(i);
				} else {
					if (stack.size() > 0) {
						stack.pop();
					}
				}
			}
			int res = stack.size();
			return (res + 1) / 2;
		}
	}
}