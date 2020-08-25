package backtracking.palindrome;

import java.util.*;

/**
 * 267
 *
 * ======
 *
 * Task.
 *
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an
 * empty list if no palindromic permutation could be form.
 *
 * ======
 *
 * Source: Leetcode
 */
public class PalindromePermutationII {
	/**
	 * so if we have an odd count char
	 * we should pick it up and decrease counter in that count
	 * basically the construction of palindome is a permutation of
	 * half of all chars concated with itself with that special
	 * char in the middle
	 *
	 * - we only need half of each char bucket because we want to build only half os permutation
	 * string
	 */
	public static class Solution {
		public List<String> generatePalindromes(String s) {

			// edge cases
			if (s == null) return new ArrayList<>();
			int n = s.length();
			if (s.isEmpty()) return new ArrayList<>();

			// Part I
			// get all chars that we are going to use to
			// construct half of permutation string
			char[] halfOfChars = new char[n / 2];
			int[] counts = new int[256];
			for (int i = 0; i < n; i++) {
				counts[s.charAt(i)]++;
			}

			// Part II
			// check if we can form an answer
			// palindrome can happen only if all char counts are even
			// or only 1 char count is odd

			// index of odd char count, or -1 if no odd
			int odd = -1;
			// for special case
			int uniqueChars = 0;
			// flag to check if we have > 1 odd
			boolean haveOdd = false;
			// index for halfOfChars filling
			int j = 0;

			for (int i = 0; i < 256; i++) {

				// dont check empty buckets
				if (counts[i] == 0) continue;
				if (counts[i] % 2 != 0) {
					if (haveOdd) {
						// 2nd odd - cant form
						return new ArrayList<>();
					}
					haveOdd = true;
					// found our odd
					odd = i;
				}

				uniqueChars++;
				// we need only half of bucket
				int x = counts[i] / 2;
				char curChar = getChar(i);
				while (--x >= 0) {
					halfOfChars[j++] = curChar;
				}
			}

			// edge case
			if (uniqueChars == 1) return Arrays.asList(s);

			char[] ans = new char[n];

			// set in advance our special character from the odd bucket
			if (odd != -1) {
				ans[halfOfChars.length] = getChar(odd);
			}

			Set<String> set = new HashSet<>();
			gen(0, new boolean[halfOfChars.length], set, odd, halfOfChars, ans);
			return new LinkedList<>(set);
		}

		private void gen(int curInd, boolean[] seen, Set<String> set, int odd, char[] halfOfChars, char[] ans) {
			if (curInd == halfOfChars.length) {
				// index for reverse
				int j = curInd - 1;
				// if we have odd case - it is already in the middle, so move
				if (odd != -1) {
					curInd++;
				}
				// construct reverse
				while (curInd < ans.length) {
					ans[curInd++] = ans[j--];
				}
				set.add(String.valueOf(ans));
			} else {
				for (int i = 0; i < halfOfChars.length; i++) {
					if (seen[i]) continue;
					seen[i] = true;
					ans[curInd] = halfOfChars[i];
					gen(curInd + 1, seen, set, odd, halfOfChars, ans);
					seen[i] = false;
				}
			}
		}

		private char getChar(int i) {
			return (char) i;
		}
	}
}