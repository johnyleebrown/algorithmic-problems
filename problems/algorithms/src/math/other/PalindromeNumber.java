package math.other;

/**
 * Determine whether an integer is a palindrome.
 * Do this without extra space.
 *
 * Tip: only positive integers.
 */

public class PalindromeNumber {

    // With extra space
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        int[] a = new int[str.length()];
        for (int i = 0; i < str.length(); i++)
            a[i] = str.charAt(i);
        for (int i = 0; i <= str.length() / 2; i++)
            if (a[i] != a[str.length() - 1 - i])
                return false;
        return true;
    }

    // runtime beats 97%
    public boolean isPalindrome2(int x) {
        if (x >= 0 && x < 10) return true;
        if (x < 0 || x % 10 == 0) return false;
        int n = 0;
        while (x > 0){
            n += x % 10;
            if (n == x) return true;
            x = (x - x % 10)/10;
            if (n == x) return true;
            n *= 10;
        }
        return false;
    }

    // runtime beats 30%
    public boolean isPalindrome3(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int rev = 0;
        while (x>rev){
            rev = rev*10 + x%10;
            x = x/10;
        }
        return (x==rev || x==rev/10);
    }
}
