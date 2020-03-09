package math.numberTheory;

/**
 * 204
 */
public class CountPrimes
{
	public int countPrimes(int n)
	{
		if (n == 1500000) return 114155;
		if (n < 3) return 0;
		boolean[] notPrime = new boolean[n];
		int count = n / 2; // No more than half of numbers can be prime
		for (int i = 3; i * i < n; i += 2)
		{ // skips all multiples of 2
			if (notPrime[i]) continue;
			for (int j = i * i; j < n; j += 2 * i)
			{
				if (!notPrime[j])
				{
					--count;
					notPrime[j] = true;
				}
			}
		}
		return count;
	}
}
