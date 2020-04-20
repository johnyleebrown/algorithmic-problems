package hashtable;

import java.util.HashMap;

/**
 * 881
 */
public class BoatsToSavePeople
{
	public int numRescueBoats(int[] people, int limit)
	{
		HashMap<Integer, Integer> map = new HashMap<>();
		int count = 0;
		for (int i = 0; i < people.length; i++)
		{
			if (people[i] == limit) count++;
			else map.put(i, map.getOrDefault(i, 0) + 1);
		}
		for (int i : map.keySet())
		{
			int c = map.getOrDefault(i, 0);
			if (c > 0)
			{
				int c2 = map.getOrDefault(limit - i, 0);
				if (c2 > 0)
				{
					map.put(limit - i, c2 - 1);
				}
				map.put(i, c - 1);
				count++;
			}
		}
		return count;
	}
}
