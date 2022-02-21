package string.hash;

import java.math.BigInteger;
import java.util.Random;

/**
 * 28
 */
public class ImplementStrStr {

	public static class Solution {

		static final int R = 256;
		static long RMQ;
		static long Q;
		static int m;
		static String t;

		public int strStr(String t, String p) {
			m = p.length();
			if (m > t.length()) return -1;
			this.t = t;
			// get rand prime mod
			Q = getRandomPrime();
			// precompute pattern hash
			long patHash = hash(p, m);
			// precompute (r^m-1)%Q
			RMQ = 1;
			for (int i = 1; i < m; i++) {
				RMQ = (R * RMQ) % Q;
			}
			// rolling hash
			long h = hash(t, m);
			// base case
			if (h == patHash && check(0, p)) return 0;
			for (int i = m; i < t.length(); i++) {
				// slide
				h = removeFirst(h, i);
				h = addLast(h, i);
				// check
				int offset = i - m + 1;
				if (h == patHash && check(offset, p)) return offset;
			}
			return -1;
		}

		// (curHash+Q-(charAt(i-m)*RMQ)%Q)%Q
		private long removeFirst(long h, int i) {
			return (h + Q - RMQ * t.charAt(i - m) % Q) % Q;
		}

		private long addLast(long h, int i) {
			return (h * R + t.charAt(i)) % Q;
		}

		private long hash(String s, int n) {
			long h = 0;
			for (int i = 0; i < n; i++) {
				h = (h * R + s.charAt(i)) % Q;
			}
			return h;
		}

		private boolean check(int offset, String p) {
			return lasVegasCheck(offset, p);
		}

		// las vegas version of RK - check if substrings chars are the same
		private boolean lasVegasCheck(int offset, String p) {
			for (int i = 0; i < m; i++) {
				if (p.charAt(i) != t.charAt(i + offset)) {
					return false;
				}
			}
			return true;
		}

		private long getRandomPrime() {
			BigInteger x = BigInteger.probablePrime(31, new Random());
			return x.longValue();
		}
	}

	public static class Solution2 {

		public int strStr2(String haystack, String needle) {
			int l1 = haystack.length();
			int l2 = needle.length();
			if (l1 < l2) return -1;
			for (int i = 0; i <= haystack.length() - l2; i++) {
				if (haystack.substring(i, i + l2).equals(needle))
					return i;
			}
			return -1;
		}
	}
}
