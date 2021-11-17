package string.parseString.other;

/**
 * 65. Valid Number
 *
 * https://leetcode.com/problems/valid-number/
 */
public class ValidNumber {

  /**
   * SF
   *
   * Find conditions that we can rely on and expand from there.
   */
  public static class Solution {

    public boolean isNumber(String s) {
      boolean containsE = false;
      int ePosition = -1;
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (!isValidChar(c)) {
          return false;
        }
        if (c == 'e' || c == 'E') {
          if (containsE) {
            return false;
          }
          containsE = true;
          ePosition = i;
          if (ePosition == 0 || ePosition == s.length() - 1) {
            return false;
          }
        }
      }
      if (containsE) {
        return isValidLeftToE(s, 0, ePosition - 1) && isValidRightToE(s,
            ePosition + 1,
            s.length() - 1);
      } else {
        return isValidLeftToE(s, 0, s.length() - 1);
      }
    }

    private boolean isValidLeftToE(String s, int l, int r) {
      boolean containsDot = false;
      int dotPos = -1;
      for (int i = l; i <= r; i++) {
        char c = s.charAt(i);
        if (c == '.') {
          if (containsDot) {
            return false;
          }
          containsDot = true;
          dotPos = i;
          if (noDecimalsLeftRight(s, dotPos, l, r)) {
            return false;
          }
        }
      }
      if (containsDot) {
        return isValidLeftToDot(s, l, dotPos - 1) && isValidRightToDot(s,
            dotPos + 1, r);
      } else {
        return isValidRightToE(s, l, r);
      }
    }

    private boolean noDecimalsLeftRight(String s, int dotPos, int l, int r) {
      return (dotPos == l || !isDecimal(s, dotPos - 1))
          && (dotPos == r || !isDecimal(s, dotPos + 1));
    }

    private boolean isDecimal(String s, int i) {
      char c = s.charAt(i);
      return c >= '0' && c <= '9';
    }

    private boolean isValidLeftToDot(String s, int l, int r) {
      if (isSign(s, l)) {
        l++;
      }
      for (int i = l; i <= r; i++) {
        if (isSign(s, i)) {
          return false;
        }
        if (!isDecimal(s, i)) {
          return false;
        }
      }
      return true;
    }

    private boolean isSign(String s, int i) {
      char c = s.charAt(i);
      return c == '-' || c == '+';
    }

    private boolean isValidRightToDot(String s, int l, int r) {
      for (int i = l; i <= r; i++) {
        if (!isDecimal(s, i)) {
          return false;
        }
      }
      return true;
    }

    private boolean isValidRightToE(String s, int l, int r) {
      if (isSign(s, l)) {
        if (r - l == 0) {
          return false;
        }
        l++;
      }
      for (int i = l; i <= r; i++) {
        if (isSign(s, i)) {
          return false;
        }
        if (!isDecimal(s, i)) {
          return false;
        }
      }
      return true;
    }

    private boolean isValidChar(char c) {
      return c == '+' || c == '-' || c == '.' || (c >= '0' && c <= '9')
          || c == 'e' || c == 'E';
    }
  }
}