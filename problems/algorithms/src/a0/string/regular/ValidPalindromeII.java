package a0.string.regular;

/**
 * 680
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 */
public class ValidPalindromeII {

    // Time Complexity: O(N) where N is the length of the string.
    // Each of two checks of whether some substring is a palindrome is O(N).
    // Space Complexity: O(1) additional complexity. Only pointers were stored in memory.
    public boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++)
            if (s.charAt(k) != s.charAt(j - k + i)) return false;
        return true;
    }
    public boolean validPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 - i;
                return (isPalindromeRange(s, i+1, j) || isPalindromeRange(s, i, j-1));
            }
        }
        return true;
    }
}
