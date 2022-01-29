package string.hash;

import java.math.BigInteger;
import java.util.Random;

/**
 * LongestRepeatedSubsequence
 *
 * ======
 *
 * Task.
 *
 * Find out the length of the longest repeated subsequence of s1 in s2.
 * s1="ab", s2="abcababdefab" ans=2
 *
 * ======
 *
 * Company: Pure Storage
 */
public class LongestRepeatedSubsequence {

	public interface S {
		int getLongestRepeatedSubsequence(String shortStr, String longStr);
	}

	/**
	 * Brute force.
	 */
	public static class Solution1 implements S {
		public int getLongestRepeatedSubsequence(String shortStr, String longStr) {

			// base
			if (shortStr == null || longStr == null) return 0;
			int n = longStr.length();
			int m = shortStr.length();
			if (m == 0 || n == 0) return 0;
			if (m > n) return 0;

			int ans = 0;
			for (int i = 0; i < n; i++) {
				int localCount = 0;
				while (startsWith(i, shortStr, longStr)) {
					i += m;
					localCount++;
				}
				ans = Math.max(ans, localCount);
			}

			return ans;
		}

		private boolean startsWith(int start, String shortStr, String longStr) {
			for (int j = 0; j < shortStr.length(); j++) {
				if (j + start >= longStr.length()) {
					return false;
				}
				if (longStr.charAt(j + start) != shortStr.charAt(j)) {
					return false;
				}
			}
			return true;
		}
	}

	public static class Solution2 implements S {
		private final long R = 256;
		private long powR;
		private long Q;

		@Override
		public int getLongestRepeatedSubsequence(String shortStr, String longStr) {

			// base
			if (shortStr == null || longStr == null) return 0;
			int n = longStr.length();
			int m = shortStr.length();
			if (m == 0 || n == 0) return 0;
			if (m > n) return 0;

			// init
			Q = generateRandomQ();
			powR = calculatePowR(m);

			//
			long shortStrHash = getHashCode(shortStr);
			long rollingHash = getHashCode(longStr, 0, m);
			int ans = 0;
			int l = 0, r = m;
			while (r < n) {
				int localCount = 0, localL = l, localR = r;
				long localHash = rollingHash;
				while (localHash == shortStrHash) {
					localL+=m;
					localR+=m;
					localHash = getHashCode(longStr, localL, localR);
					localCount++;
				}
				ans = Math.max(ans, localCount);
				rollingHash = removeFirst(rollingHash, longStr.charAt(l));
				rollingHash = addLast(rollingHash, longStr.charAt(r));
				l++;
				r++;
			}

			return ans;
		}

		/*========================================================*/

		private long addLast(long h, char c) {
			return ((h * R) % Q + c) % Q;
		}

		private long removeFirst(long h, char c) {
			return (h - (c * powR) % Q + Q) % Q;
		}

		private long calculatePowR(int m) {
			long powR = 1;
			for (int i = 1; i <= m - 1; i++) {
				powR = (powR * R) % Q;
			}
			return powR;
		}

		private long getHashCode(String s) {
			return getHashCode(s, 0, s.length());
		}

		private long getHashCode(String s, int start, int end) {
			if (end > s.length()) return -1;
			long h = 0;
			for (int i = start; i < end; i++) {
				h = (R * h + s.charAt(i)) % Q;
			}
			return h;
		}

		private long generateRandomQ() {
			return BigInteger.probablePrime(31, new Random()).longValue();
		}
	}
}