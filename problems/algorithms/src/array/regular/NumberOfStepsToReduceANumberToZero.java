package array.regular;

/**
 * 1342
 *
 * ======
 *
 * Task.
 *
 * Given a non-negative integer num, return the number of steps to reduce it to
 * zero. If the current number is even, you have to divide it by 2, otherwise,
 * you have to subtract 1 from it.
 *
 * ======
 *
 * Source: Leetcode
 */
public class NumberOfStepsToReduceANumberToZero
{
	class Solution
	{
		public int numberOfSteps(int num)
		{
			int c = 0;
			while (num != 0)
			{
				if (num % 2 == 0)
				{
					num /= 2;
				}
				else
				{
					num--;
				}
				c++;
			}
			return c;
		}
	}
}