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
		// treemap for events
		TreeMap<Integer, int[]> map = new TreeMap<>();
		for (int[] p: processes)
		{
			int ind = p[0];
			
			int val = map.getOrDefault(p[1], 0) + 1;
			map.put(p[1], new int[]{val, ind});

			val = map.getOrDefault(p[2], 0) - 1;
			map.put(p[2], new int[]{val, ind});
		}

		int countOngoingProcesses = 0;
		int curProcessInd = 0;
		int prevDuration = 0;

		int n = processes.length;
		int[][] ans = new int[n + 1][2];
		
		for (int key: map.keySet())
		{
			int[] val = map.get(key);
			int curDuration = key;
			int curInd = val[1];

			if (countOngoingProcesses == 0)
				curProcessInd = curInd;

		}		

		return ans;
	}
}

