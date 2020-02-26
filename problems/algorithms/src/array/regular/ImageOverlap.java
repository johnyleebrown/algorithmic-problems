package array.regular;

import java.util.HashSet;
import java.util.Set;

/**
 * 835
 *
 * Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)
 * We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.
 * (Note also that a translation does not include any kind of rotation.)
 * What is the largest possible overlap?
 *
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 */
public class ImageOverlap {
    /**
     * Time complexity: O(n^3)
     * Space complexity: O(n)
     */
    class Solution1 {
        public int largestOverlap(int[][] A, int[][] B) {
            Set<int[]> set = new HashSet<>();
            for (int i = 0 ; i < A.length ; i++) {
                for (int j = 0 ; j < A.length ; j++) {
                    if (A[i][j] == 1) set.add(new int[]{i, j});
                }
            }

            int max = 0;
            for (int i = -B.length + 1 ; i < B.length + B.length - 1 ; i++) {
                for (int j = -B.length + 1 ; j < B.length + B.length - 1 ; j++) {
                    int localMax = 0;
                    for (int[] cell : set) {
                        if (i + cell[0] < B.length && i + cell[0] >= 0
                                && j + cell[1] < B.length && j + cell[1] >= 0
                                && B[i + cell[0]][j + cell[1]] == 1) localMax++;
                    }
                    max = Math.max(max, localMax);
                }
            }

            return max;
        }
    }
}
