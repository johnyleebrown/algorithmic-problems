package bs.range;

/**
 * 34
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public static class Solution {
        public int[] searchRange(int[] a, int t) {
            return new int[]{bs1(a.length, t, a), bs2(a.length, t, a)};
        }

        private int bs1(int n, int x, int[] a) {
            int lo = -1;
            int hi = n;
            while (hi - lo > 1) {
                int mid = lo + (hi - lo) / 2;
                if (a[mid] < x) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            return hi >= n || a[hi] != x ? -1 : hi;
        }

        private int bs2(int n, int x, int[] a) {
            int lo = -1;
            int hi = n;
            while (hi - lo > 1) {
                int mid = lo + (hi - lo) / 2;
                if (a[mid] <= x) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            return lo <= -1 || a[lo] != x ? -1 : lo;
        }
    }
}