package tricky;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
	 *
	 * the idea for this solution is to find the next number for each of the numbers starting
	 * from the right
	 * if we cant find it then we set it to the smallest one we have
	 * example: 12:34 => 12:41 is the next
	 */
	public static class Solution {
		public String nextClosestTime(String t) {
			int[] ans = new int[]{t.charAt(0) - '0', t.charAt(1) - '0', t.charAt(3) - '0', t.charAt(4) - '0'};
			int[] next = new int[4];

			int min = Integer.MAX_VALUE;
			// find next number for each
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
				int x = getNext(ans, next, i);
				// if we couldnt find the next
				if (x == 10) {
					ans[i] = min;
				}
				// found the next, set the next and return the answer
				else {
					ans[i] = x;
					return getAnswer(ans);
				}
			}

			return getAnswer(ans);
		}

		private String getAnswer(int[] ans) {
			return ans[0] + "" + ans[1] + ":" + ans[2] + "" + ans[3];
		}

		private int getNext(int[] ans, int[] next, int i) {
			int x = next[i];
			if (x == 10) return 10;
			if (i == 2 && x > 5) return 10;
			else if (i == 1 && ans[0] == 2 && x > 3) return 10;
			else if (i == 0 && x > 2) return 10;
			else return x;
		}
	}

	/**
	 * We have 3600 minutes in 1 day, so increment minute by minute.
	 * We have a while loop where we increment position by position
	 * So if we want to incr 00:59 we would increment
	 * index 3 => 00:50 then
	 * ind2 => 00:00 then
	 * ind1 => 01:00
	 */
	public static class Solution2 {
		public String nextClosestTime(String s) {
			// add minute b minute and see if we have the numbers
			int[] d2 = new int[4];
			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < s.length(); i++) {
				if (i == 2) {
					continue;
				}
				int x = Integer.parseInt(String.valueOf(s.charAt(i)));
				if (i < 2) {
					d2[i] = x;
				} else {
					d2[i - 1] = x;
				}
				set.add(x);
			}

			int position = 4;
			// flag that shows that it is not a temporary result cuz we incr pos by position
			boolean ready = false;
			int count = 3600;
			while (--count >= 0) {
				if (position == 4) { // xx:xy
					if (d2[3] == 9) {
						position = 3;
						d2[3] = 0;
						ready = false;
					} else {
						d2[3]++;
						ready = true;
					}
				} else if (position == 3) { // xx:yx
					if (d2[2] == 5) {
						position = 2;
						d2[2] = 0;
						ready = false;
					} else {
						d2[2]++;
						position = 4;
						ready = true;
					}
				} else if (position == 2) { // xy:xx
					if (d2[1] == 9 || (d2[1] == 3 && d2[0] == 2)) {
						position = 1;
						d2[1] = 0;
						ready = false;
					} else {
						d2[1]++;
						position = 4;
						ready = true;
					}
				} else { // yx:xx
					if (d2[0] == 2) {
						d2[0] = 0;
						ready = false;
					} else {
						d2[0]++;
						ready = true;
					}
					position = 4;
				}

				if (ready && check(set, d2)) {
					return d2[0] + "" + d2[1] + ":" + d2[2] + "" + d2[3];
				}
			}

			return s;
		}

		/**
		 * check if we have all the numbers from the original
		 */
		private boolean check(Set<Integer> set, int[] d2) {
			return set.contains(d2[0]) && set.contains(d2[1]) && set.contains(d2[2]) && set.contains(d2[3]);
		}
	}

	/**
	 * Same thing as in S1 but with TreeSet
	 */
	public static class Solution3 {
		public String nextClosestTime(String time) {
			char[] res = time.toCharArray();
			Character[] digits = new Character[]{res[0], res[1], res[3], res[4]};
			TreeSet<Character> set = new TreeSet<Character>(Arrays.asList(digits));

			res[4] = next(set, res[4], '9');
			if (time.charAt(4) < res[4]) return new String(res);

			res[3] = next(set, res[3], '5');
			if (time.charAt(3) < res[3]) return new String(res);

			res[1] = next(set, res[1], res[0] == '2' ? '3' : '9');
			if (time.charAt(1) < res[1]) return new String(res);

			res[0] = next(set, res[0], '2');
			return new String(res);
		}

		private char next(TreeSet<Character> set, char c, char limit) {
			Character n = set.higher(c);
			return n == null || n > limit ? set.first() : n;
		}
	}

	/**
	 * Generate all variations with backtracking and then find the closest.
	 * Check {@link backtracking.numbers.NextClosestTime}
	 */
	public static class Solution4 {
	}
}