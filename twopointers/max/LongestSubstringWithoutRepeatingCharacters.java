// 3
class Solution 
{
    public int lengthOfLongestSubstring(String s) 
    {
        int l = 0;
        int maxLen = 0;
        int countRepChar = 0;
        int[] map = new int[256];
         
        for (int r = 0; r < s.length(); r++)
        {
            map[s.charAt(r)]++;
            
            if (map[s.charAt(r)] == 2) // the char began being repeated
            {
                countRepChar++;
            }
            
            while (countRepChar > 0) // moving left pointer to find other solutions
            {
                map[s.charAt(l)]--;
                
                if (map[s.charAt(l)] == 1) // the char stopped being repeated
                {
                    countRepChar--;
                }
                
                l++;
            }
            
            maxLen = Math.max(maxLen, r - l + 1);
        }
        
        return maxLen;
    }
}

