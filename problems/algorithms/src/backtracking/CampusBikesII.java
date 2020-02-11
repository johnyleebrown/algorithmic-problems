package backtracking;

/**
 * 1066
 *
 * =====
 *
 * Task.
 *
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N
 * <= M. Each worker and bike is a 2D coordinate on this grid.
 *
 * We assign one unique bike to each worker so that the sum of the Manhattan
 * distances between each worker and their assigned bike is minimized.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) =
 * |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return the minimum possible sum of Manhattan distances between each worker
 * and their assigned bike.
 */
public class CampusBikesII
{
	/**
	 * For every worker w we are considering every bike i. Along the way
	 * counting the the min sum.
	 */
	class Solution
	{
		private boolean[] used;
		private int min = Integer.MAX_VALUE;

		public int assignBikes(int[][] workers, int[][] bikes)
		{
			used = new boolean[bikes.length];
			generate(0, 0, workers, bikes);
			return min;
		}

		private void generate(int sum, int w, int[][] workers, int[][] bikes)
		{
			if (sum > min)
			{
				return;
			}

			if (w == workers.length)
			{
				min = Math.min(min, sum);
			}
			else
			{
				for (int i = 0; i < bikes.length; i++)
				{
					if (used[i])
					{
						continue;
					}

					used[i] = true;
					generate(sum + getsum(workers[w], bikes[i]), w + 1, workers, bikes);
					used[i] = false;
				}
			}
		}

		private int getsum(int[] a, int[] b)
		{
			return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
		}
	}
}
