package string.hash;

import java.math.BigInteger;
import java.util.Random;

/**
 * 214
 */
public class ShortestPalindrome {

	/**
	 * Rolling hash comparison of s and reversed s
	 */
	public static class Solution1 {

		private long Q;
		private int R;
		private long[] rp;
		private long modInverse;

		public String shortestPalindrome(String s) {

			if (s.length() == 0) {
				return s;
			}

			precompute(s);

			long hashOrigin = computeHash(s);

			String reversed = new StringBuilder(s).reverse().toString();
			long hashReversed = computeHash(reversed);

			StringBuilder ans = new StringBuilder();
			for (int i = 0; hashOrigin != hashReversed; i++) {
				hashOrigin = removeLast(hashOrigin, s, s.length() - 1 - i);
				hashReversed = removeFirst(hashReversed, reversed, i);
				ans.append(reversed.charAt(i));
			}

			ans.append(s);
			return ans.toString();
		}

		private void precompute(String s) {
			Q = getRandomPrime();
			R = 256;
			rp = computeRadixPowers(s.length());
			modInverse = BigInteger.valueOf(R).modInverse(BigInteger.valueOf(Q)).longValue();
		}

		private long removeLast(long hashOrigin, String origin, int i) {
			long curChar = origin.charAt(i);
			return ((hashOrigin - curChar) * modInverse) % Q;
		}

		private long removeFirst(long hashReversed, String reversed, int i) {
			char curChar = reversed.charAt(i);
			int power = reversed.length() - i - 1;
			return (hashReversed - (rp[power] * curChar) % Q + Q) % Q;
		}

		private long getRandomPrime() {
			return BigInteger.probablePrime(31, new Random()).longValue();
		}

		private long[] computeRadixPowers(int n) {
			long[] p = new long[n];
			p[0] = 1;
			for (int i = 1; i < n; i++) {
				p[i] = (R * p[i - 1]) % Q;
			}
			return p;
		}

		private long computeHash(String s) {
			long h = 0;
			for (int i = 0; i < s.length(); i++) {
				h = (h * R + s.charAt(i)) % Q;
			}
			return h;
		}
	}

	/**
	 * Better version
	 */
	public static class Solution2 {

		private long Q;
		private int R;
		private long[] rp;
		private long modInverse;
		private long hashOrigin = 0;
		private long hashReversed = 0;

		public String shortestPalindrome(String s) {

			if (s.length() == 0) return s;

			Q = getRandomPrime();
			R = 256;

			computeHashes(s);
			if (hashOrigin == hashReversed) return s;

			rp = computeRadixPowers(s.length());
			modInverse = BigInteger.valueOf(R).modInverse(BigInteger.valueOf(Q)).longValue();

			StringBuilder ans = new StringBuilder();

			for (int i = 0; hashOrigin != hashReversed; i++) {
				int x = s.length() - 1 - i;
				char c = s.charAt(x);
				hashOrigin = removeLast(hashOrigin, c);
				hashReversed = removeFirst(hashReversed, c, x);
				ans.append(s.charAt(s.length() - 1 - i));
			}

			ans.append(s);
			return ans.toString();
		}

		private long removeLast(long hashOrigin, char c) {
			return ((hashOrigin - c) * modInverse) % Q;
		}

		private long removeFirst(long hashReversed, char c, int power) {
			return (hashReversed - (rp[power] * c) % Q + Q) % Q;
		}

		private long getRandomPrime() {
			return BigInteger.probablePrime(31, new Random()).longValue();
		}

		private long[] computeRadixPowers(int n) {
			long[] p = new long[n];
			p[0] = 1;
			for (int i = 1; i < n; i++) {
				p[i] = (R * p[i - 1]) % Q;
			}
			return p;
		}

		private void computeHashes(String s) {
			for (int i = 0, j = s.length() - 1; i < s.length(); i++, j--) {
				hashOrigin = (hashOrigin * R + s.charAt(i)) % Q;
				hashReversed = (hashReversed * R + s.charAt(j)) % Q;
			}
		}
	}
}