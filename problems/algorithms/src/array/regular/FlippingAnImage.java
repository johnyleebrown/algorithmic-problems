package array.regular;

/**
 * 832
 *
 * Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
 * To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
 */
public class FlippingAnImage {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        int[][] ans = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                ans[i][j] = 1 - A[i][n - 1 - j];

        return ans;
    }
}
