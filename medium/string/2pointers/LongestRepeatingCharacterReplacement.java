/*
 * 424
 *
 * Given a string s that consists of only uppercase English letters, 
 * you can perform at most k operations on that string. In one operation, 
 * you can choose any character of the string and change it to any other 
 * uppercase English character. Find the length of the longest sub-string 
 * containing all repeating letters you can get after performing the above operations.
 * Note: Both the string's length and k will not exceed 104.
 *
 * Solution.
 * Let's assume we don't have k, then min number of changes is a
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

