package bs;

/**
 * 774
 *
 * ======
 *
 * Task.
 *
 * On a horizontal number line, we have gas stations at positions stations[0],
 * stations[1], ..., stations[N-1], where N = stations.length.
 *
 * Now, we add K more gas stations so that D, the maximum distance between
 * adjacent gas stations, is minimized.
 *
 * Return the smallest possible value of D.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MinimizeMaxDistanceToGasStation {
    /**
     * We want to find dist with binary search.
     */
    public static class Solution {
        public double minmaxGasDist(int[] a, int k) {
            double eps = 1e-6;
            double lo = -1;
            double hi = 1e8 + 1;
            while (hi - lo > eps) {
                double mid = lo + (hi - lo) / 2;
                if (k < countStationsForMaxDist(a, mid)) { // dist < x
                    lo = mid;
                } else { // dist >= x
                    hi = mid;
                }
            }
            return hi;
        }

        private int countStationsForMaxDist(int[] a, double maxDist) {
            int n = a.length;
            int count = 0; // count of stations we can insert
            for (int i = 0; i < n - 1; i++) {
                count += Math.ceil((a[i + 1] - a[i]) / maxDist) - 1;
            }
            return count;
        }
    }
}