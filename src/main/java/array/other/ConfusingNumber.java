package array.other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1056
 *
 * ======
 *
 * Task.
 *
 * Given a number N, return true if and only if it is a confusing number, which
 * satisfies the following condition:
 *
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9
 * are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3,
 * 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number
 * is a number that when rotated 180 degrees becomes a different number with
 * each digit valid.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ConfusingNumber {
		public static class Solution {
				public boolean confusingNumber(int n) {
						Deque<Integer> q = new ArrayDeque<>();
						int x = n;
						while (x > 0) {
								if (isBad(x % 10)) {
										return false;
								}
								q.addFirst(x % 10);
								x /= 10;
						}
						int ret = 0;
						double tens = 1;
						for (int i : q) {
								ret += getFlipped(i) * tens;
								tens *= 10;
						}
						return ret != n;
				}

				private boolean isBad(int n) {
						return n == 2 || n == 3 || n == 4 || n == 5 || n == 7;
				}

				private int getFlipped(int n) {
						if (n == 0) return 0;
						else if (n == 1) return 1;
						else if (n == 6) return 9;
						else if (n == 8) return 8;
						else return 6;
				}
		}
}