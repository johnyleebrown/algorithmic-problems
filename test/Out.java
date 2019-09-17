package test;

public class Out
{
	private static final String DELIMITER = " ";

	public static void sout(Object o)
	{
		System.out.println(o.toString());
	}

	public static void sout(Object... os)
	{
		String output = "";

		output += os[0];

		for (int i = 1; i < os.length; i++)
		{
			output += DELIMITER + os[i];
		}

		System.out.println(output);
	}

	public static void sout(int[][] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			sout(a[i]);
		}
	}

	public static void sout(int[] a)
	{
		String output = "";

		output += a[0];

		for (int i = 1; i < a.length; i++)
		{
			output += DELIMITER + a[i];
		}

		System.out.println(output);
	}

	public static void sout()
	{
		System.out.println();
	}

	public static void sou(Object o)
	{
		System.out.print(o.toString());
	}
}

