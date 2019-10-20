/*
 * 947
 * Google
 *
 * In another words, we want to find a number of items we can remove so only unique cc's
 * are left. So we find number of connected componenets. This number is the number of
 * unique parents, to count that we use a set size.
 */
class Solution
{
	public int removeStones(int[][] stones)
	{
		Map<String, String> parents = new HashMap<>();
		Set<String> components = new HashSet<>(1000);

		for (int[] stone: stones)
		{
			String i = "i" + stone[0];
			String j = "j" + stone[1];

			if (parents.containsKey(i) && parents.containsKey(j))
			{
				String jParent = findParent(j, p);
				String iParent = findParent(i, p);

				// replace parent of j with parent of i
				p.put(jParent, iParent);

				// remove prev parent of j, since we replace it with i
				components.remove(jParent);
			}
			if (p.containsKey(i))
			{
				String iParent = findParent(i, p);
				p.put(j, iParent);
				components.add(iParent);
			}
			else if (p.containsKey(j))
			{
				String jParent = findParent(j, p);
				p.put(i, jParent);
				components.add(jParent);
			}
			else
			{
				p.put(i, i);
				p.put(j, i);

				components.add(i);
			}
		}

		return stones.length - set.size();
	}

	private String findParent(String x, Map<String, String> parents)
	{
		while (parents.get(x) != x)
		{
			x = parents.get(x);
		}
		return x;
	}
}

