package graph.shortestPaths.bfs.board;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 934
 */
public class ShortestBridge
{
    /*
     * 99 / 93, 7ms
     */
    public int shortestBridge(int[][] A)
    {
        // edge cases
        if (A == null || A.length == 0 || A[0].length == 0) return -1;

        int n = A.length, m = A[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] seen = new boolean[n][m];
        // directions for the navigation
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        // put the perimeter of the first island to the queue for graph.shortestPaths.bfs
        boolean foundIsland = false;
        for (int i = 0; i < n; i++)
        {
            if (foundIsland) break;

            for (int j = 0; j < m; j++)
            {
                if (A[i][j] == 1)
                {
                    dfs(i, j, A, q, dirs, seen, n, m);
                    foundIsland = true;
                    break;
                }
            }
        }

        // another edge case
        if (!foundIsland) return -1;

        int path = 0;

        // graph.shortestPaths.bfs
        while (!q.isEmpty())
        {
            path++;

            int size = q.size();
            for (int k = 0; k < size; k++)
            {
                int[] cell = q.poll();
                for (int[] dir : dirs)
                {
                    int newI = cell[0] + dir[0], newJ = cell[1] + dir[1];

                    // if we ve been there and it's out of bounds we don't go there
                    if (!isValidDirection(newI, newJ, n, m, seen)) continue;

                    // when we find the 2nd island
                    if (A[newI][newJ] == 1) return path;

                    // usual routine
                    seen[newI][newJ] = true;
                    q.add(new int[]{newI, newJ});
                }
            }
        }

        // if we haven't find the path earlier
        return -1;
    }

    private void dfs(int i, int j, int[][] A, Queue<int[]> q, int[][] dirs, boolean[][] seen, int n, int m)
    {
        // if we have reached the water cells, we will add them to the perimeter queue
        // thus decreasing the rounds of graph.shortestPaths.bfs by 1
        if (A[i][j] == 0)
        {
            q.add(new int[]{i, j});
            return;
        }

        seen[i][j] = true;

        for (int[] dir : dirs)
        {
            int newI = i + dir[0], newJ = j + dir[1];
            if (isValidDirection(newI, newJ, n, m, seen))
                dfs(newI, newJ, A, q, dirs, seen, n, m);
        }
    }

    private boolean isValidDirection(int newI, int newJ, int n, int m, boolean[][] seen)
    {
        return isInBounds(newI, newJ, n, m) && !seen[newI][newJ];
    }

    private boolean isInBounds(int i, int j, int n, int m)
    {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

}
/*
[[0,1],[1,0]]
[[0,1,0],[0,0,0],[0,0,1]]
[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
*/
