package test;

import static test.Out.sout;

public class Tester
{
	public static void check(Object source, String target)
	{
		final String s = source.toString();
		if (target.equals(s))
		{
        	sout("OK. " +  s + " = " + target);
		}
		else
		{
			sout("NOK. " + s + " != " + target);
		}
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

