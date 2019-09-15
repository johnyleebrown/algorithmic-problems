// 487
// Series=2pointers
class Solution 
{
    public int findMaxConsecutiveOnes(int[] nums) 
	{
        int start = 0, end = 0, ones = 0, zeros = 0, maxlen = Integer.MIN_VALUE;
        
		while (end < nums.length)
        {
            if (nums[end] == 0) zeros++;
            if (zeros > 1) if (nums[start++] == 0) zeros--;
            maxlen = Math.max(maxlen, end - start + 1);
            end++;
        }

        return maxlen == Integer.MIN_VALUE ? 0 : maxlen; 
    }
}

