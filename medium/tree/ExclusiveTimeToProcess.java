package medium.tree;

import static test.Out.sout;

public class ExclusiveTimeToProcess
{
	public static void main(String[] args)
	{
		int[][] ps = new int[][]{
			{1, 150, 300},
			{2, 100, 200},
			{3, 300, 350}
		};
		ExclusiveTimeToProcess e = new ExclusiveTimeToProcess();
		int[][] times = e.calculateExclusiveTime(ps);
		for (int[] t: times)
		{
			sout(t[0], t[1]);
		}
	}

	private static int[][] calculateExclusiveTime(int[][] processes)
	{
			
	}
}

