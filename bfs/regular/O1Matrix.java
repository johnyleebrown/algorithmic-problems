package medium.bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 542. 01 Matrix
 *
 * the goal is to find the shortest dist from every 1 to a 0
 * since we don't know the target cell in the beginning we can use a regular bfs
 *
 * optimization: idea similar to dijkstra, check if there is already counted a length for cells that are on the way
 * if the length counted on the other cells is becoming bigger then drop them
 */
public class O1Matrix
{
    /*
     * 77 / 97 w/o optimization
     * 75 / 96 w optimization
     */
    public int[][] updateMatrix(int[][] matrix)
    {
        if (matrix == null) return new int[][]{};
        int n = matrix.length, m = matrix[0].length;
        int[][] resultArray = new int[n][m];
        if (n == 0 || m == 0) return resultArray;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        // optimization - pre-fill with maximums for comparison
        /*
		for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) {
			if (matrix[i][j] == 1) resultArray[i][j] = Integer.MAX_VALUE;
		}
        */

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
            {
                if (matrix[i][j] == 1)
                    resultArray[i][j] = bfs(dirs, i, j, matrix, n, m, resultArray);
            }

        return resultArray;
    }

    private int bfs(int[][] dirs, int baseI, int baseJ, int[][] matrix, int n, int m, int[][] resultArray)
    {
        int length = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{baseI, baseJ});
        while (!q.isEmpty())
        {
            int size = q.size();
            // optimization - get a value from resultarray or simply integer.max_value
			/*
			int localMin = resultArray[baseI][baseJ];
            */
            length++;
            for (int k = 0; k < size; k++)
            {
                int[] cell = q.poll();
                // we can go in 4 dirs minding the seen cells
                int i = cell[0], j = cell[1];
                for (int[] dir : dirs)
                {
                    int newI = i + dir[0], newJ = j + dir[1];
                    if (newI >= 0 && newI < n && newJ >= 0 && newJ < m)
                    {
                        if (matrix[newI][newJ] == 0) return length;
                        // optimization - we reached a cell where the length to nearest 0 was already calculated
                        // calculate a minimum for current level
                        /*
                        if (resultArray[newI][newJ] != Integer.MAX_VALUE)
                            localMin = Math.min(resultArray[newI][newJ] + length, localMin);
						if (length < localMin) q.add(new int[]{newI, newJ});
                        */
                        q.add(new int[]{newI, newJ});
                    }
                }
            }

            // optimization - if we haven't found zero on this level
            // we want to return the localmin but only if it is not bigger then current length
            /*
            if (localMin <= length) return localMin;
            */
        }

        return length;
    }

}

