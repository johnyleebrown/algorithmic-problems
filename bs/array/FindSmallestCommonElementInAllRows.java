class Solution 
{   
    public int smallestCommonElement(int[][] mat) 
    {
        int n = mat.length, m = mat[0].length;
        
        for (int j = 0; j < m; j++) for (int i = 0; i < n; i++)
        {
            if (minExists(mat, mat[i][j])) 
            {
                return mat[i][j];
            }
        }
        
        return -1;
    }
    
    private boolean minExists(int[][] as, int x)
    {
        for (int[] a: as)
        {
            if (Arrays.binarySearch(a, x) < 0) 
            {
                return false;
            }
        }
        
        return true;
    }
}

