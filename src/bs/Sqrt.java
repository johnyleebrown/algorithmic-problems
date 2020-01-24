package bs;

/**
 * 69
 */
class Sqrt
{
	static class Solution
	{
		public int mySqrt(int x)
		{
			if (x == 0) return 0;
			int lo = 1, hi = x, ans = -1;
			while (lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;
				if (mid <= x / mid)
				{
					lo = mid + 1;
					ans = mid;
				}
				else
				{
					hi = mid - 1;
				}
			}
			return ans;
		}
	}
}
