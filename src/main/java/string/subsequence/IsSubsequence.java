package string.subsequence;

/**
 * 392. Is Subsequence
 *
 * <p>======
 *
 * <p>Given two strings s and t, check if s is a subsequence of t.
 *
 * <p>A subsequence of a string is a new string that is formed from the original string by deleting
 * some (can be none) of the characters without disturbing the relative positions of the remaining
 * characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * <p>Example 1:
 *
 * <p>Input: s = "abc", t = "ahbgdc" Output: true Example 2:
 *
 * <p>Input: s = "axc", t = "ahbgdc" Output: false
 *
 * <p>Constraints:
 *
 * <p>0 <= s.length <= 100 0 <= t.length <= 104 s and t consist only of lowercase English letters.
 *
 * <p>Follow up: If there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want
 * to check one by one to see if t has its subsequence. In this scenario, how would you change your
 * code?
 *
 * <p>======
 *
 * <p>https://leetcode.com/problems/is-subsequence/
 */
public class IsSubsequence {
  /**
   * SF.
   */
  public static class Solution {
    public boolean isSubsequence(String s, String t) {
      if (s.length() == 0) return true;
      int indexS = 0, indexT = 0;
      while (indexT < t.length()) {
        if (t.charAt(indexT) == s.charAt(indexS)) {
          indexS++;
          if (indexS == s.length()) return true;
        }
        indexT++;
      }
      return false;
    }
  }
}
