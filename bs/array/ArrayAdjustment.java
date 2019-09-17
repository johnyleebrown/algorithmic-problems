/*
 * Binary searching the value. The min is the m/a.length - the average.
 * We check if the value, after replacing bigger items in array, won't exceed the max.
 *
 * Company:Google
 */
public class ArrayAdjustment
{
    private static int f(int[] a, int m)
    {
        int lo = m/a.length, hi = m, mid = -1;
        while (lo <= hi)
        {
            mid = lo + (hi - lo)/2;
            if (exceeds(a, mid, m))
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

    private static boolean exceeds(int[] a, int mid, int max)
    {
        int sum = 0;
        for (int i: a)
        {
            sum += Math.min(i, mid);
            if (sum > max) return true;
        }
        return false;
    }

    public static void main(String[] args) 
	{
        int[] a = new int[]{10, 5, 20, 30};
	    int max = 40;
        int m = f(a, max);
        System.out.println(m);
    }
}

