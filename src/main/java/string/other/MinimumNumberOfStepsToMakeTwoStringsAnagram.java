package string.other;

/**
 * 1347
 */
public class MinimumNumberOfStepsToMakeTwoStringsAnagram {

	class Solution {

		public int minSteps(String s, String t) {
			int[] c1 = new int[26];
			int n = s.length();
			for (int i = 0; i < n; i++) {
				c1[s.charAt(i) - 'a']++;
			}
			int m = t.length();
			int[] c2 = new int[26];
			for (int i = 0; i < m; i++) {
				c2[t.charAt(i) - 'a']++;
			}
			int res = 0;
			for (int i = 0; i < 26; i++) {
				res += Math.abs(c1[i] - c2[i]);
			}
			return res / 2;
		}
	}
}