package bs.modified;

/**
 * 153
 */
public class FindMinimumInRotatedSortedArray {
    /**
     * We go as regular binary search, and we notice that if mid element is >
     * a[0], then mid is between 0 and peak element. And if it is < a[n-1] then
     * mid is between peak and n-1 element.
     */
    public static class Solution {
        public int findMin(int[] a) {
            int n = a.length;
            if (a[0] <= a[n - 1]) {
                return a[0];
            }
            int lo = 0;
            int hi = n - 1;
            while (hi - lo >= 0) {
                int mid = lo + (hi - lo) / 2;
                if (a[mid] > a[mid + 1]) {
                    return a[mid + 1];
                }
                if (a[mid] < a[mid - 1]) {
                    return a[mid];
                }
                if (a[mid] >= a[lo]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return a[lo];
        }
    }
}