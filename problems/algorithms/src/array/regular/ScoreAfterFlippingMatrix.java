package array.regular;

/**
 * 861
 *
 * We have a two dimensional matrix A where each value is 0 or 1.
 * A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.
 * After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
 * Return the highest possible score.
 *
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] is 0 or 1.
 */
public class ScoreAfterFlippingMatrix {
    /**
     * 88.31%
     * Time complexity: O(mn)
     * Space complexity: O(n)
     */
    public int matrixScore(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i][0] == 0) flipRow(A[i]);
        }

        for (int i = 0; i < A[0].length; i++) {
            if (moreZeroes(i, A)) flipColumn(i, A);
        }

        int res = 0;
        for (int i = 0; i < A.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < A[0].length; j++) sb.append(A[i][j]);
            res += Integer.parseInt(sb.toString(), 2);
        }

        return res;
    }

    boolean moreZeroes(int col, int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col] == 1) count++;
            else count--;
        }
        return count < 0;
    }

    void flipColumn(int col, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = flip(matrix[i][col]);
        }
    }

    void flipRow(int[] row) {
        for (int i = 0; i < row.length; i++) {
            row[i] = flip(row[i]);
        }
    }

    int flip(int cell) {
        if (cell == 1) return 0;
        else return 1;
    }
}
/*
[0,0,1,1],
[1,0,1,0],
[1,1,0,0]

[1,1,1,1],
[1,0,0,1],
[1,1,1,1]
*/