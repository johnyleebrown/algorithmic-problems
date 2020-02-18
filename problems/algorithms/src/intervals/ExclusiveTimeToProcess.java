package intervals;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Google_Interview_5
 *
 * =====
 *
 * Task.
 *
 * Given a list of processes that can run in parallel. Each process is
 * represented by a triplet [id, startTime, endTime].
 *
 * A process's exclusive time is the time spent to execute this process. Note
 * that this does not include the time while multiple processes run
 * simultaneously. Return the exclusive time of each process in the form [id,
 * duration].
 *
 * https://leetcode.com/discuss/interview-question/367064/Google-or-Phone-Screen-or-Exclusive-Time-of-Processes/331066
 */
public class ExclusiveTimeToProcess
{
	class Solution1
	{
		private int[][] calculateExclusiveTime(int[][] processes)
		{
			// treemap for events
			TreeMap<Integer, int[]> map = new TreeMap<>();
			for (int[] p : processes)
			{
				int count = 1;
				if (map.containsKey(p[1]))
				{
					count += map.get(p[1])[2];
				}
				map.put(p[1], new int[]{p[0], 0, count});

				count = -1;
				if (map.containsKey(p[2]))
				{
					count -= map.get(p[2])[2];
				}
				map.put(p[2], new int[]{p[0], 1, count});
			}

			// set the indexes of the processes answers
			int n = processes.length;
			int[][] ans = new int[n + 1][2];
			for (int i = 0; i < n + 1; i++) ans[i][0] = i + 1;

			int count = 0;
			int prevDuration = map.firstKey();
			List<Integer> l = new ArrayList<>();

			for (int curDuration : map.keySet())
			{
				int[] val = map.get(curDuration);
				int curInd = val[0], start = val[1], c = val[2];

				count += c;

				if (start == 0) l.add(curInd);
				else l.remove(new Integer(curInd));

				if (count == 1)
					ans[l.get(0) - 1][1] += curDuration - prevDuration;

				prevDuration = curDuration;
			}

			return ans;
		}
	}
}