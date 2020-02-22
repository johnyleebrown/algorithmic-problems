package twoPointers.slidingWindow.anagram;

import util.test.Tester;

import java.util.*;

/**
 * 30
 *
 * ======
 *
 * Task.
 *
 * You are given a string, s, and a list of words, words, that are all of the
 * same length. Find all starting indices of substring(s) in s that is a
 * concatenation of each word in words exactly once and without any intervening
 * characters.
 */
public class SubstringWithConcatenationOfAllWords
{
	public static class Solution
	{
		public List<Integer> findSubstring(String s, String[] words)
		{
			List<Integer> res = new ArrayList<>();
			if (s.length() == 0 || words.length == 0) return res;
			int n = s.length();
			int m = words[0].length();
			Map<String, Integer> map = new HashMap<>();
			for (String x : words) map.put(x, map.getOrDefault(x, 0) + 1);
			int totalUniqueWords = map.size();
			int targetLength = m * words.length;

			// idea is to step by not the regular length (1), but step by the length of words[0]
			for (int start = 0; start < m; start++)
			{
				int uniqueWordsCount = 0;
				int l = start;
				Map<String, Integer> lmap = new HashMap<>();

				for (int r = start; r <= n - m; r += m)
				{
					String curr = getCur(s, r, r + m);
					if (map.containsKey(curr))
					{
						lmap.put(curr, lmap.getOrDefault(curr, 0) + 1);
						if (map.get(curr) - lmap.get(curr) == 0)
							uniqueWordsCount++;
					}

					if (targetLength == r - l)
					{

						String curl = getCur(s, l, l + m);
						if (map.containsKey(curl))
						{
							lmap.put(curl, lmap.getOrDefault(curl, 0) - 1);
							if (map.get(curl) - lmap.get(curl) == 1)
								uniqueWordsCount--;
						}

						l += m;
					}

					if (uniqueWordsCount == totalUniqueWords)
						res.add(l);
				}
			}

			return res;
		}

		private String getCur(String s, int i, int j)
		{
			return s.substring(i, j);
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo", "barr", "wing", "ding", "wing"}).expect(Arrays.asList(13))
				.add("aaaaaa", new String[]{"aaa", "aaa"}).expect(Arrays.asList(0))
				.add("aaaaaaaa", new String[]{"aa", "aa", "aa"}).expect(Arrays.asList(0, 2, 1))
				.run();
	}
}
