package intervals;

import java.util.TreeMap;

/**
 * 731
 */
public class MyCalendarII
{
	class Solution
	{
		private TreeMap<Integer, Integer> map = new TreeMap<>();

		public boolean book(int start, int end)
		{
			map.put(start, map.getOrDefault(start, 0) + 1);
			map.put(end, map.getOrDefault(end, 0) - 1);

			int count = 0;
			for (int v : map.values())
			{
				count += v;
				if (count > 2)
				{
					if (map.get(start) == 1)
					{
						map.remove(start);
					}
					else
					{
						map.put(start, map.get(start) - 1);
					}

					if (map.get(end) == -1)
					{
						map.remove(end);
					}
					else
					{
						map.put(end, map.get(end) + 1);
					}

					return false;
				}
			}

			return true;
		}
	}
}