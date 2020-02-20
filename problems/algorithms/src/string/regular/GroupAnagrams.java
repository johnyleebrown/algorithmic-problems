package string.regular;

/*
 * 49
 * Company: Amazon
 */
public class G
{
	/*
	 * Sort each time we encounter a string.
	 */
	public class Solution1 
	{
		public List<List<String>> groupAnagrams(String[] strs) 
		{
			if (strs == null || strs.length == 0) 
			{
				return new ArrayList<List<String>>();
			}

			Map<String, List<String>> map = new HashMap<String, List<String>>();

			for (String s : strs) 
			{
				char[] ca = s.toCharArray();
				Arrays.sort(ca);
				String keyStr = String.valueOf(ca);

				if (!map.containsKey(keyStr)) 
				{
					map.put(keyStr, new ArrayList<String>());
				}

				map.get(keyStr).add(s);
			}

			return new ArrayList<List<String>>(map.values());
		}
	}

	/*
	 * Custom Node class for storage.
	 */
	class Solution2
	{
		public List<List<String>> groupAnagrams(String[] strs)
		{
			Map<Node, List<String>> map = new HashMap<>();
			for (String s: strs)
			{
				Node x = new Node(s);
				if (!map.contains(x))
				{
					map.put(x, new ArrayList<>());
				}
				map.get(x).add(s);
			}
			List<List<String>> ans = new ArrayList<>();
			for (List<String> l: map.values())
			{
				ans.add(l);
			}
			return ans;
		}

		private class Node
		{
			int len;
			Map<Character, Integer> c = new HashMap<>();

			public Node(String s)
			{
				this.len = s.length();
				for (int i = 0; i < s.length(); i++)
				{
					c.put(s.charAt(i), getOrDefault(s.charAt(i), 0) + 1);
				}	
			}

			@Override
			public int hashCode()
			{
				return 31 * len + 11 * c.hashCode();		
			}

			@Override
			public boolean equals(Object o)
			{
				if (o == null)
				{
					return false;
				}

				if (o.len != this.len)
				{
					return false;
				}

				if (o.c.size() != this.c.size())
				{
					return false;
				}

				for (Entry<Character, Integer> e: o.c.entrySet())
				{
					if (!this.c.containsKey(e.getKey()))
					{
						return false;
					}

					if (this.c.get(e.getKey()) != e.getValue())
					{
						return false;
					}
				}

				return true;
			}
		}
	}

}

