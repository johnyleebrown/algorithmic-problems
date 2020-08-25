package twoPointers.regular.anagram;

import java.util.ArrayList;
import java.util.List;

/**
 * 438
 *
 * ======
 *
 * Task.
 *
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will
 * not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindAllAnagramsInAString {
	/**
	 * Same code as 567.
	 *
	 * Optimal.
	 */
	public static class Solution1 {
		public List<Integer> findAnagrams(String s2, String s1) {
			List<Integer> ans = new ArrayList<>();

			int n1 = s1.length();
			int n2 = s2.length();
			if (n1 > n2) return ans;
			int[] counts = new int[26];
			int unique = 0;

			for (int i = 0; i < n1; i++) {
				counts[s1.charAt(i) - 'a']++;
				if (counts[s1.charAt(i) - 'a'] == 1) {
					unique++;
				}
			}

			int r = 0;
			int hits = 0;
			for (; r < n1; r++) {
				counts[s2.charAt(r) - 'a']--;
				if (counts[s2.charAt(r) - 'a'] == 0) {
					hits++;
				}
			}

			// maybe we got a hit in first n1 chars of s2
			if (hits == unique) {
				ans.add(0);
			}

			for (int l = 0; r < n2; r++, l++) {
				counts[s2.charAt(r) - 'a']--;
				if (counts[s2.charAt(r) - 'a'] == 0) {
					hits++;
				}
				counts[s2.charAt(l) - 'a']++;
				if (counts[s2.charAt(l) - 'a'] == 1) {
					hits--;
				}
				if (hits == unique) {
					ans.add(l + 1);
				}
			}

			return ans;
		}
	}

	/**
	 * Using the sliding window template.
	 */
	public static class Solution2 {
		public List<Integer> findAnagrams(String s, String p) {
			List<Integer> result = new ArrayList<>();
			if (s == null || s.isEmpty()) {
				return result;
			}

			// getting a map of chars from p
			int[] map = new int[26];
			for (int i = 0; i < p.length(); i++) {
				map[p.charAt(i) - 'a']++;
			}

			int matchedLettersCount = 0;

			// sliding window template
			int l = 0;
			for (int r = 0; r < s.length(); r++) {
				map[s.charAt(r) - 'a']--;
				if (map[s.charAt(r) - 'a'] >= 0) {
					matchedLettersCount++;
				}

				// when ensured all the chars from p are in the window
				while (matchedLettersCount == p.length()) {
					if (r - l + 1 == p.length()) {
						result.add(l);
					}

					map[s.charAt(l) - 'a']++;
					if (map[s.charAt(l) - 'a'] >= 1) {
						matchedLettersCount--;
					}

					l++;
				}
			}

			return result;
		}
	}
}