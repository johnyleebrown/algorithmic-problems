package math.numberTheory;

/**
 * 507
 */
public class PerfectNumber {
    /**
     * A perfect number is a positive integer that is equal to the sum of its proper positive divisors, that is, the sum of its positive divisors excluding the number itself
     * Euclid-Euler Theorem
     * (2^(p−1))*((2^p)−1) is an even perfect number whenever 2^p−1 is prime, where p is prime.
     * Prime numbers of the form 2^p−1 are known as Mersenne primes.
     */

    // Time: O(logn) Number of primes will be in order lognum.
    // Space: O(logn) Space used to store primes.
    class Solution {
        public int pn(int p) {
            return (1 << (p - 1)) * ((1 << p) - 1);
        }

        public boolean checkPerfectNumber(int num) {
            int[] primes = new int[]{2, 3, 5, 7, 13, 17, 19, 31};
            for (int prime : primes) if (pn(prime) == num) return true;
            return false;
        }
    }

    // therefore
    class Solution1p5 {
        public boolean checkPerfectNumber(int num) {
            if (num == 6 || num == 28 || num == 496 || num == 8128 || num == 33550336) return true;
            else return false;
        }
    }

    // O(sqrt(n))
    // O(1)
    class Solution2 {
        public boolean checkPerfectNumber(int num) {
            if (num <= 0) return false;
            int sum = 0;
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    sum += i;
                    if (i * i != num) sum += num / i;
                }
            }
            return sum - num == num;
        }
    }
}

