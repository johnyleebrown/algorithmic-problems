package test;

/*
 * Do a solution call
 */

public class Tester
{
	public static void check(Object source, String target)
	{
		try
		{
			assert evaluate(source.toString(), target);
		}
		catch(java.lang.AssertionError a)
		{
			System.out.print("NOK. ");
			return;
		}
		

		System.out.println("OK. " +  source + " = " + target);
	}

	private static boolean evaluate(String source, String target)
	{
		if (source == null) 
		{
			System.out.println("Source is null. ");
			return false;
		}

		return target.equals(source);
	}

	public static void main(String[] args)
	{
		System.out.println("[ ===== Tester ===== ]");
	}
}

