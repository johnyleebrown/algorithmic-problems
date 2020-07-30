package math.numberTheory.primes;

import java.util.Arrays;

/**
 * Print prime numbers <= n
 */
public class Primes
{
	/**
	 * Sieve of Eratosthenes O(N log (log N))
	 */
	public static void printPrimes1(int n)
	{
		// Create a boolean array "prime[0..n]" and initialize
		// all entries it as true. A value in prime[i] will
		// finally be false if i is Not a prime, else true.
		boolean prime[] = new boolean[n + 1];
		Arrays.fill(prime, true);

		for (int p = 2; p * p <= n; p++)
		{
			// If prime[p] is not changed, then it is a prime
			if (prime[p])
				// Update all multiples of p
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
		}

		// Print all prime numbers
		for (int i = 2; i <= n; i++)
			if (prime[i])
				System.out.print(i + " ");
	}

	/**
	 * Sieve of Sundaram
	 */
	public static void printPrimes2(int n)
	{
		// Produce primes smaller than (2*x + 2) for a number
		// given number x. Since we want primes
		// smaller than n, we reduce n to half
		int newN = (n - 2) / 2;

		// This array is used to separate numbers of the
		// form i+j+2ij from others where 1 <= i <= j
		boolean marked[] = new boolean[newN + 1];

		// init all elements as not marked
		Arrays.fill(marked, false);

		// Mark all numbers of the form i + j + 2ij as true where 1 <= i <= j
		for (int i = 1; i <= newN; i++)
			for (int j = i; (i + j + 2 * i * j) <= newN; j++)
				marked[i + j + 2 * i * j] = true;

		// Since 2 is a prime number
		if (n > 2)
		    System.out.print(2 + " ");

		// Print other primes. Remaining primes are of
		// the form 2*i + 1 such that marked[i] is false.
		for (int i = 1; i <= newN; i++)
			if (!marked[i])
				System.out.print(2 * i + 1 + " ");
	}
}
