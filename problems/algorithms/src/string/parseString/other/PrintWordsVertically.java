package string.parseString.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 1324
 *
 * ======
 *
 * Task.
 *
 * Given a string s. Return all the words vertically in the same order in which they appear in s.
 * Words are returned as
 * a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
 * Each word would be put
 * on only one column and that in one column there will be only one word.
 */
public class PrintWordsVertically {
	/**
	 * SF.
	 */
	class Solution {
		public List<String> printVertically(String s) {
			String[] ar = s.split(" ");
			int maxLen = 0;
			for (String str : ar) {
				maxLen = Math.max(maxLen, str.length());
			}

			List<StringBuilder> preRes = new ArrayList<>();
			for (String str : ar) {
				for (int i = 0; i < maxLen; i++) {
					if (preRes.size() <= i) {
						preRes.add(new StringBuilder());
					}
					if (i < str.length()) {
						preRes.get(i).append(str.charAt(i));
					} else {
						preRes.get(i).append(" ");
					}
				}
			}

			List<String> res = new ArrayList<>();
			for (StringBuilder sb : preRes) {
				res.add(sb.toString().replaceFirst("\\s++$", ""));
			}

			return res;
		}
	}
}
