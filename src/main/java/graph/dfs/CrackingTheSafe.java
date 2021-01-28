package graph.dfs;

import java.util.*;

/**
 * 753. Cracking the Safe
 *
 * ======
 *
 * There is a box protected by a password. The password is a sequence of n digits where each digit
 * can be one of the first k digits 0, 1, ..., k-1.
 *
 * While entering a password, the last n digits entered will automatically be matched against the
 * correct password.
 *
 * For example, assuming the correct password is "345", if you type "012345", the box will open
 * because the correct password matches the suffix of the entered password.
 *
 * Return any password of minimum length that is guaranteed to open the box at some point of
 * entering it.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * Example 2:
 *
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 *
 *
 * Note:
 *
 * n will be in the range [1, 4].
 * k will be in the range [1, 10].
 * k^n will be at most 4096.
 *
 * ======
 *
 * https://leetcode.com/problems/cracking-the-safe/
 */
public class CrackingTheSafe {
	/*
	TODO
	Guaranteed to open the box - permutation of all combinations
	How to make a shortest continuous string - combine the permutations
	All permutations of k^n has an overlap of n-1 (000 and 001 overlap 2 numbers = 00)
	So we need an overlap of minimum length which is basically n numbers of first permutation
	and + 1 last number of each permutation, with the size n + k^n - 1
	We can create a graph where each node is a permutation, nodes are connected if they have an
	overlap of n-1 (the one we need)
	In other words an overlap is when a suffix of one string equals to the prefix of another, of
	size n-1 in our case
	After that what we need to do is to find such path in a graph that will result in the answer
	This path should go through each node once because we cant stop at node 111 and continue with
	 000, these nodes are not overlapping, so we wont have a minimum password
	Such path that goes through each node once is called Hamiltonian path, it is an NP complete
	problem but one of the solutions is a brute force by doing dfs
	 */

	/**
	 * @formatter:off
     * $INSERT_EXPLANATION.
     * @formatter:on
	 */
	public static class Solution {
		int vertexCount;
		StringBuilder ans;

		public String crackSafe(int n, int k) {
			if (n == 1) {
				char[] c = new char[k];
				for (int i = 0; i < k; i++) c[i] = (char) (i + '0');
				return String.valueOf(c);
			}
			Map<String, String> combToSuffix = new HashMap<>();
			Map<String, Set<String>> suffixToCombs = new HashMap<>();
			gen(0, k, n, new char[n], combToSuffix, suffixToCombs);
			vertexCount = combToSuffix.size();
			dfs(combToSuffix.entrySet().iterator().next().getKey(), combToSuffix, suffixToCombs, new HashSet<>());
			return ans.toString();
		}

		private void gen(int j, int k, int n,
		                 char[] comb,
		                 Map<String, String> combToSuffix, Map<String, Set<String>> suffixToCombs) {
			if (j == n) {
				String c = String.valueOf(comb);
				combToSuffix.put(c, String.valueOf(comb, 1, n - 1));
				String prefix = String.valueOf(comb, 0, n - 1);
				suffixToCombs.putIfAbsent(prefix, new HashSet<>());
				suffixToCombs.get(prefix).add(c);
			} else {
				for (int i = 0; i < k; i++) {
					comb[j] = (char) (i + '0');
					gen(j + 1, k, n, comb, combToSuffix, suffixToCombs);
				}
			}
		}

		private boolean dfs(String cur, Map<String, String> combToSuffix, Map<String, Set<String>> suffixToCombs,
		                    Set<String> seen) {

			if (seen.contains(cur)) {
				return false;
			}

			vertexCount--;
			seen.add(cur);
			updateAnswer(cur);

			String suf = combToSuffix.get(cur);
			// count good vertices, if zero then we have nowhere to go
			int count = suffixToCombs.get(suf).size();

			for (String comb : suffixToCombs.get(suf)) {

				// dont count itself
				if (cur.equals(comb)) {
					count--;
					continue;
				}

				boolean ret = dfs(comb, combToSuffix, suffixToCombs, seen);
				if (!ret) {
					count--;
				}
			}

			// can't go further
			if (count == 0 && vertexCount != 0) {
				System.out.println(cur);
				ans.deleteCharAt(ans.length() - 1);
				seen.remove(cur);
				vertexCount++;
				return false;
			}

			return true;
		}

		private void updateAnswer(String cur) {
			// first time = fill n symbols
			if (ans == null) {
				ans = new StringBuilder();
				for (int i = 0; i < cur.length(); i++) ans.append(cur.charAt(i));
			}
			// all other times just take the last symbol
			else {
				ans.append(cur.charAt(cur.length() - 1));
			}
		}
	}

	/**
	 * Cleaner
	 */
	public static class Solution2 {

		public String crackSafe(int n, int k) {
			if (n == 1) {
				return computeForK(k);
			}

			Map<String, String> combToSuffix = new HashMap<>();
			Map<String, List<String>> prefixToCombs = new HashMap<>();
			gen(0, k, n, new char[n], combToSuffix, prefixToCombs);

			StringBuilder ans = new StringBuilder();
			String first = combToSuffix.entrySet().iterator().next().getKey();
			dfs(first, new HashSet<>(), combToSuffix.size(), ans, combToSuffix, prefixToCombs);

			return ans.toString();
		}

		private String computeForK(int k) {
			char[] c = new char[k];
			for (int i = 0; i < k; i++) c[i] = (char) (i + '0');
			return String.valueOf(c);
		}

		private void gen(int j, int k, int n, char[] comb,
		                 Map<String, String> combToSuffix,
		                 Map<String, List<String>> prefixToCombs) {
			if (j == n) {
				String c = String.valueOf(comb);
				String suffix = String.valueOf(comb, 1, n - 1);
				String prefix = String.valueOf(comb, 0, n - 1);

				combToSuffix.put(c, suffix);
				prefixToCombs.putIfAbsent(prefix, new ArrayList<>());
				prefixToCombs.get(prefix).add(c);
			} else {
				for (int i = 0; i < k; i++) {
					comb[j] = (char) (i + '0');
					gen(j + 1, k, n, comb, combToSuffix, prefixToCombs);
				}
			}
		}

		private boolean dfs(String cur, Set<String> seen, int vertexCount, StringBuilder ans,
		                    Map<String, String> combToSuffix,
		                    Map<String, List<String>> prefixToCombs) {

			updateAnswer(ans, cur);
			seen.add(cur);

			if (vertexCount == 1) {
				return true;
			}

			String suf = combToSuffix.get(cur);

			for (String comb : prefixToCombs.get(suf)) {
				if (!seen.contains(comb)) {
					if (dfs(comb, seen, vertexCount - 1, ans, combToSuffix, prefixToCombs)) {
						return true;
					}
				}
			}

			seen.remove(cur);
			ans.deleteCharAt(ans.length() - 1);

			return false;
		}

		private void updateAnswer(StringBuilder ans, String cur) {
			// first time = fill n symbols
			if (ans.length() == 0) {
				for (int i = 0; i < cur.length(); i++) ans.append(cur.charAt(i));
			}
			// all other times just take the last symbol
			else {
				ans.append(cur.charAt(cur.length() - 1));
			}
		}
	}

}