package string.other;

/**
 * 680
 */
public class ValidPalindromeII {

  /**
   * Iterative
   */
  public static class Solution {

    public boolean validPalindrome(String s) {
      int l = 0, r = s.length() - 1;
      while (l < r) {
        if (s.charAt(l) != s.charAt(r)) {
          return helper(l + 1, r, s) || helper(l, r - 1, s);
        }
        l++;
        r--;
      }
      return true;
    }

    private boolean helper(int l, int r, String s) {
      while (l < r) {
        if (s.charAt(l) != s.charAt(r)) {
          return false;
        }
        l++;
        r--;
      }
      return true;
    }
  }

  /**
   * Recursive
   */
  public static class Solution2 {

    public boolean validPalindrome(String s) {
      return helper(0, s.length() - 1, s, 0);
    }

    private boolean helper(int l, int r, String s, int c) {
      while (l < r) {
        if (s.charAt(l) != s.charAt(r)) {
          if (c == 1) return false;
          return helper(l + 1, r, s, c + 1) || helper(l, r - 1, s, c + 1);
        }
        l++;
        r--;
      }
      return true;
    }
  }

  public static class SolutionOther {

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
  }
}
