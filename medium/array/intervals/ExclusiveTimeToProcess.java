package medium.array.intervals;

import static test.Out.sout;
import java.util.*;

// todo
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
			// count, index, isStart
			map.put(p[1], new int[]{-1, ind, 1});
			map.put(p[2], new int[]{-1, ind, 0});
		}

		int n = processes.length;
		int[][] ans = new int[n + 1][2];
		for (int i = 0; i < n + 1; i++) ans[i][0] = i + 1;
		int countOngoingProcesses = 1;
		int[] firstValue = map.get(map.firstKey());
		int prevDuration = map.firstKey();
		sout(prevDuration, firstValue[1]);
		List<Integer> l = new ArrayList<>();
		l.add(firstValue[1]);

		for (int key: map.keySet())
		{
			int[] val = map.get(key);
			
			int curDuration = key;
			int curInd = val[1];
			int isStart = val[2];
		
			sout(countOngoingProcesses, (l.size() > 0 ? l.get(0) - 1 : -1),
					(curDuration - prevDuration));	

			if (countOngoingProcesses == 1)
			{
				ans[l.get(0) - 1][1] += curDuration - prevDuration;
			}

			if (isStart == 0)
			{
				countOngoingProcesses++;
				l.add(curInd);
			}
			else
			{
				countOngoingProcesses--;	
				l.remove(new Integer(curInd));
			}

			prevDuration = curDuration;
		}		

		return ans;
	}
}

