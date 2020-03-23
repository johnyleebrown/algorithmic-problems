package bs.shift;

/**
 * 744
 */
public class FindSmallestLetterGreaterThanTarget
{
	class Solution
	{
		public char nextGreatestLetter(char[] letters, char target)
		{
			if (target < letters[0] || target >= letters[letters.length - 1])
			{
				return letters[0];
			}
			return letters[bs(letters, target)];
		}

		private int bs(char[] a, char x)
		{
			int lo = 0;
			int hi = a.length - 1;
			while (lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;
				if (a[mid] <= x)
				{
					lo = mid + 1;
				}
				else
				{
					hi = mid - 1;
				}
			}
			return lo;
		}
	}
}
