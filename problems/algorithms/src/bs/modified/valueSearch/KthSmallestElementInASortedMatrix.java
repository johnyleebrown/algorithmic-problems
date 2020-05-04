package bs.modified.valueSearch;

/**
 * 378
 *
 * ======
 *
 * Task.
 *
 * Given a n x n matrix where each of the rows and columns are sorted in
 * ascending order, find the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth
 * distinct element.
 *
 * ======
 *
 * Source: Leetcode
 */
public class KthSmallestElementInASortedMatrix {
    /**
     * Binary search - find kth smallest means that if count < k - this doesn't
     * suit us, but if count >=k - from that we need the first one.
     */
    public static class Solution {
        public int kthSmallest(int[][] ar, int k) {
            int n = ar.length;
            int lo = ar[0][0] - 1;
            int hi = ar[n - 1][n - 1] + 1;
            while (hi - lo > 1) {
                int mid = lo + (hi - lo) / 2;
                int count = lessEqual(ar, mid);
                if (count < k) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            return hi;
        }

        public int lessEqual(int[][] ar, int mid) {
            int c = 0;
            int j = ar[0].length - 1;
            for (int i = 0; i < ar.length; i++) {
                while (j >= 0 && ar[i][j] > mid) j--;
                c += (j + 1);
            }
            return c;
        }
    }
}