/*
 * 424
 *
 * Let's assume we don't have k, then min number of changes is 
 * length of the entire string - number of times of the maximum occurring character in the string.
 * Given this we can apply most k changes constraint like so:
 * we want to find max(end - start + 1) when (end - start + 1) - maxNumberOfSameChars <= k.
 */
class Solution 
{
    public int characterReplacement(String s, int k) 
    {
        int start = 0, end = -1;
        int maxCountOfRepeatedChar = Integer.MIN_VALUE;
        int maxLen = Integer.MIN_VALUE;
        int[] map = new int[91];
        
        while (end + 1 < s.length())
        {
            end++;
            map[s.charAt(end)]++;
            maxCountOfRepeatedChar = Math.max(maxCountOfRepeatedChar, map[s.charAt(end)]);
            
            while ((end - start + 1) - maxCountOfRepeatedChar > k)
            {
                map[s.charAt(start)]--;
                start++;
            }
            
            maxLen = Math.max(maxLen, end - start + 1);
        }
        
        return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
    }
}

