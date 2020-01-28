package twoPointers.regular;

import java.util.HashSet;
import java.util.Set;

/**
 * 1316
 *
 * ======
 *
 * Task.
 *
 * Return the number of distinct non-empty substrings of text that can be written as the concatenation of some string
 * with itself (i.e. it can be written as a + a where a is some string).
 *
 * ======
 *
 * Source: Leetcode
 */
public class DistinctEchoSubstrings
{
	/**
	 * Sliding window, rolling counter.
	 *
	 */
	class Solution
	{
		public int distinctEchoSubstrings(String text)
		{
			Set<String> set = new HashSet<>();
			int n = text.length();

			for (int len = 1; len <= n / 2; len++)
			{
				for (int l = 0, r = len, counter = 0; l < n - len; l++, r++)
				{
					if (text.charAt(l) == text.charAt(r))
					{
						counter++;
					}
					else
					{
						counter = 0;
					}

					if (counter == len)
					{
						set.add(text.substring(l - len + 1, l + 1));
						counter--;
					}
				}
			}

			return set.size();
		}
	}
}
