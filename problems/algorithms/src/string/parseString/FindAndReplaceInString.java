package string.parseString;

import java.util.Arrays;

/**
 * 833
 */
public class FindAndReplaceInString {
		public static class Solution {
				public String findReplaceString(String baseString, int[] indexes, String[] sources, String[] targets) {
						StringBuilder sb = new StringBuilder();
						int n = baseString.length();
						int[] links = new int[n];
						Arrays.fill(links, -1);
						for (int i = 0; i < indexes.length; i++) {
								links[indexes[i]] = i;
						}
						int baseStringInd = 0;
						while (baseStringInd < n) {
								if (links[baseStringInd] != -1 && isReplaceable(baseStringInd, baseString, sources[links[baseStringInd]])) {
										sb.append(targets[links[baseStringInd]]);
										baseStringInd += sources[links[baseStringInd]].length();
								}
								else {
										sb.append(baseString.charAt(baseStringInd++));
								}
						}
						return sb.toString();
				}

				private boolean isReplaceable(int i, String a, String b) {
						for (int j = 0; j < b.length(); j++) {
								if (a.charAt(i + j) != b.charAt(j)) {
										return false;
								}
						}
						return true;
				}
		}
}