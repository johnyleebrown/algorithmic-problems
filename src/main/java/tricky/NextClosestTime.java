package tricky;

/**
 * 681
 *
 * ======
 *
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current
 * digits. There is no limit on how many times a digit can be reused.
 *
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all
 * valid. "1:34", "12:9" are all invalid.
 *
 *
 *
 * Example 1:
 *
 * Input: time = "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5
 * minutes later.
 * It is not 19:33, because this occurs 23 hours and 59 minutes later.
 * Example 2:
 *
 * Input: time = "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
 * It may be assumed that the returned time is next day's time since it is smaller than the input
 * time numerically.
 *
 *
 * Constraints:
 *
 * time.length == 5
 * time is a valid time in the form "HH:MM".
 * 0 <= HH < 24
 * 0 <= MM < 60
 *
 * ======
 *
 * https://leetcode.com/problems/next-closest-time/
 */
public class NextClosestTime {
	/**
	 * key insight
	 * if we haven't found the next number some position
	 * then the next best for that position would be the smallest number of all
	 */
	public static class Solution {
		public String nextClosestTime(String t) {
			int[] ans = new int[]{t.charAt(0) - '0', t.charAt(1) - '0', t.charAt(3) - '0', t.charAt(4) - '0'};
			int[] next = new int[4];

			int min = Integer.MAX_VALUE;
			for (int i = 0; i < 4; i++) {
				min = Math.min(min, ans[i]);
				next[i] = 10;
				for (int j = 0; j < 4; j++) {
					if (ans[i] < ans[j]) {
						next[i] = Math.min(next[i], ans[j]);
					}
				}
			}

			for (int i = 3; i >= 0; i--) {
				int x = bs(ans, next, i);
				if (x == 10) {
					ans[i] = min;
				} else {
					ans[i] = x;
					return getAns(ans);
				}
			}

			return getAns(ans);
		}

		private String getAns(int[] ans) {
			return ans[0] + "" + ans[1] + ":" + ans[2] + "" + ans[3];
		}

		private int bs(int[] ans, int[] next, int i) {
			int x = next[i];
			if (x == 10) return 10;
			if (i == 2 && x > 5) return 10;
			else if (i == 1 && ans[0] == 2 && x > 3) return 10;
			else if (i == 0 && x > 2) return 10;
			else return x;
		}
	}
}