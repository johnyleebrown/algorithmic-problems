package regular.string;

/*
 * 482
 */
public class LicenseKeyFormatting {
	public static class Solution {
		public String licenseKeyFormatting(String S, int k) {
			StringBuilder sb = new StringBuilder();
			int count = 0;
			String s = S.toUpperCase();

			for (int i = s.length() - 1; i >= 0; i--) {
				char ch = s.charAt(i);
				if (ch != '-') {
					if (count == k) {
						sb.append("-");
						count = 0;
						sb.append(ch);
					}
					else {
						sb.append(ch);
					}

					count++;
				}
			}

			return sb.reverse().toString();
		}
	}
}