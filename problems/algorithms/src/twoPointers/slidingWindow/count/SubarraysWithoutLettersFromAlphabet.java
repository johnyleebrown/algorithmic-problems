package twoPointers.slidingWindow.count;

import util.test.Tester;

import java.util.HashMap;
import java.util.Map;

/**
 * Google_Interview_1
 *
 * ======
 *
 * Task.
 *
 * Suppose you have a string, haystack, and a set of characters which may or may
 * not appear in that string, alphabet. (No characters appear in alphabet more
 * than once, because it's a set.) How many non-empty sub-strings of haystack do
 * not contain every character in alphabet? Write a function that accepts
 * haystack and alphabet as input and returns an answer to this question as an
 * integer.
 *
 * ======
 *
 * Source: Unknown
 */
public class SubarraysWithoutLettersFromAlphabet
{
	/**
	 * Sliding window.
	 */
	private static class Solution
	{
		public int numSubarraysWithoutLettersFromAlphabet(String haystack, char[] alphabet)
		{
			int n = haystack.length();
			int l = 0;
			int res = 0;
			int c = 0;

			// we use map so we could perform calculations on the chars from the 'alphabet'
			// we could do set and char[26] arr instead which is faster
			Map<Character, Integer> m = new HashMap<>();
			for (char ch : alphabet)
			{
				m.put(ch, 0);
			}

			for (int r = 0; r < n; r++)
			{
				char charAtR = haystack.charAt(r);
				if (m.containsKey(charAtR))
				{
					m.put(charAtR, m.get(charAtR) + 1);
					if (m.get(charAtR) == 1)
					{
						c++;
					}
				}

				// bad condition - found a letter from alphabet
				while (c == alphabet.length)
				{
					char charAtL = haystack.charAt(l);
					if (m.containsKey(charAtL))
					{
						m.put(charAtL, m.get(charAtL) - 1);
						if (m.get(charAtL) == 0)
						{
							c--;
						}
					}

					l++;
				}

				res += r - l + 1;
			}

			return res;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add("cab", new char[]{'a', 'c'}).expect(4)
				.add("albuquerque", new char[]{'a', 'b', 'q'}).expect(59)
				.add("baculum", new char[]{'a', 'b', 'c'}).expect(23)
				.add("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc", new char[]{'a', 'b', 'c'}).expect(107)
				.run();
	}
}