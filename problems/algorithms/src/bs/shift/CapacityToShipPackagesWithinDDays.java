package bs.shift;

/**
 * 1011
 *
 * ======
 *
 * Task.
 *
 * Binary search solution, so we are trying to pick the right value. After the
 * pick we check if all weights fit, if they don't, we increase the lower
 * bound.
 */
public class CapacityToShipPackagesWithinDDays
{
	class Solution
	{
		public int shipWithinDays(int[] weights, int D)
		{
			int lo = weights[0], hi = 0;
			for (int i = 0; i < weights.length; i++)
			{
				if (weights[i] > lo)
				{
					lo = weights[i];
				}

				hi += weights[i];
			}

			int mid = hi;
			while (lo <= hi)
			{
				mid = lo + (hi - lo) / 2;
				if (!calculateFitOffset(weights, D, mid))
				{
					lo = mid + 1;

					// take lo because in the end hi will be the wrong answer
					mid = lo;
				}
				else
				{
					hi = mid - 1;
				}
			}

			return mid;
		}

		private boolean calculateFitOffset(int[] weights, int D, int mid)
		{
			int i = 0, n = weights.length;

			for (int k = 0; k < D; k++)
			{
				int cur = 0;
				while (i < n && cur + weights[i] <= mid)
				{
					cur += weights[i++];
				}
			}

			if (i < n)
			{
				return false;
			}

			return true;
		}
	}
}
