// 1055
// google
class Solution 
{
	public int shortestWay(String source, String target) 
	{
		int sourceLen = source.length(), targetLen = target.length();
		int sourcePointer, targetPointer = 0;
		int subsequencesCount = 0;
		boolean foundSameChar = false;

		while (targetPointer < targetLen)
		{
			sourcePointer = 0;

			while (sourcePointer < sourceLen && targetPointer < targetLen)
			{
				if (source.charAt(sourcePointer) == target.charAt(targetPointer))
				{
					foundSameChar = true;
					sourcePointer++;
					targetPointer++;
				}
				else
				{
					sourcePointer++;
				}
			}

			if (!foundSameChar)
			{
				return -1;
			}
			else
			{
				foundSameChar = false;
			}

			subsequencesCount++;
		}

		return subsequencesCount;
	}
}

