import java.util.regex.Pattern;

/**
 * 395
 *
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every
 * character in T appears no less than k times.
 *
 * Solution.
 *
 * Recursively split on characters that appear less than k times, there can not be any answer containing these chars.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters
{
	class Solution
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
			for (int i = 0; i < 256; i++) if (map[i] > 0 && map[i] < k)
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
}
