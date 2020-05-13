package design.array;

import java.util.ArrayList;
import java.util.List;

/**
 * DesignHitCounter2
 *
 * ======
 *
 * Task.
 *
 * Similar to DesignHitCounter, but getHits should have a range parameter - not
 * fixed 300.
 *
 * ======
 *
 * Company: Google
 * Source: Leetcode
 */
public class DesignHitCounter2 {
    public static class HitCounter {
        List<Item> times;

        public HitCounter() {
            times = new ArrayList<>();
        }

        public void hit(int t) {
            if (times.size() > 0 && times.get(times.size() - 1).time == t) {
                times.get(times.size() - 1).val++;
            } else {
                times.add(new Item(1, t));
            }
        }

        public int getHits(int t, int range) {
            if (times.size() == 0 || t - range + 1 > times.get(times.size() - 1).time)
                return 0;
            int left = bs(t - range + 1);
            int ans = 0;
            for (int i = left; i < times.size() && times.get(i).time < t; i++) {
                ans += times.get(i).val;
            }
            return ans;
        }

        // find first >=
        private int bs(int target) {
            int lo = -1;
            int hi = times.size();
            while (hi - lo > 1) {
                int mid = lo + (hi - lo) / 2;
                if (times.get(mid).time < target) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            return hi;
        }

        static class Item {
            int val, time;

            public Item(int val, int time) {
                this.val = val;
                this.time = time;
            }

            @Override
            public String toString() {
                return "Item{" +
                        "val=" + val +
                        ", time=" + time +
                        '}';
            }
        }
    }
}