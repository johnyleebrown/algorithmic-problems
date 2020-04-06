package array.greedy;

import java.util.PriorityQueue;

/**
 * 1405
 *
 * ======
 *
 * Task.
 *
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb'
 * or 'ccc' as a substring.
 *
 * Given three integers a, b and c, return any string s, which satisfies
 * following conditions:
 *
 * s is happy and longest possible. s contains at most a occurrences of the
 * letter 'a', at most b occurrences of the letter 'b' and at most c occurrences
 * of the letter 'c'. s will only contain 'a', 'b' and 'c' letters.
 *
 * If there is no such string s return the empty string "".
 *
 * ======
 *
 * Source: Leetcode
 */
public class LongestHappyString {
	/**
	 * we try to use 2 chars if we have a chance, then we drain that resource,
	 * and go to the next one.
	 *
	 * case: drain 0 w 2,and 1 w 1,then 2
	 */
	public static class Solution {
		public String longestDiverseString(int a, int b, int c) {
			PriorityQueue<Ch> pq = new PriorityQueue<>((e1,e2)->e2.c-e1.c);
			initPq(pq, a, b, c);
			StringBuilder sb = new StringBuilder();
			while (pq.size() > 1) {
				Ch first = append(pq, sb, -1);
				Ch second = append(pq, sb, first.c);
				if (first.c > 0) {
					pq.add(first);
				}
				if (second.c > 0) {
					pq.add(second);
				}
			}

			if (!pq.isEmpty()) {
				if (sb.length() == 0 || sb.charAt(sb.length() - 1) != pq.peek().ch) {
					append(pq, sb, -1);
				}
			}

			return sb.toString();
		}

		private Ch append(PriorityQueue<Ch> pq, StringBuilder sb, int firstCount) {
			Ch cur = pq.poll();
			if (cur.c >= 2 && (firstCount == -1 || firstCount < cur.c)) {
				sb.append(cur.ch);
				sb.append(cur.ch);
				cur.c -= 2;
			} else {
				sb.append(cur.ch);
				cur.c -= 1;
			}
			return cur;
		}

		private void initPq(PriorityQueue<Ch> pq, int a, int b, int c) {
			if (a > 0) {
				pq.add(new Ch(a, 'a'));
			}
			if (b > 0) {
				pq.add(new Ch(b, 'b'));
			}
			if (c > 0) {
				pq.add(new Ch(c, 'c'));
			}
		}

		class Ch {
			int c;
			char ch;

			private Ch(int c, char ch) {
				this.c = c;
				this.ch = ch;
			}
		}
	}
}