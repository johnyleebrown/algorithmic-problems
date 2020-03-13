package util.utility;

public class Timer
{
	private long startTime, endTime;

	public Timer start()
	{
		startTime = System.nanoTime();
		return this;
	}

	public Timer end()
	{
		endTime = System.nanoTime();
		return this;
	}

	public double getTotal()
	{
		return (endTime - startTime)/1e6;
	}

	public void printEnd()
	{
		System.out.println(String.format("%.2f", this.end().getTotal()));
	}
}
