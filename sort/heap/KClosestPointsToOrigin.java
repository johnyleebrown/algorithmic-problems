// 973
public class ClosestPointsToOrigin
{
	/*
	 * Quick sort partitioning
	 * O(n), O(1)
	 */
	class Solution 
	{
		public int[][] kClosest(int[][] points, int K) 
		{
			int lo = 0, hi = points.length - 1;

			while (lo < hi)
			{
				int p = findPartition(lo, hi, points);

				if (p > K)
				{
					hi = p - 1;
				}
				else if (p < K)
				{
					lo = p + 1;
				}
				else
				{
					break;
				}
			}

			return convertListToArray(points, K);
		}

		private int[][] convertListToArray(int[][] ar, int K)
		{
			int[][] ans = new int[K][2];

			for (int i = 0; i < K; i++)
			{
				ans[i] = ar[i];
			}

			return ans;
		}

		private int findPartition(int lo, int hi, int[][] a)
		{
			int[] v = a[lo];
			int i = lo;
			int j = hi + 1;

			while (true)
			{
				while (less(a[++i], v))
				{
					if (i == hi)
					{
						break;
					}
				}

				while (less(v, a[--j]))
				{
					if (j == lo)
					{	
						break;
					}
				}

				if (i >= j) 
				{
					break;
				}

				exch(a, i, j);
			}

			exch(a, lo, j);
			return j;
		}

		private boolean less(int[] a, int[] b)
		{
			int distA = Math.abs(a[0]) * Math.abs(a[0]) + 
				Math.abs(a[1]) * Math.abs(a[1]);
			int distB = Math.abs(b[0]) * Math.abs(b[0]) + 
				Math.abs(b[1]) * Math.abs(b[1]);

			return distA < distB;
		}

		private void exch(int[][] a, int i, int j)
		{
			int[] temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

	/*
	 * Max heap
	 * O(n*logk)
	 */
	public class Solution2
	{
		public int[][] kClosest(int[][] points, int K) 
		{
			int n = points.length;
			int[][] x = new int[K][2];

			for (int i = 0; i < K; i++)
			{
				x[i] = points[i];
				swim(x, i);
			}

			for (int i = K; i < points.length; i++)
			{
				if (greater(x[0], points[i]))
				{
					x[0] = points[i];
					sink(x, 0, K);
				}
			}

			return x;
		}

		// min heap - using greater instead of less
		private void sink(int[][] a, int k, int n)
		{
			while (2 * k <= n)
			{
				int j = 2 * k;

				if (j + 1 < n && greater(a[j], a[j + 1]))
				{
					j++;
				}

				if (!greater(a[k], a[j]))
				{
					break;
				}

				exch(a, k, j);

				k = j;
			}
		}

		// moving element up, towards the start of the array
		private void swim(int[][] a, int k)
		{
			while (k > 1 && greater(a[k/2], a[k]))
			{
				exch(a, k, k/2);
				k /= 2;
			}
		}

		private boolean greater(int[] a, int[] b)
		{
			int distA = Math.abs(a[0]) * Math.abs(a[0]) + 
				Math.abs(a[1]) * Math.abs(a[1]);
			int distB = Math.abs(b[0]) * Math.abs(b[0]) + 
				Math.abs(b[1]) * Math.abs(b[1]);

			return distA > distB;
		}

		private void exch(int[][] a, int i, int j)
		{
			int[] temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

}
/*
   [[1,3],[-2,2]]
   1
   [[3,3],[5,-1],[-2,4]]
   2
   [[-2,10],[-4,-8],[10,7],[-4,-7]]
   3
   */

