package math;

/**
 * Determine whether an integer is a palindrome.
 * Do this without extra space.
 */

public class PalindromeNumber {

    // With extra space
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        int[] a = new int[str.length()];
        for (int i = 0; i < str.length(); i++) a[i] = str.charAt(i);
        for (int i = 0; i <= str.length()/2; i++) if (a[i] != a[str.length()-1-i]) return false;
        return true;
    }
}
