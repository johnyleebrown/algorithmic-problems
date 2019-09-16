// 3
class Solution 
{
    public int lengthOfLongestSubstring(String s) 
    {
        int start = 0, end = 0; // start and end of the sliding window
        int maxlen = Integer.MIN_VALUE; // the longest subarray without rep chars
        int repeatingCharCounter = 0; // counter of repeating characters
        int[] chars = new int[256]; // to keep the counts of chars
        
        while (end < s.length())
        {
            if (chars[s.charAt(end++)]++ > 0) // if we stumbled upon a diplicate 
				repeatingCharCounter++;
            
			
			while (repeatingCharCounter > 0) // if we have a duplicated in sliding window
                // increasing start while there are no duplicates
				if (chars[s.charAt(start++)]-- > 1) // found dupliate
					repeatingCharCounter--;
            
			maxlen = Math.max(maxlen, end - start);
        }
        
        return maxlen == Integer.MIN_VALUE ? 0 : maxlen;
    }
}

