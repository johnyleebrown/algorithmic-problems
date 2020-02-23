package math.other;

/**
 * 1317
 *
 * ======
 *
 * Task.
 *
 * Given an integer n. No-Zero integer is a positive integer which doesn't contain any 0 in its decimal representation.
 * Return a list of two integers [A, B] where: A and B are No-Zero integers. A + B = n It's guaranteed that there is at
 * least one valid solution. If there are many valid solutions you can return any of them.
 */
public class ConvertIntegerToTheSumOfTwoNoZeroIntegers
{
	static class Solution
	{
		public int[] getNoZeroIntegers(int n)
		{
			for (int i = 1; i < n; i++)
			{
				if (isNonZeroInteger(i) && isNonZeroInteger(n - i))
				{
					return new int[]{i, n - i};
				}
			}

			return new int[]{};
		}

		private static boolean isNonZeroInteger(int n)
		{
			while (n > 0)
			{
				if (n % 10 == 0)
				{
					return false;
				}

				n /= 10;
			}

			return true;
		}
	}
}
