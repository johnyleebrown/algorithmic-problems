package math.detectRecurrence;

import util.tester.Tester;

/**
 * 1359
 *
 * ======
 *
 * Task.
 *
 * Given n orders, each order consist in pickup and delivery services.
 *
 * Count all valid pickup/delivery possible sequences such that delivery(i) is
 * always after of pickup(i).
 *
 * ======
 *
 * Source: Leetcode
 */
public class CountAllValidPickupAndDeliveryOptions
{
	/**
	 * Straightforward. Do some examples.
	 */
	private static class Solution
	{
		private final static long mod = (long) (1e9) + 7;

		public int countOrders(int n)
		{
			long start = 1;
			long sum = 1;
			long prod = 1;

			while (--n > 0)
			{
				for (int i = 0; i < 2; i++)
				{
					start++;
					sum += start;
				}

				prod = prod * sum % mod;
			}

			return (int) prod;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(18).expect(368349166)
				.run();
	}
}