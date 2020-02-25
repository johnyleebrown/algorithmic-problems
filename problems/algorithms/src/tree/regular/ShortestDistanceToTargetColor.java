package tree.regular;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 1182
 */
public class ShortestDistanceToTargetColor
{
	// treeset solution
	// other solution is to use binary search
	class Solution
	{
		public List<Integer> shortestDistanceColor(int[] colors, int[][] queries)
		{
			// a map of key-color, value - tree set of indexes
			List<TreeSet<Integer>> map = new ArrayList<>(3);
			for (int i = 0; i < 3; i++)
			{
				map.add(new TreeSet<>());
			}
			for (int i = 0; i < colors.length; i++)
			{
				map.get(colors[i] - 1).add(i);
			}

			List<Integer> ans = new ArrayList<>();
			for (int[] q:queries)
			{
				int i = q[0];
				int c = q[1];
				if (map.get(c - 1).size() == 0)
				{
					ans.add(-1);
				}
				else
				{
					int m = getmin(map, c, i);
					ans.add(m);
				}
			}

			return ans;
		}

		private int getmin(List<TreeSet<Integer>> map, int co, int i)
		{
			Integer f = map.get(co - 1).floor(i);
			Integer c = map.get(co - 1).ceiling(i);
			if (c == null && f == null)
			{
				return -1;
			}
			else if (f == null)
			{
				return Math.abs(c - i);
			}
			else if (c == null)
			{
				return Math.abs(f - i);
			}
			else
			{
				return Math.min(Math.abs(f - i), Math.abs(c - i));
			}
		}
	}

}

