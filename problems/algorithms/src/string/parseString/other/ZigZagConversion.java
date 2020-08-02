package string.parseString.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 6
 *
 * ======
 *
 * Task.
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 * ======
 *
 * Source: Leetcode
 */
public class ZigZagConversion {
	/**
	 * Each row is a list of chars, when moving - go up and down, calculate index of list and
	 * insert char there.
	 */
	public static class Solution {
		public String convert(String s, int n) {
			int m = s.length();
			if (m == 0) return "";
			if (n == 1) return s;
			List<Character>[] l = new List[n];
			boolean down = true;
			for (int i = 0, j = 0; i < m; i++) {
				if (down) {
					if (j > n - 1) {
						down = !down;
						j = n - 2;
					}
				} else {
					if (j < 0) {
						down = !down;
						j = 1;
					}
				}
				if (l[j] == null) l[j] = new ArrayList<>();
				l[j].add(s.charAt(i));
				if (down) {
					j++;
				} else {
					j--;
				}
			}
			char[] ca = new char[m];
			int i = 0;
			for (List<Character> ll : l) {
				if (ll == null) continue;
				for (char x : ll) {
					ca[i++] = x;
				}
			}
			return String.valueOf(ca);
		}
	}
}