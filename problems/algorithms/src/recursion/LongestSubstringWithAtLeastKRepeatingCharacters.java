package recursion;

import util.test.Tester;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 395
 *
 * ============
 *
 * Task.
 *
 * Find the length of the longest substring T of a given string (consists of
 * lowercase letters only) such that every character in T appears no less than k
 * times.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters
{
	/**
	 * Recursively split on characters that appear less than k times, there can
	 * not be any answer containing these chars. Pulling out good substrings
	 * with regex and checking them recursively.
	 */
	private static class Solution
	{
		public int longestSubstring(String s, int k)
		{
			if (s == null || s.length() == 0)
			{
				return 0;
			}
			if (k == 0)
			{
				return s.length();
			}

			// get counts of all the characters
			int[] map = new int[256];
			for (int i = 0; i < s.length(); i++)
			{
				map[s.charAt(i)]++;
			}

			// create a regex out of bad chars
			String regexToAdd = "";
			for (int i = 0; i < 256; i++)
				if (map[i] > 0 && map[i] < k)
				{
					regexToAdd += String.valueOf((char) i);
				}

			// finalize regex if there are bad chars
			String regex = "";
			if (regexToAdd.length() != 0)
			{
				regex += "[" + regexToAdd + "]";
			}
			else
			{
				return s.length();
			}

			// split at bad chars
			Pattern pattern = Pattern.compile(regex);
			String[] ar = pattern.split(s);

			// get the longest string
			int max = 0;
			for (String a : ar)
			{
				max = Math.max(max, longestSubstring(a, k));
			}

			return max;
		}
	}

	/**
	 * No regex. Pulling out good substrings and checking them recursively.
	 * Faster.
	 */
	private static class Solution2
	{
		public int longestSubstring(String s, int k)
		{
			if (s == null || s.length() == 0)
			{
				return 0;
			}
			if (k == 0)
			{
				return s.length();
			}

			int[] map = new int[26];
			for (int i = 0; i < s.length(); i++)
			{
				map['z' - s.charAt(i)]++;
			}

			Set<Character> badChars = new HashSet<>();
			for (int i = 0; i < 26; i++)
				if (map[i] > 0 && map[i] < k)
				{
					badChars.add((char) ('z' - i));
				}
			if (badChars.size() == 0)
			{
				return s.length();
			}

			List<String> ar = new ArrayList<>();
			int l = 0;
			for (int r = 0; r < s.length(); r++)
			{
				// found bad after good => want to cut the substring
				if (r > 0 && badChars.contains(s.charAt(r)) && !badChars.contains(s.charAt(r - 1)))
				{
					ar.add(s.substring(l, r));
				}
				// found good after bad => put l there
				else if (r > 0 && !badChars.contains(s.charAt(r)) && badChars.contains(s.charAt(r - 1)))
				{
					l = r;
				}
				// good char is the last one
				else if (r == s.length() - 1 && !badChars.contains(s.charAt(r)))
				{
					ar.add(s.substring(l, r + 1));
				}
			}

			int max = 0;
			for (String a : ar)
			{
				max = Math.max(max, longestSubstring(a, k));
			}

			return max;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution2())
				.add("aaabb",3).expect(3)
				.add("ababbc",2).expect(5)
				.add("cababb",2).expect(5)
				.add("abcabb",2).expect(2)
				.add("cababbbc",3).expect(3)
				.add("abcabbbc",3).expect(3)
				.add("cabcabbb",3).expect(3)
				.add("cabcabbbbc",4).expect(4)
				.add("cabccabccc",4).expect(0)
				.add("cccaaaaaaaabccabccc",8).expect(8)
				.add("aacbbbdc",2).expect(3)
				.run();
	}
}
