// 3
class Solution 
{
	public int lengthOfLongestSubstring(String s) 
	{
		int start = 0, end = 0, count = 0, maxlen = Integer.MIN_VALUE;
		int[] chars = new int[256];

		while (end < s.length())
		{
			if (chars[s.charAt(end++)]++ > 0) count++;
			while (count > 0) if (chars[s.charAt(start++)]-- > 1) count--;
			maxlen = Math.max(maxlen, end - start);
		}

		return maxlen;
	}
}

