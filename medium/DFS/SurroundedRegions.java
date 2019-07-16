package medium.dfs;

/**
 * 130
 * https://leetcode.com/problems/surrounded-regions/description/
 * <p>
 * Given a 2D board containing 'X' and 'O' (the letter O),
 * capture all regions surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in
 * that surrounded region.
 * <p>
 * Example:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * <p>
 * After running your function, the board should be:
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * <p>
 * Explanation:
 * <p>
 * Surrounded regions shouldn’t be on the border, which
 * means that any 'O' on the border of the board are not
 * flipped to 'X'. Any 'O' that is not on the border and
 * it is not connected to an 'O' on the border will be
 * flipped to 'X'. Two cells are connected if they are
 * adjacent cells connected horizontally or vertically.
 * <p>
 * <p>
 * Similar Idea:
 * {@link NumberofIslands}, {@link MaxAreaOfIsland}
 */
public class SurroundedRegions
{
    /*
     * 53 / 40
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * Go around perimeter and get all perimeter-connected cells and color them into 3rd color;
     * So all not needed cells will be colored in 3rd color.
     * In the last loop we color O -> X and 3rd color -> O
     */
    class Solution0
    {
        public void solve(char[][] board)
        {
            // edge cases
            if (board == null || board.length == 0 || board[0].length == 0) return;

            // to mark untouchables we could create a separate array
            // but instead we could color them into non 'X' or 'O' char and then reverse it in the end

            // color into 3rd color with bfs
            int n = board.length, m = board[0].length;
            boolean[][] seen = new boolean[n][m];
            for (int j = 0; j < m; j++)
            {
                if (board[0][j] == 'O' && !seen[0][j]) dfs(0, j, seen, n, m, board);
                if (board[n - 1][j] == 'O' && !seen[n - 1][j]) dfs(n - 1, j, seen, n, m, board);
            }
            for (int i = 0; i < n; i++)
            {
                if (board[i][0] == 'O' && !seen[i][0]) dfs(i, 0, seen, n, m, board);
                if (board[i][m - 1] == 'O' && !seen[i][m - 1]) dfs(i, m - 1, seen, n, m, board);
            }

            // capture relevant zeroes and color back
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                {
                    if (board[i][j] == 'O') board[i][j] = 'X';
                    if (board[i][j] == '3') board[i][j] = 'O';
            }
        }

        private void dfs(int i, int j, boolean[][] seen, int n, int m, char[][] board)
        {
            seen[i][j] = true;
            board[i][j] = '3';
            int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            for (int[] dir : dirs)
            {
                int newI = i + dir[0], newJ = j + dir[1];
                if (isInBounds(newI, newJ, n, m) && !seen[newI][newJ] && board[newI][newJ] == 'O')
                    dfs(newI, newJ, seen, n, m, board);
            }
        }

        private boolean isInBounds(int i, int j, int n, int m)
        {
            return i >= 0 && i < n && j >= 0 && j < m;
        }
    }
}
