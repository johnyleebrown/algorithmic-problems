package math.random;

import java.util.*;

/**
 * 380
 *
 * ======
 *
 * Task.
 *
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present. remove(val): Removes an item val from the set if
 * present. getRandom: Returns a random element from current set of elements. Each element must have the same
 * probability of being returned.
 *
 * ======
 *
 * Source: Leetcode
 */
public class InsertDeleteGetrandomO1
{
	/**
	 *
	 */
	class Solution
	{
		private Random r;
		private List<Integer> l;
		private Map<Integer, Integer> m;

		public Solution()
		{
			r = new Random();
			l = new ArrayList<>();
			m = new HashMap<>();
		}

		public boolean insert(int val)
		{
			if (m.containsKey(val))
			{
				return false;
			}

			m.put(val, l.size());
			l.add(val);

			return true;
		}

		public boolean remove(int val)
		{
			if (!m.containsKey(val))
			{
				return false;
			}

			int valIndex = m.get(val);
			if (valIndex < l.size() - 1)
			{
				int replacementValue = l.get(l.size() - 1);
				l.set(valIndex, replacementValue);
				m.put(replacementValue, valIndex);
			}

			l.remove(l.size() - 1);
			m.remove(val);

			return true;
		}

		public int getRandom()
		{
			return l.get(r.nextInt(l.size()));
		}
	}
}