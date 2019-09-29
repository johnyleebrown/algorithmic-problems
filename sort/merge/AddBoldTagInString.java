/*
 * 616
 * Company:Google
 */
class Solution 
{
	public String addBoldTag(String s, String[] dict) 
	{
		// create intervals of indexes
		List<Interval> ar = new ArrayList<>();
		for (int i = 0; i < dict.length; i++)
		{
			// System.out.println(i);
			int k = -1;
			int ind = s.indexOf(dict[i], k);
			while (ind != -1)
			{   
				// System.out.println(ind + " " + (ind + dict[i].length()) + " " + dict[i]);
				ar.add(new Interval(ind, ind + dict[i].length()));
				k = ind + dict[i].length();
				ind = s.indexOf(dict[i], k);
			}
		}

		// sort and merge intervals
		Map<Integer, Integer> m = merge(ar);

		// combine intervals with bold tags
		return generateString(m, s, dict);
	}

	private String generateString(Map<Integer, Integer> m, String s, String[] dict)
	{
		String ans = "";
		int i = 0;
		while (i < s.length())
		{
			if (m.containsKey(i))
			{
				ans += "<b>" + s.substring(i, m.get(i)) + "</b>";
				i += m.get(i) + 1;
			}
			else
			{
				ans += s.charAt(i);
				i++;
			}
		}

		return ans;
	}
	private Map<Integer, Integer> merge(List<Interval> ar)
	{
		sortArray(ar);
		return mergeIntervals(ar);
	}

	private Map<Integer, Integer> mergeIntervals(List<Interval> ar)
	{
		Map<Integer, Integer> ans= new HashMap<>();

		int i = 0;
		while (i < ar.size())
		{
			Interval cur = ar.get(i);
			int end = cur.getEnd();
			i++;

			while (i < ar.size() && end >= ar.get(i).getStart())
			{
				end = Math.max(end, cur.getEnd());
				i++;
			}

			ans.put(cur.getStart(), end);
		}

		// System.out.println(1);

		return ans;
	}

	private void sortArray(List<Interval> ar)
	{
		Collections.sort(ar, (a, b) -> a.getStart() - b.getStart());
	}

	private class Interval
	{
		private int start;
		private int end;

		public Interval(int i, int j)
		{
			start = i; end = j;
		}

		private int getStart()
		{
			return start;
		}

		private int getEnd()
		{
			return start;
		}
	}
}
