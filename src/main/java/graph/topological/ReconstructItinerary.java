package graph.topological;

import java.util.*;

/**
 * 332
 */
public class ReconstructItinerary {

  public static class Solution {

    public List<String> findItinerary(String[][] tickets) {
      if (tickets == null || tickets.length == 0 || tickets[0].length == 0)
        return null;

      Map<String, PriorityQueue<String>> m = new HashMap<>();
      fillMap(m, tickets);

      LinkedList<String> l = new LinkedList<>();
      createPath(l, m, "JFK");

      return l;
    }

    private void createPath(LinkedList<String> l, Map<String, PriorityQueue<String>> m,
        String at) {
      PriorityQueue<String> pq = m.get(at);

      while (pq != null && !pq.isEmpty()) {
        String to = pq.poll();
        createPath(l, m, to);
      }

      l.addFirst(at);
    }

    private void fillMap(Map<String, PriorityQueue<String>> m, String[][] tickets) {
      for (String[] ticket : tickets) {
        String from = ticket[0], to = ticket[1];
        m.putIfAbsent(from, new PriorityQueue<>());
        m.get(from).add(to);
      }
    }
  }
}
