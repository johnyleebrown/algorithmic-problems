package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 131
 */
public class PalindromePartitioning
{
	/**
	 * aab - check if a is a pali, no, check if aa is pali. if yes, check if the
	 * b is pali, no, check if..
	 */
	class Solution
	{
		private List<List<String>> combinations = new ArrayList<>();

		public List<List<String>> partition(String s)
		{
			generate(0, new ArrayList<>(), 0, s);
			return combinations;
		}

		private void generate(int start, List<String> curList, int curLen, String s)
		{
			if (s.length() == curLen)
			{
				combinations.add(new ArrayList<>(curList));
			}
			else
			{
				for (int i = start; i < s.length(); i++)
				{
					String subs = s.substring(start, i + 1);

					if (isPalindrome(subs))
					{
						curList.add(subs);

						// go to next ind to check if the rest of the str is pali
						// curLen + i + 1 - start = count of how many added so far
						generate(i + 1, curList, curLen + i + 1 - start, s);

						curList.remove(curList.size() - 1);
					}
				}
			}
		}

		// could send here just the indexes
		private boolean isPalindrome(String s)
		{
			int n = s.length();
			for (int i = 0; i < n / 2; i++)
			{
				if (s.charAt(i) != s.charAt(n - 1 - i)) return false;
			}

			return true;
		}
	}
}
/*
""
"a"
"ab"
"aab"
"abb"
"aaa"
*/