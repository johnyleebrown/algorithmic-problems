package bs.range;

import java.util.List;

/**
 * LeftmostColumnWithAtLeastAOne
 *
 * ======
 *
 * Task.
 *
 * A binary matrix means that all elements are 0 or 1. For each individual row
 * of the matrix, this row is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return leftmost column
 * index(0-indexed) with at least a 1 in it. If such index doesn't exist, return
 * -1.
 *
 * You can't access the Binary Matrix directly.  You may only access the matrix
 * using a BinaryMatrix interface:
 *
 * BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y)
 * (0-indexed).
 * BinaryMatrix.dimensions() returns a list of 2 elements [n, m], which means
 * the matrix is n * m.
 *
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged
 * Wrong Answer.  Also, any solutions that attempt to circumvent the judge will
 * result in disqualification.
 *
 * For custom testing purposes you're given the binary matrix mat as input in
 * the following four examples. You will not have access the binary matrix
 * directly.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LeftmostColumnWithAtLeastAOne {
    public static class Solution {
        public int leftMostColumnWithOne(BinaryMatrix b) {
            List<Integer> d = b.dimensions();
            int n = d.get(0);
            int m = d.get(1);
            int ans = m;
            for (int i = 0; i < n; i++) {
                ans = Math.min(ans, bs(m, b, i));
            }
            return ans == n ? -1 : ans;
        }

        private int bs(int hi, BinaryMatrix b, int i) {
            int lo = -1;
            while (hi - lo > 1) {
                int mid = lo + (hi - lo) / 2;
                if (b.get(i, mid) < 1) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            return hi;
        }

        private class BinaryMatrix {
            private List<Integer> dimensions() {
                return null;
            }

            private int get(int i, int mid) {
                return 0;
            }
        }
    }
}