package twoPointers.slidingWindow.anagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30
 *
 * ======
 *
 * Task.
 *
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
 * substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 */
public class SubstringWithConcatenationOfAllWords
{
	static class Solution
	{
		public List<Integer> findSubstring(String s, String[] words)
		{
			List<Integer> result = new ArrayList<>();
			if (words.length == 0)
			{
				return result;
			}

			Map<String, Integer> originalMap = new HashMap<>();
			for (String str : words)
			{
				originalMap.put(str, originalMap.getOrDefault(str, 0) + 1);
			}

			int wordLength = words[0].length();
			int targetLength = wordLength * words.length;

			for (int start = 0; start < wordLength; start++)
			{
				int l = start;
				int count = 0;
				Map<String, Integer> currentMap = new HashMap<>();

				for (int r = wordLength + start - 1; r < s.length(); r += wordLength)
				{
					String substringEndingAtRightPointer = s.substring(r + 1 - wordLength, r + 1);
					if (originalMap.containsKey(substringEndingAtRightPointer))
					{
						currentMap.put(substringEndingAtRightPointer, currentMap.getOrDefault(substringEndingAtRightPointer, 0) + 1);
						if (originalMap.get(substringEndingAtRightPointer) - currentMap.get(substringEndingAtRightPointer) >= 0)
						{
							count++;
						}
					}

					while (r - l + 1 == targetLength)
					{
						if (count == words.length)
						{
							result.add(l);
						}

						String substringStartAtLeftPointer = s.substring(l, l + wordLength);
						if (originalMap.containsKey(substringStartAtLeftPointer))
						{
							currentMap.put(substringStartAtLeftPointer, currentMap.getOrDefault(substringStartAtLeftPointer, 0) - 1);
							if (originalMap.get(substringStartAtLeftPointer) - currentMap.get(substringStartAtLeftPointer) >= 1)
							{
								count--;
							}
						}

						l += wordLength;
					}
				}
			}

			return result;
		}
	}
}
