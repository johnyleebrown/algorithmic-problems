package bs.regular;

/**
 * 540
 */
public class SingleElementInASortedArray {
    /**
     * If count of subarray elements is odd it means it contains a single
     * element.
     */
    public static class Solution {
        public int singleNonDuplicate(int[] a) {
            int lo = 0;
            int n = a.length;
            int hi = n - 1;
            while (hi - lo >= 0) {
                int mid = lo + (hi - lo) / 2;
                int x = helper(mid, a);
                if (x == 0) return a[mid];
                if (x == 1) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return a[lo];
        }

        private int helper(int i, int[] a) {
            int n = a.length;
            if (i + 1 < n && a[i] == a[i + 1]) {
                if ((n - 1 - i + 1) % 2 == 0) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (i - 1 >= 0 && a[i] == a[i - 1]) {
                if ((n - 1 - i + 1) % 2 == 0) {
                    return 1;
                } else {
                    return -1;
                }
            } else return 0;
        }
    }
}