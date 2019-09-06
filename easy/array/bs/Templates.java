public class Templates
{
	/*
	 * Template 1 - Regular
	 */
	public static int binarySearch1(int[] a, int x)
	{
		int lo = 0, hi = a.length - 1;

		while (lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;

			if (a[mid] < x)
			{
				hi = mid + 1;
			}
			else if (a[mid] > x)
			{
				lo = mid - 1;
			}
			else
			{
				return mid;
			}
		}

		return -1;
	}
}

