package twoPointers.regular.anagram;

/**
 * 567
 *
 * ======
 *
 * Task.
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of
 * s1. In other words, one of the first string's permutations is the substring of the second
 * string.
 *
 * ======
 *
 * Source: Leetcode
 */
public class PermutationInString {
	/**
	 * Since we know the window size is fixed - we will be moving that window and determining
	 * what is and what is not in that window.
	 */
	public static class Solution {
		public boolean checkInclusion(String s1, String s2) {

			int n1 = s1.length();
			int n2 = s2.length();
			if (n1 > n2) return false;
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
				return true;
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
					return true;
				}
			}

			return false;
		}
	}
}