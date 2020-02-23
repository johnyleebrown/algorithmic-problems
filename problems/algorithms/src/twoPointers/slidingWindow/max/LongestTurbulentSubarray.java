package twoPointers.slidingWindow.max;

/**
 * 978
 *
 * ======
 *
 * Task.
 *
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only
 * if:
 *
 * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is
 * even; OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1]
 * when k is odd. That is, the subarray is turbulent if the comparison sign
 * flips between each adjacent pair of elements in the subarray.
 *
 * Return the length of a maximum size turbulent subarray of A.
 */
public class LongestTurbulentSubarray
{
	static class Solution
	{
		public int maxTurbulenceSize(int[] A)
		{
			if (A.length == 1)
			{
				return 1;
			}

			int result = calculateResult(A, 0, this::isValid1);
			return calculateResult(A, result, this::isValid2);
		}

		private int calculateResult(int[] A, int result, BasicFunctionalInterface functionalInterface)
		{
			int l = 0;
			for (int r = 0; r < A.length - 1; r++)
			{
				if (!functionalInterface.perform(A, r))
				{
					l = r + 1;
				}

				result = Math.max(result, r - l + 2);
			}

			return result;
		}

		private boolean isValid1(int[] A, int i)
		{
			return (i % 2 != 0 && A[i] > A[i + 1]) || (i % 2 == 0 && A[i] < A[i + 1]);
		}

		private boolean isValid2(int[] A, int i)
		{
			return (i % 2 != 0 && A[i] < A[i + 1]) || (i % 2 == 0 && A[i] > A[i + 1]);
		}

		@FunctionalInterface
		interface BasicFunctionalInterface
		{
			boolean perform(int[] A, int i);
		}
	}
}
