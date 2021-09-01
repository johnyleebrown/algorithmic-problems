import java.math.BigInteger;
import java.util.*;

/**
 * 187. Repeated DNA Sequences
 * https://leetcode.com/problems/repeated-dna-sequences/
 */
public class RepeatedDNASequences {

	public static class Solution2 {

		private long R = 4; // radix
		private long Q = getRandomPrime();
		private long PR; // power R

		public List<String> findRepeatedDnaSequences(String s) {
			List<String> ans = new ArrayList<>();
			if (s.length() <= 10) {
				return ans;
			}

			PR = getPowerR();
			Map<Long, Boolean> map = new HashMap<>();
			long h = getHash(s);
			map.put(h, null);

			for (int l = 0, r = 10; r < s.length(); l++, r++) {
				h = moveWindow(h, s.charAt(l), s.charAt(r));
				if (map.containsKey(h)) {
					if (map.get(h) == null) {
						map.put(h, true);
						ans.add(s.substring(l + 1, l + 11));
					}
				} else {
					map.put(h, null);
				}
			}

			return ans;
		}

		private long getPowerR() {
			long powR = 1;
			for (int i = 0; i < 9; i++) {
				powR = (powR * R) % Q;
			}
			return powR;
		}

		private long getRandomPrime() {
			return BigInteger.probablePrime(31, new Random()).longValue();
		}

		private long addLast(long h, char c) {
			return ((h * R) % Q + hash(c)) % Q;
		}

		private long removeFirst(long h, char c) {
			return (Q + h - (hash(c) * PR) % Q) % Q;
		}

		private long moveWindow(long h, char first, char last) {
			h = removeFirst(h, first);
			h = addLast(h, last);
			return h;
		}

		private long getHash(String s) {
			long h = 0;
			for (int i = 0; i < 10; i++) {
				h = (h * R + hash(s.charAt(i))) % Q;
			}
			return h;
		}

		private int hash(char c) {
			switch (c) {
				case 'C':
					return 1;
				case 'G':
					return 2;
				case 'T':
					return 3;
				case 'A':
				default:
					return 0;
			}
		}
	}

	/**
	 * Regular
	 */
	public static class Solution1 {

		public List<String> findRepeatedDnaSequences(String s) {
			List<String> ans = new ArrayList<>();
			if (s.length() <= 10) return ans;
			Set<String> set = new HashSet<>();
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (; i < 10; i++) {
				sb.append(s.charAt(i));
			}
			Set<String> ansSet = new HashSet<>();
			set.add(sb.toString());
			for (; i < s.length(); i++) {
				sb.append(s.charAt(i));
				sb.deleteCharAt(0);
				String cur = sb.toString();
				if (!set.add(cur)) {
					ansSet.add(cur);
				}
			}
			return new ArrayList<>(ansSet);
		}
	}
}
