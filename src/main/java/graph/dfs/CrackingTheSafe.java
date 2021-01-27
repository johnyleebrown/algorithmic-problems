package graph.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	public static void main(String[] args) {
		Solution s = new Solution();
		s.crackSafe(3, 9);
	}

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
}