// 76
public class MinimumWindowSubstring
{
	// optimize with 256 size array
	class Solution 
	{
		public String minWindow(String s, String t) 
		{
			if (s.length() == 0 || t.length() == 0 || t.length() > s.length()) return "";
			if (t.equals(s)) return s;

			int tcount = t.length(), i = 0, j = 0, mini = 0, minj = 0, minlen = Integer.MAX_VALUE;
			Map<Character, Integer> m = new HashMap<>();
			for (char c: t.toCharArray()) m.put(c, m.getOrDefault(c, 0) + 1);

			while (j < s.length())
			{
				char cj = s.charAt(j);
				if (m.containsKey(cj))
				{
					if (m.get(cj) > 0) tcount--;
					m.put(cj, m.get(cj) - 1);
				}

				j++;

				while (tcount == 0)
				{
					if (j - i < minlen)
					{
						minlen = j - i;
						minj = j; mini = i;
					}

					char ci = s.charAt(i);
					if (m.containsKey(ci))
					{
						if (m.get(ci) == 0) tcount++;
						m.put(ci, m.get(ci) + 1);
					}

					i++;
				}
			}

			return s.substring(mini, minj);
		}
	}
}

