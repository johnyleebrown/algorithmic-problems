package array.regular;

import java.util.HashSet;
import java.util.Set;

/**
 * 48
 * <p>
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 */
public class RotateImage {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            int newj = n - 1;
            int newi = 0;
            Set<javafx.util.Pair> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (set.size() == n * n) return;
                    if (!set.contains(new javafx.util.Pair(i, j)))
                        replace(matrix, i, j, newi + j, newj - i, matrix[i][j], set);
                }
            }

        }

        private void replace(int[][] matrix, int originalI, int originalJ, int newi, int newj, int num, Set<javafx.util.Pair> set) {
            set.add(new javafx.util.Pair(newi, newj));
            int temp = matrix[newi][newj];
            matrix[newi][newj] = num;
            if (newi == originalI && newj == originalJ) return;
            replace(matrix, originalI, originalJ, 0 + newj, (matrix.length - 1) - newi, temp, set);
        }
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution2 {
        public void rotate(int[][] matrix) {
            if (matrix == null || matrix.length == 0) return;
            if (matrix.length != matrix[0].length) return;
            rotateUtil(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1);
        }

        public void rotateUtil(int[][] matrix, int rowSt, int rowEnd, int colSt, int colEnd) {
            if (rowSt >= rowEnd) return;
            int n = colEnd - colSt;
            for (int i = 0; i < n; i++) {
                swap(matrix, rowSt, colSt + i, rowSt + i, colEnd);
                swap(matrix, rowEnd - i, colSt, rowEnd, colEnd - i);
                swap(matrix, rowSt, colSt + i, rowEnd, colEnd - i);
            }
            rotateUtil(matrix, rowSt + 1, rowEnd - 1, colSt + 1, colEnd - 1);
        }

        public void swap(int[][] matrix, int row1, int col1, int row2, int col2) {
            int temp = matrix[row1][col1];
            matrix[row1][col1] = matrix[row2][col2];
            matrix[row2][col2] = temp;
        }
    }

    public static void main(String[] args) {
        int[][] s = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
    }
}
