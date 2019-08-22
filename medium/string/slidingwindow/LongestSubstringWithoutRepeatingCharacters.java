// 3
class Solution 
{
	public int lengthOfLongestSubstring(String s) 
	{
		int i = 0, j = 0, count = 0, maxlen = Integer.MIN_VALUE;
		int[] chars = new int[256];
		while (j < s.length())
		{
			if (chars[s.charAt(j++)]++ > 0) count++;

			while (count > 0)
			{
				if (chars[s.charAt(i++)]-- > 1) count--;
				maxlen = Math.max(maxlen, j - i);
			}
		}
		return Math.max(j - i, maxlen);
	}
}

