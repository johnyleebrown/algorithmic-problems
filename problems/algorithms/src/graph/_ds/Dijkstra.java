package graph._ds;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Dijkstra
 * Find SP from source to target in a weighted graph with positive wights.
 *
 * Note:
 * - graph might have cycles
 * - graph should not contain negative weights
 * - avg time O(E+V), worst O(E+V)
 */
public class Dijkstra {
    /**
     * @return shortest path from source to target, otherwise return -1 if there
     *         is no path from source to target
     */
    public static int find(int source, int target, Map<Integer, Map<Integer, Integer>> g, int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{source, 0});
        int[] distTo = new int[n];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[source] = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == target) return distTo[target];
            for (int w : g.getOrDefault(cur[0], new HashMap<>()).keySet()) {
                relaxVertex(cur[0], w, g, pq, distTo);
            }
        }
        return -1;
    }

    private static void relaxVertex(int v, int w, Map<Integer, Map<Integer, Integer>> g, PriorityQueue<int[]> pq, int[] distTo) {
        if (distTo[w] > distTo[v] + g.get(v).get(w)) {
            distTo[w] = distTo[v] + g.get(v).get(w);
//            pq.removeIf(x -> x[0] == w);
            pq.add(new int[]{w, distTo[v] + g.get(v).get(w)});
        }
    }
}