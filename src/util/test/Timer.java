package util.test;

public class Timer
{
	final long startTime;

	public Timer()
	{
		startTime = System.currentTimeMillis();
	}

	public long getTotalTime()
	{
		return System.currentTimeMillis() - startTime;
	}
}
