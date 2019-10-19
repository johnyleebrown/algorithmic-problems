/*
 * 221
 * Google
 *
 * Dp, keep aux array for max count at each cell, keep a global max var as well.
 * The value at cache cell is minimum across top, left and right cells.
 * If zero, there can't be any square so no action.
 */
class Solution
{
    public int maximalSquare(char[][] matrix)
    {
        int n = matrix.length;
		if (n == 0)
        {
            return 0;
        }

        int max = Integer.MIN_VALUE;
		int m = matrix[0].length;
        int[][] cache = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) for (int j = 1; j <= m; j++)
        {
            if (matrix[i - 1][j - 1] == '1')
            {
                cache[i][j] = min(cache[i - 1][j], cache[i][j - 1], cache[i - 1][j - 1]) + 1;
				max = Math.max(max, cache[i][j]);
            }
        }

        return max == Integer.MIN_VALUE ? 0 : max*max;
    }

    private int min(int a, int b, int c)
    {
        return Math.min(a, Math.min(b, c));
    }
}

