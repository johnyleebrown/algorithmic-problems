package twoPointers.regular.other;

/**
 * 696
 */
public class CountBinarySubstrings {

	public static class Solution {

		public int countBinarySubstrings(String s) {
			int a = 0, b = 0;
			boolean isCharChanged = false;
			int ans = 0;
			for (int i = 0; i < s.length(); i++) {
				if (i > 0 && s.charAt(i - 1) != s.charAt(i)) {
					ans += Math.min(a, b);
					isCharChanged = !isCharChanged;//t
					if (isCharChanged) {
						b = 0;
					} else {
						a = 0;
					}
				}
				if (isCharChanged) {
					b++;
				} else {
					a++;
				}
			}
			ans += Math.min(a, b);
			return ans;
		}
	}
}
