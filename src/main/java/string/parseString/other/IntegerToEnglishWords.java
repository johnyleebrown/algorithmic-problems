package string.parseString.other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 273
 *
 * ======
 *
 * Task.
 *
 * Convert a non-negative integer to its english words representation.
 *
 * ======
 *
 * Similar: $INSERT_SIMILAR.
 *
 * ======
 *
 * Source: Leetcode
 */
public class IntegerToEnglishWords {
	/**
	 * SF, but important cases to remember: (1)we need a word from other when
	 * one of 3 digits is not zero;(2)spotting strings from tens array(like
	 * eleven)-if next int is 1 then we skip to 3rd digit of the pack.
	 */
	public static class Solution {
		private static String[] d = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
		private static String[] dd = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
		private static String[] other = new String[]{"Hundred", "Thousand", "Million", "Billion"};
		private static String[] tens = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

		public String numberToWords(int num) {
			if (num == 0)
				return "Zero";

			Deque<String> st = new ArrayDeque<>();
			int localIndex = 0; // index from 0 to 3, when 3 - reset
			int threes = 0;

			while (num > 0) {
				int curDigit = num % 10;
				num /= 10;
				localIndex++;

				if (localIndex == 1) {
					// case 1
					if (threes != 0 && (curDigit != 0 || num % 10 != 0 || (num / 10) % 10 != 0)) {
						st.push(other[threes]);
					}

					// case 2
					if (num % 10 == 1) {
						st.push(tens[curDigit]);
						num /= 10;
						localIndex = 2;
					} else if (curDigit != 0) {
						st.push(d[curDigit]);
					}
				} else if (localIndex == 2) {
					if (curDigit != 0)
						st.push(dd[curDigit]);
				} else if (localIndex == 3) {
					if (curDigit != 0) {
						st.push(other[0]);
						st.push(d[curDigit]);
					}
					localIndex = 0;
					threes++;
				}
			}

			StringBuilder ans = new StringBuilder();
			int size = st.size();
			while (--size >= 1) {
				ans.append(st.pop()).append(" ");
			}
			ans.append(st.pop());

			return ans.toString();
		}
	}
}
