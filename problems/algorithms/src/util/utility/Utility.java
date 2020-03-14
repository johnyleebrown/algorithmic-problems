package util.utility;

public class Utility
{
	public static String getMaxLen3Strings(String a, String b, String c)
	{
		return getMaxLen2Strings(a, getMaxLen2Strings(b, c));
	}

	public static String getMaxLen2Strings(String a, String b)
	{
		if (a.length() > b.length())
			return a;
		else
			return b;
	}

	public static void exch(int[] a, int j, int i)
	{
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
}
