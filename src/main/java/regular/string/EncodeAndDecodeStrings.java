package regular.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 271
 */
public class EncodeAndDecodeStrings {
	/**
	 * Place the length of each string as the separator.
	 */
	class Solution1 {
		public String encode(List<String> strs) {
			StringBuilder sb = new StringBuilder();
			for (String s : strs) {
				sb.append(s.length());
				sb.append("|");
				sb.append(s);
			}
			return sb.toString();
		}

		public List<String> decode(String s) {
			List<String> ans = new ArrayList<>();
			int i = 0, n = s.length();
			while (i < n) {
				StringBuilder offsetSb = new StringBuilder();
				while (i < n && s.charAt(i) != '|') {
					offsetSb.append(s.charAt(i++));
				}
				if (i >= n) {
					return ans;
				}
				i++;
				int offset = Integer.parseInt(offsetSb.toString());
				StringBuilder sb = new StringBuilder();
				while (i < n && --offset >= 0) {
					sb.append(s.charAt(i++));
				}
				ans.add(sb.toString());
			}
			return ans;
		}
	}

	/*
	 * Use non 256-ascii character as a separator.
	 * Distinguish between empty string and empty array with 258th char.
	 */
	class Solution2 {
		public String encode(List<String> strs) {
			if (strs.size() == 0) {
				return Character.toString((char) 258);
			}

			String d = Character.toString((char) 257);
			StringBuilder sb = new StringBuilder();
			for (String s : strs) {
				sb.append(s);
				sb.append(d);
			}
			sb.deleteCharAt(sb.length() - 1);

			return sb.toString();
		}

		public List<String> decode(String s) {
			String d = Character.toString((char) 258);

			if (s.equals(d)) {
				return new ArrayList();
			}

			d = Character.toString((char) 257);
			return Arrays.asList(s.split(d, -1));
		}
	}
}

