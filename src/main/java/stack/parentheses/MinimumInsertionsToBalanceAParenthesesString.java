package stack.parentheses;

/**
 * 1541. Minimum Insertions to Balance a Parentheses String
 * https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
 */
public class MinimumInsertionsToBalanceAParenthesesString {
  /**
   * Taking idea from the similar problem and expanding on it. We could have closing brackets from
   * the start, opening from the end, and closing in the middle. In all other cases we will have
   * possible correct combination.
   */
  public static class Solution0 {
    int ans = 0;

    public int minInsertions(String s) {
      int n = s.length();

      int left = 0, right = 0;
      int k = 0;
      while (k < n) {
        char c = s.charAt(k);
        if (c == ')') {
          if (left > 0) {
            if (k + 1 < n && s.charAt(k + 1) == ')') {
              left--;
              k += 2;
            } else if (k + 1 < n && s.charAt(k + 1) == '(') {
              left--;
              ans++;
              k++;
            } else if (k + 1 >= n) {
              left--;
              ans++;
              k++;
            }
          }
          // closing brackets in the middle or in the beginning
          else {
            k = countClosing(k, s);
          }
        } else if (c == '(') {
          left++;
          k++;
        }
      }

      return ans + left * 2;
    }

    public int countClosing(int i, String s) {
      int countClosing = 0;
      while (i < s.length() && s.charAt(i) != '(') {
        countClosing++;
        i++;
      }
      ans += (countClosing / 2) + (countClosing % 2 == 1 ? 2 : 0);
      return i;
    }
  }
}
