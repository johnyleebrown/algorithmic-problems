// 76
public class MinimumWindowSubstring
{
	class Solution 
	{
		public String minWindow(String s, String t) 
		{
			if (s.length() == 0 || t.length() == 0 
					|| t.length() > s.length()) return "";
			if (t.equals(s)) return s;
			
			int n = s.length();
			int tcount = t.length();
			int rescount = Integer.MAX_VALUE;
			int i = 0, j = 0;
			int res = "";

			Map<Character, Integer> m = new HashMap<>();
			for (char c: t.toCharArray())
			{
				m.put(c, m.getOrDefault(c, 0) + 1);
			}

			while (j < n)
			{
				// decr count of letter if exists
				char cur = s.charAt(j);
				if (m.containsKey(cur))
				{
					m.put(cur, m.get(cur) - 1);
					if (m.get(cur) >= 0) tcount--;
				}

				j++;
				
				// if found all letters
				// start to move i looking for a better min
				// but first capture substring
				// then move i
				// and check the letters	
				if (tcount == 0)
				{
					if (j - i < res.length())
					{
						res = s.substring(i, j);
					}

					char cur2 = s.charAt(i);
					if (m.contains(cur2))
					{
						m.put(cur, m.get(cur) + 1);
						if (m.get(cur) >= 0) tcount++;
					}

					i++;
				}
			}
			// 7
			// 0
			//  i    j
			// ADOBECODEBANC		
			// A 1 |0| |1| |0|
			// B 1 |0| |-1| |0|
			// C 1 |0|	
			return res;
		}
	}
}
/*
Given a string S and a string T, find the minimum window in S 
which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, 
return the empty string "".
If there is such window, you are guaranteed that there will 
always be only one unique minimum window in S.
*/
