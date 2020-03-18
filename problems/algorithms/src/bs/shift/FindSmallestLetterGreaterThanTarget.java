package bs.shift;

/**
 * 744
 */
public class FindSmallestLetterGreaterThanTarget
{
	static class Solution
	{
		public char nextGreatestLetter(char[] letters, char target)
		{
			if (letters[0] > target) return letters[0];
			if (letters[letters.length - 1] <= target) return letters[0];
			return binarySearch(letters, target);
		}

		private char binarySearch(char[] letters, char target)
		{
			int start = 0;
			int end = letters.length - 1;
			while (start <= end)
			{
				int mid = start + (end - start) / 2;
				if (letters[mid] <= target)
					start = mid + 1;
				else
					end = mid - 1;
			}
			return letters[start];
		}
	}
}
