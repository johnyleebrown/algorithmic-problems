/*
 * Binary searching the value. The min is the m/a.length - the average.
 * We check if the value, after replacing bigger items in array, won't exceed the max.
 *
 * Company:Google
 */
public class ArrayAdjustment
{
	/*
	 * Find the value that is less then max.
	 * Just change the exceeds method.
	 */
	public class Variant1
	{
		public static void main(String[] args) 
		{
			int[] a = new int[]{10, 5, 20, 30};
			int max = 40;

			System.out.println(f(a, max));
		}
	}

	/*
	 * Equals piece.
	 */
	public class Variant2
	{
		private static int f(int[] a, int m)
		{
			// check if we need to search for anything
			int s = 0, max = Integer.MIN_VALUE;
			for (int i: a) 
			{
				s += i;
				max = Math.max(max, i);
			}
			if (s == m) return max;
			//////////////////////

			int lo = 1, hi = m, mid = -1;
			while (lo <= hi)
			{
				mid = lo + (hi - lo)/2;
				int x = e(a, mid, m);
				if (x == 0) return mid;
				if (x == 1)
				{
					hi = mid - 1;
				}
				else
				{
					lo = mid + 1;
				}
			}
			return mid;
		}

		private static int e(int[] a, int mid, int max)
		{
			int sum = 0;
			for (int i: a)
			{
				sum += Math.min(i, mid);
			}
			return sum != max ? sum > max ? 1 : -1 : 0;
		}

		public static void main(String[] args) 
		{
			int[] a = new int[]{100, 300, 200, 400};
			int max = 800;
			System.out.println(f(a, max)); // 250

			a = new int[]{100, 300, 200, 400};
			max = 400;
			System.out.println(f(a, max)); // 100

			a = new int[]{100};
			max = 100;
			System.out.println(f(a, max)); // 100

			a = new int[]{100, 100, 100, 100, 200};
			max = 500;
			System.out.println(f(a, max)); // 100

			a = new int[]{100, 100, 500, 300, 200, 400};
			max = 1500;
			System.out.println(f(a, max)); // 400

			a = new int[]{100, 100, 100, 100, 200};
			max = 600;
			System.out.println(f(a, max)); // 200

			a = new int[]{100, 300, 200, 400};
			max = 999;
			System.out.println(f(a, max)); // 399
		}
	}	
}

