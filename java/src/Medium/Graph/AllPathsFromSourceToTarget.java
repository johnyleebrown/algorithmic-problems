package Medium.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a directed, acyclic graph of N nodes. Find all possible paths
 * from node 0 to node N-1, and return them in any order. The graph is
 * given as follows: the nodes are 0, 1, ..., graph.length - 1. graph[i]
 * is a list of all nodes j for which the edge (i, j) exists.
 */
public class AllPathsFromSourceToTarget {
    /**
     * Time complexity: O(V)
     * Space complexity: O(1)
     */
    List<List<Integer>> l = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        fill(graph, 0, new ArrayList<>());
        return l;
    }

    void fill(int[][] graph, int v, ArrayList<Integer> cur) {
        if (v == graph.length - 1) l.add(cur);
        cur.add(v);
        for (int w : graph[v]) fill(graph, w, new ArrayList<>(cur));
    }
}
