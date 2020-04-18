package bs.ternarySearch;

/**
 * 852
 */
public class PeakIndexInAMountainArray {
    public static class Solution {
        public int peakIndexInMountainArray(int[] a) {
            int lo = -1;
            int hi = a.length;
            while (hi - lo > 2) {
                int m1 = lo + (hi - lo) / 3;
                int m2 = lo + 2 * (hi - lo) / 3;
                if (a[m1] > a[m2]) {
                    hi = m2;
                } else {
                    lo = m1;
                }
            }
            return (hi + lo) / 2;
        }
    }
}
