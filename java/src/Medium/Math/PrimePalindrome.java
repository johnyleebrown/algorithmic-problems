package Medium.Math;

/**
 * 867
 *
 * Prime Palindrome
 *
 * Find the smallest prime palindrome greater than or equal to N.
 * Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.
 * For example, 2,3,5,7,11 and 13 are primes.
 * Recall that a number is a palindrome if it reads the same from left to right as it does from right to left.
 * For example, 12321 is a palindrome.
 */
public class PrimePalindrome {
    /**
     * Brute force
     */
    public int primePalindrome(int N) {
        if (N == 1 || N == 2) return 2;
        int x = N;
        while (true) {
            if (checkPrime(x) && checkPalindrome(x)) return x;
            x++;
        }
    }

    public static boolean checkPrime(int num){
        int half = num/2;
        for(int i=2; i<=half; i++){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }

    public static boolean checkPalindrome(int num){
        String s = Integer.toString(num);
        for(int i=0, j=s.length()-1; i<=s.length()/2; i++, j--){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }
}


