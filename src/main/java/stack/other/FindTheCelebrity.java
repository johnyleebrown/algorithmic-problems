package stack.other;

import java.util.Stack;

/**
 * 277
 */
public class FindTheCelebrity {

	/**
	 * Check every two, add candidates to stack.
	 */
	public static class Solution {

		public int findCelebrity(int n) {
			if (n <= 0) return -1;
			if (n == 1) return 0;

			Stack<Integer> stack = new Stack<>();
			for (int i = 0; i < n; i++) {
				stack.push(i);
			}

			while (stack.size() > 1) {
				int a = stack.pop();
				int b = stack.pop();

				if (knows(a, b)) stack.push(b);
				else stack.push(a);
			}

			int c = stack.pop();
			for (int i = 0; i < n; i++) {
				if (i != c && (knows(c, i) || !knows(i, c))) return -1;
			}

			return c;
		}

		private boolean knows(int a, int b) {
			return false;
		}
	}

	/**
	 * The first loop will stop to an candidate i who doesn't know anyone from i+1 to n-1.
	 * For any
	 * previous x < i either know sb else or is not known by sb else.
	 *
	 * <p>The second loop will check whether i knows anyone from 0 to i-1.
	 *
	 * <p>The third loop is gonna check whether all party participants know x or not.
	 */
	public static class Solution2 {

		public int findCelebrity(int n) {
			int x = 0;
			for (int i = 0; i < n; ++i) {
				if (knows(x, i)) x = i;
			}
			for (int i = 0; i < x; ++i) {
				if (knows(x, i)) return -1;
			}
			for (int i = 0; i < n; ++i) {
				if (!knows(i, x)) return -1;
			}
			return x;
		}

		private boolean knows(int a, int b) {
			return false;
		}
	}
}
