package math.numberTheory;

import java.util.ArrayList;

/**
 * Unknown_12
 *
 * ======
 *
 * Task.
 *
 * Given three integers a, b, n .Your task is to print number of numbers between
 * a and b including them also which have n-divisors. A number is called
 * n-divisor if it has total n divisors including 1 and itself.
 */
public class FindNumbersWithNDivisorsInAGivenRange
{
	public static class Solution
	{
		public int countNDivisors(int a, int b, int n)
		{
			int x = (int)Math.sqrt(b) + 1;

			// primes[i] = true if i is a prime number
			boolean[] primes=new boolean[x+1];

			// initialising each number as prime
			sieve(primes, x);

			return nDivisors(primes, x, a, b, n);
		}

		private void sieve(boolean[] primes, int x)
		{
			primes[1] = true;

			// if a number is prime mark all its multiples
			// as non prime
			for (int i=2; i*i <= x; i++)
			{
				if (primes[i] == false)
				{
					for (int j=2; j*i <= x; j++)
						primes[i*j] = true;
				}
			}
		}

		private int nDivisors(boolean[] primes, int x, int a, int b, int n)
		{
			// result holds number of numbers having n divisors
			int result = 0;

			// vector to hold all the prime numbers between 1
			// ans sqrt(b)
			ArrayList<Integer> v=new ArrayList<>();
			for (int i = 2; i <= x; i++)
				if (primes[i] == false)
					v.add(i);

			// Traversing all numbers in given range
			for (int i=a; i<=b; i++)
			{
				// initialising temp as i
				int temp = i;

				// total holds the number of divisors of i
				int total = 1;
				int j = 0;

				// we need to use that prime numbers that
				// are less than equal to sqrt(temp)
				for (int k = v.get(j); k*k <= temp; k = v.get(++j))
				{
					// holds the exponent of k in prime
					// factorization of i
					int count = 0;

					// repeatedly divide temp by k till it is
					// divisible and accordingly increase count
					while (temp%k == 0)
					{
						count++;
						temp = temp/k;
					}

					// using the formula no.of divisors =
					// (e1+1)*(e2+1)....
					total = total*(count+1);

				}

				// if temp is not equal to 1 then there is
				// prime number in prime factorization of i
				// greater than sqrt(i)
				if (temp != 1)
					total = total*2;

				// if i is a ndvisor number increase result
				if (total == n)
					result++;
			}
			return result;
		}
	}
}
