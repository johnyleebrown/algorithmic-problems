package string.regular;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 809
 */
public class ExpressiveWords
{
	class Solution
	{
		private final int VALID_MIN_LEN = 3;
		private String source;

		public int expressiveWords(String S, String[] words)
		{
			source = S;
			int c = 0;
			for (String s : words)
			{
				if (isValidString(s))
				{
					c++;
				}
			}
			return c;
		}

		private boolean isValidString(String target)
		{
			int n = source.length(), m = target.length();
			if (m == 0 && n != 0)
			{
				return false;
			}
			if (m > n)
			{
				return false;
			}
			int i = 0, j = 0;
			while (i < n)
			{
				char curChar = source.charAt(i);
				int sourceCount = 0, targetCount = 0;
				while (equalsCurChar(i, curChar) || equalsCurChar(j, target, curChar))
				{
					if (equalsCurChar(i, curChar))
					{
						i++;
						sourceCount++;
					}
					if (equalsCurChar(j, target, curChar))
					{
						j++;
						targetCount++;
					}
				}
				if (!isValidRangeOfChars(sourceCount, targetCount))
				{
					return false;
				}
			}
			return true;
		}

		private boolean equalsCurChar(int i, char cur)
		{
			return i < source.length() && source.charAt(i) == cur;
		}

		private boolean equalsCurChar(int i, String source, char cur)
		{
			return i < source.length() && source.charAt(i) == cur;
		}

		private boolean isValidRangeOfChars(int countSourceChar, int countTargetChar)
		{
			if (countSourceChar == countTargetChar)
			{
				return true;
			}
			return countSourceChar >= VALID_MIN_LEN && countSourceChar >= countTargetChar;
		}
	}

	/**
	 * LC 16ms Time complexity: O(n*m) Space complexity: O(n)
	 */
	static class Solution1
	{
		public static int expressiveWords(String S, String[] words)
		{
			Queue<int[]> q = new LinkedList<>();
			for (int i = 0; i < S.length(); i++)
			{
				int[] a = new int[]{S.charAt(i), 1};
				while (i < S.length() - 1 && a[0] == S.charAt(i + 1))
				{
					i++;
					a[1]++;
				}
				q.add(a);
			}

			int count = 0;
			for (String w : words)
				if (helper(w, q))
					count++;

			return count;
		}

		private static boolean helper(String w, Queue<int[]> q)
		{
			int j = 0;

			for (int[] a : q)
			{
				if (j >= w.length()) return false;
				if (a[1] <= 2)
				{ // check if the sub strings are equals
					if (w.charAt(j) != a[0]) return false;
					if (a[1] == 2)
					{
						if (w.charAt(j + 1) != a[0]) return false;
						j++;
					}
					j++;
				}
				else
				{ // move to the next letter in w
					int k = j;
					while (j < w.length() && w.charAt(j) == a[0]) j++;
					if (j - k > a[1]) return false;
				}
			}

			return j >= w.length();
		}
	}

	/**
	 * LC 8ms Time complexity: O(n*m) Space complexity: O(n)
	 */
	static class Solution2
	{
		public static int expressiveWords(String S, String[] words)
		{
			int ans = 0;

			for (String word : words)
				if (isok(S, word))
					ans++;

			return ans;
		}

		static boolean isok(String a, String b)
		{
			int i = 0;
			int j = 0;

			while (i < a.length() && j < b.length())
			{
				int ii = i + 1;
				int jj = j + 1;

				while (ii < a.length() && a.charAt(ii) == a.charAt(i)) ii++;
				while (jj < b.length() && b.charAt(jj) == b.charAt(j)) jj++;

				int cnt1 = ii - i;
				int cnt2 = jj - j;
				if (cnt1 < 3 && cnt1 != cnt2) return false;
				if (cnt1 >= 3 && cnt1 < cnt2) return false;

				i = ii;
				j = jj;
			}

			return i >= a.length() && j >= b.length();
		}
	}
}