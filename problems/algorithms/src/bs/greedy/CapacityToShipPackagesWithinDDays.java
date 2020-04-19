package bs.greedy;

/**
 * 1011
 */
public class CapacityToShipPackagesWithinDDays {
    /**
     * We operate with used days here - so if we used > days than needed =>
     * capacity is too small => need to increase it, lo = mid + 1, or else.
     *
     * Use different version of binary search because we don't discard mid, when
     * we change hi.
     */
    public static class Solution {
        public int shipWithinDays(int[] a, int d) {
            int lo = getMax(a);
            int hi = 25_000_001;
            while (hi - lo > 0) {
                int mid = lo + (hi - lo) / 2;
                if (usedMoreDaysThanNeeded(mid, a, d)) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo;
        }

        private int getMax(int[] a) {
            int max = a[0];
            for (int i = 0; i < a.length; i++) {
                max = Math.max(max, a[i]);
            }
            return max;
        }

        private boolean usedMoreDaysThanNeeded(int cap, int[] a, int d) {
            int sum = 0;
            int day = 0;
            for (int i = 0; i < a.length; i++) {
                sum += a[i];
                if (sum > cap) {
                    sum = a[i];
                    day++;
                }
            }
            return day >= d;
        }
    }
}
