package tree.count.depth;

import java.util.HashMap;
import java.util.Map;

/**
 * MinTimeToWetTree
 *
 * ======
 *
 * Task.
 *
 * An n-Ary tree was given with their level of stickiness on the edges. If the
 * water is falling from the root, find the min time taken to wet the whole
 * tree.
 *
 * ======
 *
 * Company: Google
 * Source: Leetcode
 */
public class MinTimeToWetTree {
    /**
     * edge(i,j,cost)
     */
    public static class Solution {
        public int solve(int[][] edges, int root) {
            Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
            for (int[] e : edges) {
                if (e.length > 0) {
                    g.putIfAbsent(e[0], new HashMap<>());
                    g.get(e[0]).put(e[1], e[2]);
                }
            }
            return getMax(root, g);
        }

        private int getMax(int v, Map<Integer, Map<Integer, Integer>> g) {
            if (!g.containsKey(v)) {
                return 0;
            }
            int ans = 0;
            for (int w : g.get(v).keySet()) {
                ans = Math.max(ans, getMax(w, g) + g.get(v).get(w));
            }
            return ans;
        }
    }
}