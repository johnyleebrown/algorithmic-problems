package util.utility;

public class Assertion
{
	public static void assertDoesNotThrow(FailingRunnable action)
	{
		try
		{
			action.run();
		}
		catch (Exception e)
		{
			throw new Error("ERROR", e);
		}
	}
}