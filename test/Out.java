package test;

public class Out
{
	private static final String DELIMITER = " ";

	public static void main(String[] args) {}

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

	public static void sout()
	{
		System.out.println();
	}

	public static void sou(Object o)
	{
		System.out.print(o.toString());
	}
}

