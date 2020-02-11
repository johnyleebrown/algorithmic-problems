package backtracking;

/**
 * 1066
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
