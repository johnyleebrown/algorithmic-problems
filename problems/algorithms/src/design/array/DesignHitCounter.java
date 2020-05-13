package design.array;

/**
 * 362
 *
 * ======
 *
 * Task.
 *
 * Design a hit counter which counts the number of hits received in the past 5
 * minutes.
 *
 * Each function accepts a timestamp parameter (in seconds granularity) and you
 * may assume that calls are being made to the system in chronological order
 * (ie, the timestamp is monotonically increasing). You may assume that the
 * earliest timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DesignHitCounter {
    /**
     * Keep the cache size 300. Collect all hits with times > 300 - current.
     */
    public static class HitCounter {
        int[] hits;
        int[] times;
        int k = 300;

        public HitCounter() {
            hits = new int[k + 1];
            times = new int[k + 1];
        }

        public void hit(int t) {
            int i = t % k;
            if (times[i] == t) {
                hits[i]++;
            } else {
                hits[i] = 1;
                times[i] = t;
            }
        }

        public int getHits(int t) {
            int ans = 0;
            for (int i = 0; i <= k; i++) {
                if (times[i] > t - k) {
                    ans += hits[i];
                }
            }
            return ans;
        }
    }
}