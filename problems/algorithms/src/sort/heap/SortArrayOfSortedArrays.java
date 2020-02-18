package sort.heap;

import java.util.PriorityQueue;

/**
 * Google_Interview_4
 *
 * =====
 *
 * Task.
 *
 * Sort array of sorted arrays. Input: [[ 5, 12, 17, 21, 23], [ 1,  2,  4,  6,
 * 8], [12, 14, 18, 19, 27], [ 3,  7,  9, 15, 25]]
 *
 * Output: [[ 1,  2,  3,  4,  5], [ 6,  7,  8,  9, 12], [12, 14, 15, 17, 18],
 * [19, 21, 23, 25, 27]]
 */
public class SortArrayOfSortedArrays
{
	/**
	 * Use pq O(n*m) + O(n*m*log(n*m)) + O(n*m*log(n*m))
	 */
	private static class Solution1
	{
		public void sort(int[][] a)
		{
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a[i].length; j++)
				{
					pq.add(a[i][j]);
				}
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a[i].length; j++)
				{
					a[i][j] = pq.poll();
				}
		}
	}

	/**
	 * Iterate through ar, swap with smallest number from lower rows. Place the
	 * element in the lowest row in the right position. Complexity: need to go
	 * through m*n/2 cells apprx + sort and swaps will take n/2*m^2.
	 */
	private static class Solution2
	{
		public void sort(int[][] a)
		{
			int n = a.length;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < a[i].length; j++)
				{
					int min = a[i][j];
					int minRow = i;

					// comparing with first el in each row below
					for (int k = i + 1; k < n; k++)
					{
						if (min > a[k][0])
						{
							min = a[k][0];
							minRow = k;
						}
					}

					if (min != a[i][j])
					{
						// swap the min
						swap(a, i, j, minRow, 0);

						// put new row min in right place in the row
						for (int k = 0; k < a[minRow].length - 1; k++)
						{
							if (a[minRow][k] <= a[minRow][k + 1])
							{
								break;
							}

							swap(a, minRow, k, minRow, k + 1);
						}
					}
				}
		}

		private void swap(int[][] a, int i, int j, int newI, int newJ)
		{
			int temp = a[i][j];
			a[i][j] = a[newI][newJ];
			a[newI][newJ] = temp;
		}
	}
}