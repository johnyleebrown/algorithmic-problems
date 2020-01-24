package bs;

/**
 * 744
 */
class FindSmallestLetterGreaterThanTarget
{
	static class Solution
	{
		public char nextGreatestLetter(char[] letters, char target)
		{
			if (target >= letters[letters.length - 1])
			{
				target = letters[0];
			}
			else
			{
				target += 1;
			}

			int lo = 0, hi = letters.length;
			while (lo < hi)
			{
				int mid = lo + (hi - lo) / 2;

				if (letters[mid] == target)
				{
					return letters[mid];
				}
				else if (letters[mid] < target)
				{
					lo = mid + 1;
				}
				else
				{
					hi = mid;
				}
			}

			return letters[lo];
		}
	}
}
