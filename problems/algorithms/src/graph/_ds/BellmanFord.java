package graph._ds;

import java.util.*;

/**
 * BellmanFord
 * Find SP to all vertices.
 *
 * Note:
 * - No negative cycles
 * - For queue based time required is O(E+V) on avg, and in worst case O(EV)
 * - The edge weights can be positive, negative, or zero.
 *
 * *
 * A negative cycle is a directed cycle whose total weight (sum
 * of the weights of its edges) is negative. The concept of a shortest path is
 * meaningless if there is a negative cycle.
 *
 * *
 * The old version of the algorithm would relax all edges of all vertices V
 * times. The queue-based version uses queue to keep vertices that we need to
 * relax. Vertices that didn't change in the i'th cycle won't be added to the
 * queue.
 *
 * TODO add neg cycle find
 * https://algs4.cs.princeton.edu/44sp/BellmanFordSP.java.html
 */
public class BellmanFord {
    private Deque<Integer> q; //queue of vertices to relax
    private int[] distTo; // distance  of shortest s->v path
    private boolean[] onQueue; // see if we need to add a vertex to queue

    public BellmanFord(int n) {
        distTo = new int[n];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        q = new ArrayDeque<>();
    }

    /**
     * Compute sp from source to all vertices.
     */
    public void compute(int s, Map<Integer, Map<Integer, Integer>> g) {

        distTo[s] = 0;
        q.addLast(s);
        onQueue[s] = true;

        while (!q.isEmpty() && !hasNegativeCycle()) {
            int v = q.removeFirst();
            relax(v, g);
            onQueue[v] = false;
        }
    }

    private void relax(int v, Map<Integer, Map<Integer, Integer>> g) {
        for (int w : g.getOrDefault(v, new HashMap<>()).keySet()) {
            if (distTo[w] > distTo[v] + g.get(v).get(w)) {
                distTo[w] = distTo[v] + g.get(v).get(w);
                if (!onQueue[w]) {
                    q.addLast(w);
                    onQueue[w] = true;
                }
            }
        }
    }

    public boolean hasNegativeCycle() {
        return false;
    }
}
