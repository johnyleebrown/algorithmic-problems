package sort.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56
 */
public class MergeIntervals
{
	class Solution
	{
		public int[][] merge(int[][] intervals)
		{
			int n = intervals.length;

			// edges
			if (n == 0 || intervals[0].length == 0) return new int[][]{};

			// sort
			sortArray(intervals);

			// merging
			List<int[]> l = new ArrayList<>();
			mergeIntervals(l, intervals, n);

			// to 2d array
			return listToArray(l);
		}

		private void sortArray(int[][] intervals)
		{
			Arrays.sort(intervals, ((a, b) -> a[0] - b[0]));
		}

		private void mergeIntervals(List<int[]> l, int[][] intervals, int n)
		{
			int i = 0;

			while (i < n)
			{
				int[] curpair = intervals[i];
				int end = curpair[1];
				i++;

				// if intervals overlap, end_A >= start_B, update end with end_B
				while (i < n && end >= intervals[i][0])
				{
					end = Math.max(end, intervals[i][1]);
					i++;
				}

				l.add(new int[]{curpair[0], end});
			}
		}

		private int[][] listToArray(List<int[]> l)
		{
			int[][] ans = new int[l.size()][2];
			int k = 0;

			for (int[] ar : l)
			{
				ans[k++] = new int[]{ar[0], ar[1]};
			}

			return ans;
		}
	}
}