package backtracking.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	 * Generate potential times.
	 */
	public static class Solution {
		private void gen(List<Integer> d, List<Integer> cur, List<List<Integer>> g, int k) {
			if (k == 4) {
				if (!cur.equals(d)) {
					List<Integer> ll = Arrays.asList(cur.get(0) * 10 + cur.get(1),
					cur.get(2) * 10 + cur.get(3));
					g.add(ll);
				}
			} else {
				for (int i = 0; i < d.size(); i++) {
					if (k == 0 && d.get(i) > 2) continue;
					if (k == 1 && cur.get(0) == 2 && d.get(i) > 3) continue;
					if (k == 2 && d.get(i) > 5) continue;
					cur.set(k, d.get(i));
					gen(d, cur, g, k + 1);
				}
			}
		}

		public String nextClosestTime(String s) {
			int d1 = s.charAt(0) - '0';
			int d2 = s.charAt(1) - '0';
			int d3 = s.charAt(3) - '0';
			int d4 = s.charAt(4) - '0';
			List<Integer> d = Arrays.asList(d1, d2, d3, d4);
			List<List<Integer>> g = new ArrayList<>();

			gen(d, Arrays.asList(0, 0, 0, 0), g, 0);

			return null;
		}
	}
}