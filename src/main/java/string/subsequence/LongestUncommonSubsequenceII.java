package string.subsequence;

import java.util.Arrays;

/**
 * 522. Longest Uncommon Subsequence II
 *
 * <p>======
 *
 * <p>Given a list of strings, you need to find the longest uncommon subsequence among them. The
 * longest uncommon subsequence is defined as the longest subsequence of one of these strings and
 * this subsequence should not be any subsequence of the other strings.
 *
 * <p>A subsequence is a sequence that can be derived from one sequence by deleting some characters
 * without changing the order of the remaining elements. Trivially, any string is a subsequence of
 * itself and an empty string is a subsequence of any string.
 *
 * <p>The input will be a list of strings, and the output needs to be the length of the longest
 * uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.
 *
 * <p>Example 1: Input: "aba", "cdc", "eae" Output: 3 Note:
 *
 * <p>All the given strings' lengths will not exceed 10. The length of the given list will be in the
 * range of [2, 50].
 *
 * <p>======
 *
 * <p>https://leetcode.com/problems/longest-uncommon-subsequence-ii/
 */
public class LongestUncommonSubsequenceII {
  /**
   * TODO
   */
  public static class Solution {
    public int findLUSlength(String[] ar) {
      Arrays.sort(ar, (a, b) -> b.length() - a.length());
      for (int i = 0; i < ar.length; i++) {
        boolean ans = true;
        for (int j = 0; j < ar.length; j++) {
          if (i != j && ar[j].length() >= ar[i].length() && isSubsequence(ar[i], ar[j])) {
            ans = false;
            break;
          }
        }
        if (ans) {
          return ar[i].length();
        }
      }
      return -1;
    }

    private boolean isSubsequence(String a, String b) {
      int i = 0;
      for (int j = 0; j < b.length(); j++) {
        if (a.charAt(i) == b.charAt(j)) {
          i++;
          if (i == a.length()) {
            return true;
          }
        }
      }
      return i == a.length();
    }
  }
}
