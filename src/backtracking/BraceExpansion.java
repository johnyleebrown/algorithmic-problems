/*
 * 1087
 * Company: Google
 *
 * Optimization.
 * Use pq to remove sort at the end.
 * Or iterate  without aux ds as data.
 */
class Solution 
{
	private List<String> temp = new ArrayList<>();
	private List<List<Character>> data = new ArrayList<>();

	public String[] expand(String S) 
	{
		// pre-processing
		int i = 0;
		while (i < S.length())
		{
			List<Character> local = new ArrayList<>();
			char x = S.charAt(i);
			if (x == '{')
			{
				i++;
				while (S.charAt(i) != '}')
				{
					if (S.charAt(i) != ',')
					{
						local.add(S.charAt(i));
					}

					i++;
				}
			}
			else
			{
				local.add(x);
			}
			i++;
			data.add(local);
		}	

		generate("", 0, 0);

		// place to the array	    
		String[] ans = new String[temp.size()];
		for (int j = 0; j < temp.size(); j++)
		{
			ans[j] = temp.get(j);
		}

		Arrays.sort(ans);
		return ans;
	}

	private void generate(String cur, int c, int start)
	{
		if (c == data.size())
		{
			temp.add(cur);
		}
		else
		{
			for (int i = start; i < data.size(); i++)
			{
				for (int j = 0; j < data.get(i).size(); j++)
				{
					generate(cur + data.get(i).get(j), c + 1, i + 1);
				}
			}
		}
	}
}

