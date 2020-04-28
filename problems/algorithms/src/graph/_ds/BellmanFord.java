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
 * https://algs4.cs.princeton.edu/44sp/BellmanFordSP.java.html
 */
public class BellmanFord {
    private Deque<Integer> q; //queue of vertices to relax
    private double[] distTo; // distance  of shortest s->v path
    private boolean[] onQueue; // see if we need to add a vertex to queue
    private int count; // count of calls to relax
    private int n;
    private boolean cycle;

    /**
     * Compute sp from source to all vertices.
     */
    public BellmanFord(int n, int s, Map<Integer, Map<Integer, Double>> g) {
        this.n = n;
        onQueue = new boolean[n];
        onQueue[s] = true;
        TreeMap<Integer, Integer> t;

        distTo = new double[n];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[s] = 0;

        q = new ArrayDeque<>();
        q.addLast(s);

        //&& !hasNegativeCycle()
        while (!q.isEmpty() && !hasNegativeCycle()) {
            int v = q.removeFirst();
            onQueue[v] = false;
            relax(v, g);
        }
    }

    private void relax(int v, Map<Integer, Map<Integer, Double>> g) {
        for (int w : g.get(v).keySet()) {
            if (distTo[w] > distTo[v] + g.get(v).get(w)) {
                distTo[w] = distTo[v] + g.get(v).get(w);
                if (!onQueue[w]) {
                    q.addLast(w);
                    onQueue[w] = true;
                }
            }
            if (++count % n == 0) {
//                cycle = false;
                cycle = new DetectCycle<Double>().hasCycleWeightedDigraph(g);
                if (hasNegativeCycle()) {
                    return;
                }
            }
        }
    }

    public boolean hasCycleWeightedDigraph(Map<Integer, Map<Integer, Double>> g) {
        Set<Integer> globalSeen = new HashSet<>();
        for (int v : g.keySet()) {
            if (hasCycleWeightedDigraph(v, g, globalSeen, new HashSet<>())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleWeightedDigraph(int v, Map<Integer, Map<Integer, Double>> g, Set<Integer> globalSeen, Set<Integer> localSeen) {
        if (localSeen.contains(v)) {
            return true;
        }
        if (globalSeen.contains(v)) {
            return false;
        }
        localSeen.add(v);
        globalSeen.add(v);

        for (int w : g.getOrDefault(v, new HashMap<>()).keySet()) {
            if (hasCycleWeightedDigraph(w, g, globalSeen, localSeen)) {
                return true;
            }
        }
        localSeen.remove(v);
        return false;
    }

    public boolean hasNegativeCycle() {
        return cycle;
    }

    public double[] getDistTo() {
        return distTo;
    }
}
