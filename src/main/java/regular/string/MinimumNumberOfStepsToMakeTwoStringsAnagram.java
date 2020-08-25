package regular.string;

/**
 * 1347
 *
 * ======
 *
 * Task.
 *
 * Given two equal-size strings s and t. In one step you can choose any
 * character of t and replace it with another character.
 *
 * Return the minimum number of steps to make t an anagram of s.
 *
 * An Anagram of a string is a string that contains the same characters with a
 * different (or the same) ordering.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MinimumNumberOfStepsToMakeTwoStringsAnagram
{
	class Solution
	{
		public int minSteps(String s, String t)
		{
			int[] c1 = new int[26];
			int n = s.length();
			for (int i = 0; i < n; i++)
			{
				c1[s.charAt(i) - 'a']++;
			}
			int m = t.length();
			int[] c2 = new int[26];
			for (int i = 0; i < m; i++)
			{
				c2[t.charAt(i) - 'a']++;
			}
			int res = 0;
			for (int i = 0; i < 26; i++)
			{
				res += Math.abs(c1[i] - c2[i]);
			}
			return res / 2;
		}
	}
}