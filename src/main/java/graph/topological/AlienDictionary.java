package graph.topological;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 269
 */
public class AlienDictionary {

  /**
   * create graph - add all letters as nodes in a graph - add an edge if this is a first
   * not equal letter in ar[i] and ar[i-1] check if acyclic find topsort
   *
   * edge case: - when input is wrong ex: [abc,ab] - should be [ab,abc] - when there is a
   * cycle
   */
  public static class Solution {

    public String alienOrder(String[] ar) {

      Map<Character, Set<Character>> g = new HashMap<>();
      for (int i = ar.length - 1; i >= 0; i--) {
        boolean foundFirstNonEqual = false;
        for (int j = 0; j < ar[i].length(); j++) {
          g.putIfAbsent(ar[i].charAt(j), new HashSet<>());
          if (i - 1 >= 0 && ar[i - 1].length() > ar[i].length() &&
              ar[i].charAt(j) == ar[i - 1].charAt(j) && j == ar[i].length() - 1 &&
              !foundFirstNonEqual) {
            return "";
          }
          if (i - 1 >= 0 && j < ar[i - 1].length() &&
              ar[i].charAt(j) != ar[i - 1].charAt(j) && !foundFirstNonEqual) {
            g.get(ar[i].charAt(j)).add(ar[i - 1].charAt(j));
            foundFirstNonEqual = true;
          }
        }
      }

      if (hasCycle(g)) return "";

      StringBuilder sb = new StringBuilder();
      Set<Character> seen = new HashSet<>();
      for (char v : g.keySet()) {
        dfs(v, sb, seen, g);
      }
      return sb.toString();
    }

    private void dfs(char v, StringBuilder sb, Set<Character> seen,
        Map<Character, Set<Character>> g) {
      if (!seen.add(v)) return;
      for (char w : g.getOrDefault(v, new HashSet<>())) {
        dfs(w, sb, seen, g);
      }
      sb.append(v);
    }

    private boolean hasCycle(Map<Character, Set<Character>> g) {
      Set<Character> globalSeen = new HashSet<>();
      for (char v : g.keySet()) {
        if (hasCycle(v, g, globalSeen, new HashSet<>())) {
          return true;
        }
      }
      return false;
    }

    private boolean hasCycle(char v, Map<Character, Set<Character>> g,
        Set<Character> globalSeen, Set<Character> localSeen) {
      if (localSeen.contains(v)) return true;
      if (globalSeen.contains(v)) return false;
      localSeen.add(v);
      globalSeen.add(v);
      for (char w : g.getOrDefault(v, new HashSet<>())) {
        if (hasCycle(w, g, globalSeen, localSeen)) {
          return true;
        }
      }
      localSeen.remove(v);
      return false;
    }
  }
}