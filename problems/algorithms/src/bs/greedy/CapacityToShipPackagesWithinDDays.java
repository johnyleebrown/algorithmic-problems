package bs.greedy;

/**
 * 1011
 *
 * ======
 *
 * Task.
 *
 * Binary search solution, so we are trying to pick the right value. After the
 * pick we check if all weights fit, if they don't, we increase the lower
 * bound.
 */
public class CapacityToShipPackagesWithinDDays {
    public static class Solution {
        public int shipWithinDays(int[] a, int d) {
            int lo = 1;
            int hi = 25_000_000;
            while (hi - lo >= 0) {
                int mid = lo + (hi - lo) / 2;
                if (usedMoreDaysThanNeeded(mid, a, d)) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return lo;
        }

        private boolean usedMoreDaysThanNeeded(int cap, int[] a, int d) {
            int sum = 0;
            int day = 0;
            for (int i = 0; i < a.length; i++) {
                // can't take package if it is > than capacity (capacity is too small)
                if (a[i] > cap) {
                    return true;
                }
                sum += a[i];
                if (sum > cap) {
                    sum = a[i];
                    day++;
                }
            }
            // if used > days than needed => cap is too small=>need to increase it
            return day >= d;
        }
    }
}
