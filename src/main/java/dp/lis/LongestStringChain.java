package dp.lis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1048
 */
public class LongestStringChain {

	/**
	 * Same as LIS.
	 */
	public static class Solution {

		public int longestStrChain(String[] words) {
			int n = words.length, ans = 1;
			Arrays.sort(words, (a, b) -> a.length() - b.length());
			int[] dp = new int[n];
			Arrays.fill(dp, 1);
			for (int i = 1; i < n; i++) {
				for (int j = i - 1; j >= 0; j--) {
					if (less(words[j], words[i])) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
						ans = Math.max(ans, dp[i]);
					}
				}
			}
			return ans;
		}

		private boolean less(String s1, String s2) {
			if (s2.length() - s1.length() != 1) return false;
			return areSimilar(s1, s2);
		}

		private boolean areSimilar(String s1, String s2) {
			boolean oneDiff = false;
			int j = 0;
			for (int i = 0; i < s1.length(); ) {
				if (s1.charAt(i) != s2.charAt(j)) {
					if (oneDiff) return false;
					j++;
					oneDiff = true;
				} else {
					i++;
					j++;
				}
			}
			boolean twoDiff = oneDiff && j == s1.length() - 1;
			return !twoDiff;
		}
	}

	/**
	 * Same idea as LIS, use substrings of string to check previous possible
	 * variants.
	 * For efficiency use bucket sort.
	 */
	public static class Solution22 {

		public int longestStrChain(String[] words) {
			Arrays.sort(words, (a, b) -> a.length() - b.length());

			Map<String, Integer> dp = new HashMap<>();
			for (String word : words) {
				dp.put(word, 1);
			}

			int max = 0;
			for (String word : words) {
				for (int i = 0; i < word.length(); i++) {
					String prev = word.substring(0, i) + word.substring(i + 1);
					if (dp.containsKey(prev)) {
						dp.put(word, Math.max(dp.get(word), dp.get(prev) + 1));
					}
				}
				max = Math.max(max, dp.get(word));
			}
			return max;
		}
	}

}