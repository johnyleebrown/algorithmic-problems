package array.puzzles;

/**
 * 769
 *
 * ======
 *
 * Task.
 *
 * Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we
 * split the array into some number of "chunks" (partitions), and individually
 * sort each chunk.  After concatenating them, the result equals the sorted
 * array.
 *
 * What is the most number of chunks we could have made?
 *
 * ======
 *
 * Source: Leetcode
 */
public class MaxChunksToMakeSorted
{
	/**
	 * Counting how many times we have to change max number. Same works for min.
	 * We pick up max and carry it until the drop - the length of a rotation.
	 */
	public class Solution
	{
		public int maxChunksToSorted(int[] arr)
		{
			int count = 0;
			int max = 0;

			for (int i = 0; i < arr.length; i++)
			{
				max = Math.max(max, arr[i]);

				if (max == i)
				{
					count++;
				}
			}

			return count;
		}
	}

	class Solution2
	{
		public int maxChunksToSorted(int[] a)
		{
			int res = 0;
			int currentSum = 0;
			int expectedSum = 0;

			for (int i = 0; i < a.length; i++)
			{
				currentSum += a[i];
				expectedSum += i;

				if (currentSum == expectedSum)
				{
					res++;
				}
			}

			return res;
		}
	}
}