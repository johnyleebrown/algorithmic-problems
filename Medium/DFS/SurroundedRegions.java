package Medium.DFS;

/**
 * 130
 * https://leetcode.com/problems/surrounded-regions/description/
 *
 * Given a 2D board containing 'X' and 'O' (the letter O),
 * capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in
 * that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border, which
 * means that any 'O' on the border of the board are not
 * flipped to 'X'. Any 'O' that is not on the border and
 * it is not connected to an 'O' on the border will be
 * flipped to 'X'. Two cells are connected if they are
 * adjacent cells connected horizontally or vertically.
 *
 *
 * Similar Idea:
 * {@link NumberofIslands}, {@link MaxAreaOfIsland}
 */
public class SurroundedRegions {
    /**
     * Beats 99.94%
     * Beats 92.35%
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     *
     * Idea:
     * Use 3rd character (or color).
     * Go around perimeter and get all perimeter-connected cells and color them into 3rd color;
     * So all not needed cells will be colored in 3rd color.
     * In the last loop we color O -> X and 3rd color -> O
     *
     */
    class Solution {
        public void solve(char[][] board) {

            if (board.length == 0 || board[0].length == 0) return;

            int n = board.length, m = board[0].length;
            boolean[][] seen = new boolean[n][m];
            int[][] pattern = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

            for (int j = 0; j < m; j++) setThirdColor(0, j, board, seen, n, m, pattern);        // top
            for (int i = 1; i < n; i++) setThirdColor(i, m - 1, board, seen, n, m, pattern);        // right
            for (int j = 0; j < m - 1; j++) setThirdColor(n - 1, j, board, seen, n, m, pattern);        // bottom
            for (int i = 1; i < n - 1; i++) setThirdColor(i, 0, board, seen, n, m, pattern);        // left

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'O') board[i][j] = 'X';
                    if (board[i][j] == '*') board[i][j] = 'O';
                }
            }
        }

        private void dfs(int old_i, int old_j, char[][] board, boolean[][] seen, int n, int m, int[][] pattern) {
            for (int[] cell : pattern) {
                int i = old_i + cell[0], j = old_j + cell[1];
                if (i >= 0 && j >= 0 && i < n && j < m) setThirdColor(i, j, board, seen, n, m, pattern);
            }
        }

        private void setThirdColor(int i, int j, char[][] board, boolean[][] seen, int n, int m, int[][] pattern) {
            if (board[i][j] == 'O' && !seen[i][j]) {
                board[i][j] = '*';
                dfs(i, j, board, seen, n, m, pattern);
            }
        }
    }
}
