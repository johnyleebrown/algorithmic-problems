package util.test;

import java.util.ArrayList;
import java.util.List;

import static util.test.Out.sout;

public class Tester
{
	private static final String NEW_LINE = "\n";
	private static final String TESTER_START = 	"[============== Tester ==============]";
	private static final String TESTER_SEP = 	"--------------------------------------";

	public static void check(Object source, String... targets)
	{
		final String s = source.toString();
		boolean successFlag = true;
		final List<String> unmatched = new ArrayList<>();

		for (String target: targets)
		{
			if (target.equals(s))
			{
				sout("OK. " +  s + " = " + target);
				break;
			}
			else
			{
				successFlag = false;
			   	unmatched.add(target);	
			}
		}
		if (!successFlag)
		{
			sout("NOK. " + s + " does not match none of these answers: " + unmatched + ".");
		}

		sout(TESTER_SEP);
	}

	public Tester()
	{
		sout(NEW_LINE + TESTER_START);
	}
}

