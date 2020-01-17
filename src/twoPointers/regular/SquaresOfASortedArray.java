package twoPointers.regular;

/**
 * 977
 */
class SquaresOfASortedArray
{
	static class Solution
	{
		public int[] sortedSquares(int[] A)
		{
			int icebreaker = -1;
			for (int i = 0; i < A.length - 1; i++)
			{
				if (A[i] < 0) icebreaker = i;
				if (A[i] < 0 && A[i + 1] >= 0)
				{
					break;
				}
			}

			int i = icebreaker, j = i + 1, n = A.length, k = 0;
			int[] temp = new int[n];

			while (i >= 0 && j < n)
			{
				if (lt(A[i], A[j]))
				{
					temp[k++] = A[i] * A[i];
					i--;
				}
				else
				{
					temp[k++] = A[j] * A[j];
					j++;
				}
			}

			while (i >= 0)
			{
				temp[k++] = A[i] * A[i];
				i--;
			}

			while (j < n)
			{
				temp[k++] = A[j] * A[j];
				j++;
			}

			return temp;
		}

		private boolean lt(int a, int b)
		{
			int x = Math.abs(a), y = Math.abs(b);
			return x < y;
		}
	}
}
