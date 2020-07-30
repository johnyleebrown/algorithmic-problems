package math.numberTheory.primes;

/**
 * 867
 *
 * Prime Palindrome
 *
 * Find the smallest prime palindrome greater than or equal to N. Recall that a
 * number is prime if it's only divisors are 1 and itself, and it is greater
 * than 1. For example, 2,3,5,7,11 and 13 are primes. Recall that a number is a
 * palindrome if it reads the same from left to right as it does from right to
 * left. For example, 12321 is a palindrome.
 */
public class PrimePalindrome {
	/**
	 * Brute force
	 */
	class Solution1 {
		public int primePalindrome(int N) {
			if (N == 1 || N == 2) return 2;
			int x = N;
			while (true) {
				if (checkPrime(x) && checkPalindrome(x)) return x;
				x++;
			}
		}

		public boolean checkPrime(int num) {
			int half = num / 2;
			for (int i = 2; i <= half; i++) {
				if (num % i == 0) {
					return false;
				}
			}
			return true;
		}

		public boolean checkPalindrome(int num) {
			String s = Integer.toString(num);
			for (int i = 0, j = s.length() - 1; i <= s.length() / 2; i++, j--) {
				if (s.charAt(i) != s.charAt(j)) {
					return false;
				}
			}
			return true;
		}
	}

	class Solution3 {
		/**
		 * optimized O(1), O(n)
		 */
		public int primePalindrome(int N) {
			while (true) {
				if (N == reverse(N) && isPrime(N)) return N;
				N++;
				if (10_000_000 < N && N < 100_000_000) N = 100_000_000;
			}
		}

		public boolean isPrime(int N) {
			if (N < 2) return false;
			int R = (int) Math.sqrt(N);
			for (int d = 2; d <= R; ++d)
				if (N % d == 0) return false;
			return true;
		}

		public int reverse(int N) {
			int ans = 0;
			while (N > 0) {
				ans = 10 * ans + (N % 10);
				N /= 10;
			}
			return ans;
		}
	}
}


