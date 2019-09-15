// 1004
// Series=2pointers
class Solution 
{
    public int longestOnes(int[] A, int K) 
    {
        int start = 0, end = 0, ones = 0, maxlen = Integer.MIN_VALUE;
        
        while (end < A.length)
        {
            if (A[end] == 1) ones++;
            if ((end - start + 1) - ones > K) if (A[start++] == 1) ones--;
            maxlen = Math.max(maxlen, end - start + 1);
            end++;
        }
        
        return maxlen;
    }
}

