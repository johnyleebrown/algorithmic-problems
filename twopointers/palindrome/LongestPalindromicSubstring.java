/*
 * 5
 *
 * Company: Google.
 *
 * The idea is to explore each character in both directions, we could 
 * have 2 cases, if the palindrome length is odd or it is even, if it 
 * is odd 2 pointers i and j will be k and k + 1, otherwise they will be k and k.
 */
class Solution 
{
	private int finalI, finalJ, max = Integer.MIN_VALUE;

    public String longestPalindrome(String s) 
	{
		if (s.length() == 0) 
		{
			return "";
		}

		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			explore(i, i, s);	
			explore(i, i + 1, s);	
		}

		return s.substring(finalI, finalJ + 1);
    }

	private void explore(int baseI, int baseJ, String s)
	{
		int i = baseI, j = baseJ;
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j))
		{
			if (j - i + 1 > max)
			{
				max = j - i + 1;
				finalI = i;
				finalJ = j;
			}

			i--;
			j++;
		}		
	}
}
