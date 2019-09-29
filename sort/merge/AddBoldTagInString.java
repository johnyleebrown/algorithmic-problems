/*
 * 616
 * Company:Google
 */
class Solution
{
	public String addBoldTag(String s, String[] dict)
	{
		// create intervals of indexes
		List<int[]> ar = new ArrayList<>();
		createIntervals(ar, s, dict);

		// sort and merge intervals
		Map<Integer, Integer> m = new HashMap<>();
		merge(ar, m);

		// combine intervals with bold tags
		return generateString(m, s, dict);
	}

	private void createIntervals(List<int[]> ar, String s, String[] dict)
	{
		for (int i = 0; i < dict.length; i++)
		{
			int k = -1;
			int ind = s.indexOf(dict[i], k);

			while (ind != -1)
			{
				ar.add(new int[]{ ind, ind + dict[i].length() });
				k++;
				ind = s.indexOf(dict[i], k);
			}
		}
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
				i = m.get(i);
			}
			else
			{
				ans += s.charAt(i);
				i++;
			}
		}

		return ans;
	}

	private void merge(List<int[]> ar, Map<Integer, Integer> m)
	{
		sortArray(ar);
		mergeIntervals(ar, m);
	}

	private void mergeIntervals(List<int[]> ar, Map<Integer, Integer> m)
	{
		int i = 0;

		while (i < ar.size())
		{
			int[] cur = ar.get(i);
			int end = cur[1];
			i++;

			while (i < ar.size() && end >= ar.get(i)[0])
			{
				end = Math.max(end, ar.get(i)[1]);
				i++;
			}

			m.put(cur[0], end);
		}
	}

	private void sortArray(List<int[]> ar)
	{
		Collections.sort(ar, (a, b) -> a[0] - b[0]);
	}
}

