package graph.dfs;

/**
 * 695
 */
public class MaxAreaOfIsland {
    class Solution {
        private int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
        private int n, m;

        public int maxAreaOfIsland(int[][] a) {
            int maxArea = 0;
            n = a.length;
            m = a[0].length;
            boolean[][] seen = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    maxArea = Math.max(maxArea, dfs(i, j, a, seen));
                }
            }
            return maxArea;
        }

        private int dfs(int i, int j, int[][] a, boolean[][] seen) {
            if (a[i][j] != 1 || seen[i][j]) return 0;
            seen[i][j] = true;
            int area = 1;
            for (int[] c : dirs) {
                int ni = i + c[0], nj = j + c[1];
                if (ni < 0 || ni >= n || nj < 0 || nj >= m) continue;
                area += dfs(ni, nj, a, seen);
            }
            return area;
        }
    }
}