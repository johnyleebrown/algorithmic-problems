package regular.string;

/**
 * 344
 */
public class ReverseString
{
	class Solution
	{
		public void reverseString(char[] s)
		{
			helper(0, s.length, s);
		}

		private void helper(int i, int n, char[] s)
		{
			// base case is when we crossed the middle point - n=4, n/2=2(ok), n=5, n/2=2(ok)
			if (i == n / 2) return;

			// swap
			char temp = s[i];
			s[i] = s[n - 1 - i];
			s[n - 1 - i] = temp;

			helper(i + 1, n, s);
		}
	}
}
