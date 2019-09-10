// 76
public class MinimumWindowSubstring
{
	class Solution 
	{
		public String minWindow(String s, String t) 
		{
			if (t.length() > s.length()) return "";

			int start = 0, end = 0, resultstart = 0;
			int minlen = Integer.MAX_VALUE;
			int leftOver = t.length();

			int[] map = new int[256];
			for (int i = 0; i < t.length(); i++) map[t.charAt(i)]++;

			while (end < s.length())
			{
				char charAtEnd = s.charAt(end++);
				
				// if we encounter the char from t
				// we want to decrease the counter of target chars
				if (map[charAtEnd] > 0) leftOver--;

				// decrease counter as we reached the letter
				map[charAtEnd]--;

				// if the substring contains all the target chars
				while (leftOver == 0)
				{
					// if the length of suitable string is less than min
					if (end - start < minlen)
					{
						// update minlen
						minlen = end - start;
						
						// remember the start of the suitable string
						resultstart = start;
					}
					
					char charAtStart = s.charAt(start++);
					
					// if the letter at start is used in s the
					// exact amount as we have in t
					if (map[charAtStart] == 0) leftOver++;
					
					// used it and we move further so increment count
					map[charAtStart]++;
				}
			}

			return minlen == Integer.MAX_VALUE 
				? "" 
				: s.substring(resultstart, resultstart + minlen);
		}
	}
}

