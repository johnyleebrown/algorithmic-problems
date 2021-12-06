package graph.topological;

import java.util.*;

/**
 * 444
 */
public class SequenceReconstruction {

  /**
   * We need to find a topsort, compare it with org and verify if it is unique. If a
   * topological sort has the property that all pairs of consecutive vertices in the
   * topsort are connected by edges in the graph, then these edges form a directed
   * Hamiltonian path. If a Hamiltonian path exists, the topological sort order is
   * unique.
   *
   * Edge cases: - when one of seqs has repeating values
   */
  public static class Solution {

    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {

      Map<Integer, Set<Integer>> g = new HashMap<>();
      for (List<Integer> l : seqs) {
        Set<Integer> rep = new HashSet<>();
        for (int i = l.size() - 1; i >= 0; i--) {
          if (!rep.add(l.get(i))) { // (1)
            return false;
          }
          g.putIfAbsent(l.get(i), new HashSet<>());
          if (i > 0 && l.get(i) != l.get(i - 1)) {
            g.get(l.get(i)).add(l.get(i - 1));
          }
        }
      }

      if (hasCycles(g)) return false;

      List<Integer> res = new ArrayList<>();
      Set<Integer> seen = new HashSet<>();
      for (int v : g.keySet()) {
        dfs(v, g, res, seen);
      }

      return areSame(org, res) && haveEdges(res, g);
    }

    private boolean areSame(int[] o, List<Integer> res) {
      if (o.length != res.size()) return false;
      for (int i = 0; i < res.size(); i++) {
        if (res.get(i) != o[i]) {
          return false;
        }
      }
      return true;
    }

    /**
     * all consecutive vertices in topsort should have edges
     */
    private boolean haveEdges(List<Integer> res, Map<Integer, Set<Integer>> g) {
      for (int i = 0; i < res.size() - 1; i++) {
        if (!g.get(res.get(i)).contains(res.get(i + 1)) &&
            !g.get(res.get(i + 1)).contains(res.get(i))) {
          return false;
        }
      }
      return true;
    }

    private void dfs(int v, Map<Integer, Set<Integer>> g, List<Integer> res,
        Set<Integer> seen) {
      if (!seen.add(v)) return;
      for (int w : g.getOrDefault(v, new HashSet<>())) {
        dfs(w, g, res, seen);
      }
      res.add(v);
    }

    private boolean hasCycles(Map<Integer, Set<Integer>> g) {
      Set<Integer> globalSeen = new HashSet<>();
      for (int v : g.keySet()) {
        if (hasCycles(v, g, globalSeen, new HashSet<>())) {
          return true;
        }
      }
      return false;
    }

    private boolean hasCycles(int v, Map<Integer, Set<Integer>> g,
        Set<Integer> globalSeen, Set<Integer> localSeen) {
      if (localSeen.contains(v)) return true;
      if (globalSeen.contains(v)) return false;
      localSeen.add(v);
      globalSeen.add(v);
      for (int w : g.getOrDefault(v, new HashSet<>())) {
        if (hasCycles(w, g, globalSeen, localSeen)) {
          return true;
        }
      }
      localSeen.remove(v);
      return false;
    }
  }
}