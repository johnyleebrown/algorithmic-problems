package graph.hamiltonian_path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2097
 */
public class ValidArrangementOfPairs {

  /**
   * $INSERT_EXPLANATION
   */
  public static class Solution {

    private static final List<Integer> em = new ArrayList<>();
    private static int ind;

    public int[][] validArrangement(int[][] pairs) {

      // create graph and count degree of each vertex
      Map<Integer, List<Integer>> graph = new HashMap<>();
      Map<Integer, Integer> degree = new HashMap<>();
      for (int[] pair : pairs) {
        graph.putIfAbsent(pair[0], new ArrayList<>());
        graph.get(pair[0]).add(pair[1]);
        degree.put(pair[0], degree.getOrDefault(pair[0], 0) + 1);
        degree.put(pair[1], degree.getOrDefault(pair[1], 0) - 1);
      }

      // we know an Eulerian path exists
      // either we have all degrees == 0
      // or degrees == 0 and 1 has degree == 1 and 1 has degree == -1
      int start = -1;
      for (int key : degree.keySet()) {
        if (degree.get(key) > 0) {
          start = key;
          break;
        }
      }

      // we have all degrees == 0, random start
      if (start == -1) start = pairs[0][0];

      int[][] ans = new int[pairs.length][2];
      ind = ans.length - 1;
      dfs(graph, start, ans);

      return ans;
    }

    private void dfs(Map<Integer, List<Integer>> graph, int v,
        int[][] ans) {
      while (!graph.getOrDefault(v, em).isEmpty()) {
        int size = graph.get(v).size();
        int w = graph.get(v).get(size - 1);
        graph.get(v).remove(size - 1);
        dfs(graph, w, ans);
        ans[ind][1] = w;
        ans[ind][0] = v;
        ind--;
      }
    }
  }
}
