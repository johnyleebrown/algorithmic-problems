package string.construct;

/**
 * 2182
 */
public class ConstructStringWithRepeatLimit {

	/**
	 * Fill as much as we can then go to the next one, the go back if some left.
	 */
	public static class Solution {

		public String repeatLimitedString(String s, int repeatLimit) {
			int n = s.length();

			int[] count = new int[26];
			for (int i = 0; i < n; i++) {
				count[s.charAt(i) - 'a']++;
			}

			StringBuilder sb = new StringBuilder();
			int i = 25, go2 = -1;
			while (i >= 0) {
				int max = Math.min(repeatLimit, count[i]);
				int k = max;
				char ca = (char) (i + 'a');

				if (go2 != -1 && max > 0) {
					sb.append(ca);
					count[i]--;
					n--;
					i = go2;
					go2 = -1;
					continue;
				}

				while (--max >= 0 && n != 0) {
					sb.append(ca);
					count[i]--;
					n--;
				}

				// set go2 position
				if (count[i] > 0) {
					go2 = i;
				} else if (k > 0) {
					go2 = -1;
				}

				i--;
			}

			return sb.toString();
		}
	}
}
