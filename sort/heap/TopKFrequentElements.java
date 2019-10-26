/*
 * 347
 * Google
 */
class Solution
{
	public List<Integer> topKFrequent(int[] nums, int k)
	{
		// get counts
		Map<Integer, Integer> m = new HashMap<>();
		for (int i: nums)
		{
			m.put(i, m.getOrDefault(i, 0) + 1);
		}
		// fill buckets
		List<Integer>[] a = new List[nums.length + 1];
		for (int i: m.keySet())
		{
			int c = m.get(i);
			if (a[c] == null)
			{
				a[c] = new ArrayList<>();
			}
			a[c].add(i);
		}
		// get k top elements
		List<Integer> ans = new ArrayList<>();
		int c = 0;
		for (int i = a.length - 1; c < k && i >= 0; i--)
		{
			if (a[i] == null)
			{
				continue;
			}
			for (int x: a[i])
			{
				ans.add(x);
				c++;
				if (c == k)
				{
					return ans;
				}
			}
		}
		throw new RuntimeException("k should be < n");
	}
}

