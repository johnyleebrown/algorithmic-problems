package test;

import static test.Out.sout;

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
			sout("NOK. ");
			return;
		}


        sout("OK. " +  source + " = " + target);
	}

	private static boolean evaluate(String source, String target)
	{
		if (source == null) 
		{
            sout("Source is null.");
			return false;
		}

		return target.equals(source);
	}

	public static void main(String[] args)
	{
        sout("[ ===== Tester ===== ]");
	}
}

