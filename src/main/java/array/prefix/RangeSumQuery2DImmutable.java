package array.prefix;

/**
 * 304
 */
public class RangeSumQuery2DImmutable {

  /**
   * Prefix matrix, same as prefix sum, but with slight mods.
   */
  static class NumMatrix {

    int[][] m;

    public NumMatrix(int[][] matrix) {
      m = new int[matrix.length][matrix[0].length];
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
          m[i][j] = (i > 0 ? m[i - 1][j] : 0)
                    + (j > 0 ? m[i][j - 1] : 0)
                    - (j > 0 && i > 0 ? m[i - 1][j - 1] : 0)
                    + matrix[i][j];
        }
      }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
      int upperright = row1 - 1 >= 0 ? m[row1 - 1][col2] : 0;
      int lowerleft = col1 - 1 >= 0 ? m[row2][col1 - 1] : 0;
      int upperleft = col1 - 1 >= 0 && row1 - 1 >= 0 ? m[row1 - 1][col1 - 1] : 0;
      return m[row2][col2] - upperright - lowerleft + upperleft;
    }
  }
}
