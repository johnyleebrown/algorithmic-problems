package sort.heap;

import util.tester.Tester;

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
		public int[][] sort(int[][] a)
		{
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int[] ints : a)
				for (int anInt : ints)
					pq.add(anInt);
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a[i].length; j++)
					a[i][j] = pq.poll();
			return a;
		}
	}

	/**
	 * Iterate through ar, swap with smallest number from lower rows. Place the
	 * element in the lowest row in the right position. Complexity: need to go
	 * through m*n/2 cells apprx + sort and swaps will take n/2*m^2.
	 */
	private static class Solution2
	{
		public int[][] sort(int[][] a)
		{
			int n = a.length;
			int m = a[0].length;
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < m; j++)
				{
					int min = a[i][j];
					int localI = i;
					for (int i2 = i + 1; i2 < n; i2++)
					{
						if (a[i2][0] < min)
						{
							min = a[i2][0];
							localI = i2;
						}
					}
					if (a[i][j] == min)
						continue;
					swap(a, i, j, localI, 0);
					for (int j2 = 0; j2 < m - 1; j2++)
						if (a[localI][j2] > a[localI][j2 + 1])
							swap(a, localI, j2, localI, j2 + 1);
						else
							break;
				}
			}
			return a;
		}

		private void swap(int[][] a, int i1, int j1, int i2, int j2)
		{
			int t = a[i1][j1];
			a[i1][j1] = a[i2][j2];
			a[i2][j2] = t;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution2())
				.add(new int[][]{{5, 12, 17, 21, 23}, {1, 2, 4, 6, 8}, {12, 14, 18, 19, 27}, {3, 7, 9, 15, 25}}).expect(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 12}, {12, 14, 15, 17, 18}, {19, 21, 23, 25, 27}})
				.run();
	}
}