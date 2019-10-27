/*
 * 329
 * Google
 */
class Solution
{
    private int[][] dirs = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    private int[][] cache;
    private int n, m;
    public int longestIncreasingPath(int[][] matrix)
    {
        n = matrix.length;
        if (n == 0)
        {
            return 0;
        }
        m = matrix[0].length;
        if (m == 0)
        {
            return 0;
        }
        int max = 1;
        cache = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++)
        {
            max = Math.max(max, f(i, j, matrix));
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
    private int f(int i, int j, int[][] matrix)
    {
        if (cache[i][j] != 0)
        {
            return cache[i][j];
        }
        int max = 1;
        for (int[] dir: dirs)
        {
            int newi = i + dir[0], newj = j + dir[1];
            if (isValid(newi, newj) && matrix[newi][newj] > matrix[i][j])
            {
                max = Math.max(max, f(newi, newj, matrix) + 1);
            }
        }
        cache[i][j] = max;
        return max;
    }
    private boolean isValid(int i, int j)
    {
        return isValidC(i, n) && isValidC(j, m);
    }
    private boolean isValidC(int i, int n)
    {
        return i >= 0 && i < n;
    }
}

