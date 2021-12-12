package regular.string;

/**
 * 680
 */
public class ValidPalindromeII {

  // Time Complexity: O(N) where N is the length of the string.
  // Each of two checks of whether some substring is a palindrome is O(N).
  // Space Complexity: O(1) additional complexity. Only pointers were stored in memory.
  public boolean isPalindromeRange(String s, int i, int j) {
    for (int k = i; k <= i + (j - i) / 2; k++) {
      if (s.charAt(k) != s.charAt(j - k + i)) return false;
    }
    return true;
  }

  public boolean validPalindrome(String s) {
    for (int i = 0; i < s.length() / 2; i++) {
      if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
        int j = s.length() - 1 - i;
        return (isPalindromeRange(s, i + 1, j) || isPalindromeRange(s, i, j - 1));
      }
    }
    return true;
  }

  public static class Solution0 {

    public boolean validPalindrome(String s) {
      int l = -1, r = s.length();
      while (++l < --r) {
        if (s.charAt(l) != s.charAt(r))
          return isPalindromic(s, l, r + 1) || isPalindromic(s, l - 1, r);
      }
      return true;
    }

    public boolean isPalindromic(String s, int l, int r) {
      while (++l < --r) {
        if (s.charAt(l) != s.charAt(r)) return false;
      }
      return true;
    }
  }

  /**
   * Slow and not necessary - if we already see the difference - we don't care if there
   * is another one since we are asked to look forjust 1 diff.
   */
  public static class Solution {

    public boolean validPalindrome(String s) {
      return helper(s, 0, s.length() - 1, 0) <= 1;
    }

    private int helper(String s, int i, int j, int x) {
      if (i > j) {
        return 0;
      }
      if (x > 1) return 0;
      if (s.charAt(i) != s.charAt(j)) {
        return 1 + Math.min(helper(s, i + 1, j, x + 1),
            helper(s, i, j - 1, x + 1));
      }
      return helper(s, i + 1, j - 1, x);
    }
  }
}
