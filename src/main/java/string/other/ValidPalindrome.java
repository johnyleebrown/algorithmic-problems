package string.other;

/**
 * 125
 */

public class ValidPalindrome {

  // beats 1%
  public boolean isPalindrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      while (i < j && !Character.toString(s.charAt(i)).matches("[a-zA-Z0-9]")) {
        i++;
      }
      while (i < j && !Character.toString(s.charAt(j)).matches("[a-zA-Z0-9]")) {
        j--;
      }
      if (Character.toUpperCase(s.charAt(i)) != Character.toUpperCase(s.charAt(j)))
        return false;
    }
    return true;
  }

  // beats 7%
  public boolean isPalindrome2(String s) {
    String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    String rev = new StringBuffer(actual).reverse().toString();
    return actual.equals(rev);
  }

  // beats 87%
  public boolean isPalindrome3(String s) {
    int start = 0, end = s.length() - 1;
    while (start < end) {
      char a = 0, b = 0;
      while (start < end) {
        a = s.charAt(start);
        if (!Character.isLetterOrDigit(a)) start++;
        else break;
      }
      while (start < end) {
        b = s.charAt(end);
        if (!Character.isLetterOrDigit(b)) end--;
        else break;
      }
      if (start < end && Character.toLowerCase(a) != Character.toLowerCase(b))
        return false;
      start++;
      end--;
    }
    return true;
  }

  public static class Solution {

    public boolean isPalindrome(String s) {
      int i = 0, j = s.length() - 1;
      while (i <= j) {
        while (i <= j && !good(s.charAt(i))) {
          i++;
        }
        while (i <= j && !good(s.charAt(j))) {
          j--;
        }
        if (i <= j &&
            Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--)))
          return false;
      }
      return true;
    }

    private boolean good(char c) {
      return (c >= 'a' && c <= 'z')
             || (c >= 'A' && c <= 'Z')
             || (c >= '0' && c <= '9');
    }
  }
}
