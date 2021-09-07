package twoPointers.regular.hash;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 187. Repeated DNA Sequences
 * https://leetcode.com/problems/repeated-dna-sequences/
 */
public class RepeatedDNASequences {

	/**
	 * same idea as solution3 but we add to the end, remove from the start of the integer (0th bit)
	 */
	public static class Solution4 {
		public List<String> findRepeatedDnaSequences(String s) {
			if (s.length() <= 10) return new ArrayList<>();
			Character[] ca = new Character[]{'A', 'C', 'G', 'T'};
			Map<Character, Integer> charMap = IntStream
			.range(0, ca.length)
			.boxed()
			.collect(Collectors.toMap(i -> ca[i], i -> i));
			Set<Integer> seen = new HashSet<>();
			Set<String> res = new HashSet<>();
			final int windowLength = 10;
			final int bitmaskLength = windowLength * 2;
			int bitmask = 0;
			for (int i = 0; i < windowLength; i++) {
				int val = charMap.get(s.charAt(i));
				val <<= (i * 2);
				bitmask |= val;
			}
			seen.add(bitmask);
			for (int l = 0, r = windowLength; r < s.length(); l++, r++) {

				// remove last character
				// set last 2 bits to 0
				bitmask >>= 2;

				// add first character
				int rVal = charMap.get(s.charAt(r));
				rVal <<= (bitmaskLength - 2);
				bitmask |= rVal;

				if (!seen.add(bitmask)) {
					res.add(s.substring(l + 1, r + 1));
				}
			}
			return new ArrayList<>(res);
		}
	}

	/**
	 * sliding window + bitmasks
	 * in this case the start of the window is in its end
	 */
	public static class Solution3 {
		public List<String> findRepeatedDnaSequences(String s) {
			if (s.length() <= 10) return new ArrayList<>();

			Character[] ca = new Character[]{'A', 'C', 'G', 'T'};
			Map<Character, Integer> charMap = IntStream.range(0, ca.length).boxed().collect(Collectors.toMap(i -> ca[i], i -> i));

			Set<Integer> seen = new HashSet<>();
			Set<String> res = new HashSet<>();
			final int windowLength = 10;
			// each letter is encoded with 2 bits
			// 0 -> 00 3 -> 11
			final int bitmaskLength = windowLength * 2;

			int bitmask = 0;

			// precompute window bitmask
			for (int i = 0; i < windowLength; i++) {
				int val = charMap.get(s.charAt(i));
				// set last 2 bits - i*2, i*2-1
				bitmask <<= 2;
				bitmask |= val;
			}
			seen.add(bitmask);

			// number to do unsetting of the last 2 bits
			int unset = ~(3 << (bitmaskLength));
			for (int l = 0, r = windowLength; r < s.length(); l++, r++) {

				// add first character
				int rVal = charMap.get(s.charAt(r));
				bitmask <<= 2;
				bitmask |= rVal;

				// remove last character
				// set last 2 bits to 0
				bitmask &= unset;

				if (!seen.add(bitmask)) {
					res.add(s.substring(l + 1, r + 1));
				}
			}

			return new ArrayList<>(res);
		}
	}

	/**
	 * Or just use set and set for answers
	 */
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
