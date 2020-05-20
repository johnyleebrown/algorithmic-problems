package backtracking.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 1066
 *
 * =====
 *
 * Task.
 *
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N
 * <= M. Each worker and bike is a 2D coordinate on this grid.
 *
 * We assign one unique bike to each worker so that the sum of the Manhattan
 * distances between each worker and their assigned bike is minimized.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) =
 * |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return the minimum possible sum of Manhattan distances between each worker
 * and their assigned bike.
 */
public class CampusBikesII {
    /**
     * Part I. Backtracking. O(m!/(m-n)!)
     */
    public static class Solution {
        int ans = Integer.MAX_VALUE;

        public int assignBikes(int[][] w, int[][] b) {
            gen(w, b, 0, 0, new boolean[b.length]);
            return ans;
        }

        void gen(int[][] w, int[][] b, int curW, int sum, boolean[] seenBikes) {
            if (curW == w.length) {
                ans = Math.min(ans, sum);
            } else {
                for (int i = 0; i < b.length; i++) {
                    if (seenBikes[i]) continue;
                    seenBikes[i] = true;
                    gen(w, b, curW + 1, sum + getDist(w, b, curW, i), seenBikes);
                    seenBikes[i] = false;
                }
            }
        }

        int getDist(int[][] w, int[][] b, int curW, int curB) {
            return Math.abs(w[curW][0] - b[curB][0]) + Math.abs(w[curW][1] - b[curB][1]);
        }
    }

    /**
     * Part II. DFS.
     */
    public static class Solution2 {
        public int assignBikes(int[][] w, int[][] b) {
            return gen(w, b, 0, 0, new boolean[b.length]);
        }

        int gen(int[][] w, int[][] b, int curW, int dist, boolean[] seenBikes) {
            if (curW == w.length) {
                return dist;
            }
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < b.length; i++) {
                if (seenBikes[i]) continue;
                seenBikes[i] = true;
                ans = Math.min(ans, gen(w, b, curW + 1, getDist(w, b, curW, i), seenBikes));
                seenBikes[i] = false;
            }

            return ans + dist;
        }

        int getDist(int[][] w, int[][] b, int curW, int curB) {
            return Math.abs(w[curW][0] - b[curB][0]) + Math.abs(w[curW][1] - b[curB][1]);
        }
    }

    /**
     * Part III. DFS+cache. O(n*(2^m)). 2^m states total, to get 1 state we need
     * to go through n workers.
     */
    public static class Solution3 {
        public int assignBikes(int[][] w, int[][] b) {
            return gen(w, b, 0, 0, new HashMap<>()); // seen bikes bitset - min sum
        }

        int gen(int[][] w, int[][] b, int curW, int seenBikes, Map<Integer, Integer> cache) {
            if (curW == w.length) {
                return 0;
            }
            if (cache.containsKey(seenBikes)) {
                return cache.get(seenBikes);
            }
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < b.length; i++) {
                if (seen(seenBikes, i)) continue;
                ans = Math.min(ans, getDist(w, b, curW, i) + gen(w, b, curW + 1, setBit(seenBikes, i), cache));
            }
            cache.put(seenBikes, ans);
            return cache.get(seenBikes);
        }

        boolean seen(int seenBikes, int i) {
            return ((seenBikes >> (i)) & 1) == 1;
        }

        int setBit(int seenBikes, int i) {
            return seenBikes | (1 << i);
        }

        int getDist(int[][] w, int[][] b, int curW, int curB) {
            return Math.abs(w[curW][0] - b[curB][0]) + Math.abs(w[curW][1] - b[curB][1]);
        }
    }
}
