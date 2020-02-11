package graph.shortestPaths.bfs.board;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.List;
import java.util.Arrays;

/**
 * 417
 */
public class PacificAtlanticWaterFlow
{
	// we add all the perimeter cells into 2 queues
	// one queue for pacific and the other for atlantic
	// we will do a graph.shortestPaths.bfs for each of those queues
	// in the result we will check if each cell is in both of those queues
	public static List<List<Integer>> solution(int[][] matrix)
	{
		final List<List<Integer>> resultList = new ArrayList<>();
		// edge cases
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return resultList;
        int n = matrix.length, m = matrix[0].length;
			
		// queues to iterate over to different streams
		// and seen arrays
		Queue<int[]> pacificQ = new LinkedList<>(), atlanticQ = new LinkedList<>();
		boolean[][] pacificSeen = new boolean[n][m], 
			atlanticSeen = new boolean[n][m];
		
		// fill with base data
		// fill the verticals
		for (int i = 0; i < n; i++) 
		{
			pacificQ.add(new int[]{i, 0}); atlanticQ.add(new int[]{i, m - 1});
			pacificSeen[i][0] = atlanticSeen[i][m - 1] = true;
		}
		// fill the horizontals
		for (int j = 0; j < m; j++) 
		{
			pacificQ.add(new int[]{0, j}); atlanticQ.add(new int[]{n - 1, j});
			pacificSeen[0][j] = atlanticSeen[n - 1][j] = true;
		}
		// FYI we will not look at repeating corner cells cuz we have seen arrs
		
		int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
		bfs(pacificQ, n, m, pacificSeen, matrix, dirs);
		bfs(atlanticQ, n, m, atlanticSeen, matrix, dirs);
 		
		fillResult(atlanticSeen, pacificSeen, resultList);
			
		return resultList;
	}

	// add all the close elements where we can go to the queue 
	private static void bfs(Queue<int[]> q, int n, int m, boolean[][] seen, int[][] matrix, int[][] dirs)
	{
		while (!q.isEmpty())
		{
            int[] cell = q.poll(); int i = cell[0], j = cell[1];
            for (int[] dir: dirs)
            {
                int movedI = i + dir[0], movedJ = j + dir[1];
                if (movedI < 0 || movedJ < 0 || movedI >= n || movedJ >= m ||
                   seen[movedI][movedJ] || matrix[movedI][movedJ] < matrix[i][j]) continue;
                q.add(new int[]{movedI, movedJ});
                seen[movedI][movedJ] = true;
            }
		}
	}

	private static void fillResult(boolean[][] atlArr, boolean[][] pacArr, List<List<Integer>> list)
	{
		for (int i = 0; i < atlArr.length; i++) for (int j = 0; j < atlArr[0].length; j++) {
			if (atlArr[i][j] && pacArr[i][j]) list.add(Arrays.asList(i, j));
		}
	}

}

