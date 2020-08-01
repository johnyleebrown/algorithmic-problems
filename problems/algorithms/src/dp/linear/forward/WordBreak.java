package dp.linear.forward;

import java.math.BigInteger;
import java.util.*;

/**
 * 139
 *
 * ======
 *
 * Task.
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * ======
 *
 * Source: Leetcode
 */
public class WordBreak {
	/**
	 * Check if we cur substring is ok, if yes, check [i+1,n] substring, combine answers.
	 */
	public static class Solution {
		public boolean wordBreak(String s, List<String> w) {
			return helper(0, new Boolean[s.length()], s, new HashSet<>(w));
		}

		private boolean helper(int j, Boolean[] dp, String s, Set<String> set) {
			if (j == s.length()) return true;
			if (dp[j] != null) return dp[j];

			boolean ans = false;
			for (int i = j; i < s.length(); i++) {
				String cur = s.substring(j, i + 1);
				if (set.contains(cur)) {
					ans |= helper(i + 1, dp, s, set);
				}
			}

			dp[j] = ans;
			return ans;
		}
	}

	/**
	 * Rolling Hash Solution.
	 */
	public static class Solution2 {
		long Q, R;

		public boolean wordBreak(String s, List<String> d) {

			// init
			Q = getRandomPrime();
			R = 256;
			int n = s.length();
			char[] ca = s.toCharArray();

			// convert all variants to hashes
			Set<Long> hs = new HashSet<>();
			for (String str : d) {
				hs.add(getHashCode(str));
			}

			// keep suffix hashes (i..j )
			long[][] hm = new long[n][n];

			Set<Integer> seen = new HashSet<>();
			Deque<Integer> q = new ArrayDeque<>();
			q.add(0);
			seen.add(0);

			while (!q.isEmpty()) {
				int start = q.removeFirst();
				long h = 0;
				for (int i = start; i < n; i++) {
					if (hm[start][i] != 0) {
						h = hm[start][i];
					} else {
						h = addLast(h, ca[i]);
						hm[start][i] = h;
					}

					if (hs.contains(h) && !seen.contains(i + 1)) {
						if (i + 1 == n) return true;
						q.add(i + 1);
						seen.add(i + 1);
					}
				}
			}

			return false;
		}

		long getHashCode(String s) {
			long h = 0;
			for (int i = 0; i < s.length(); i++) {
				h = (h * R + s.charAt(i)) % Q;
			}
			return h;
		}

		long getRandomPrime() {
			return BigInteger.probablePrime(31, new Random()).longValue();
		}

		long addLast(long h, char c) {
			return (h * R + c) % Q;
		}
	}
}