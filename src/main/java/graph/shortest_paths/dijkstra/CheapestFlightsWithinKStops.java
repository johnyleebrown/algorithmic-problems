package graph.shortest_paths.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 787. Cheapest Flights Within K Stops
 *
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */
public class CheapestFlightsWithinKStops {

  /**
   * Modified Dijkstra.
   */
  public static class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

      Map<Integer, List<Node>> g = new HashMap<>();
      for (int[] f : flights) {
        g.putIfAbsent(f[0], new ArrayList<>());
        g.get(f[0]).add(new Node(f[1], f[2], Integer.MAX_VALUE));
      }

      PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
      pq.add(new Node(src, 0, 0));
      int[] dp = new int[n];
      Arrays.fill(dp, Integer.MAX_VALUE);
      dp[src] = 0;
      Map<Integer, Integer> stops = new HashMap<>();
      while (!pq.isEmpty()) {
        Node curNode = pq.poll();
        if (curNode.ind == dst) {
          return curNode.cost;
        }
        if (curNode.stops > k) {
          continue;
        }
        if (stops.getOrDefault(curNode.ind, Integer.MAX_VALUE) <= curNode.stops) {
          continue;
        }
        stops.put(curNode.ind, curNode.stops);
        for (Node childNode : g.getOrDefault(curNode.ind, new ArrayList<>())) {
          int newCost = curNode.cost + childNode.cost;
          if (dp[childNode.ind] > newCost) {
            dp[childNode.ind] = newCost;
            pq.add(new Node(childNode.ind, newCost, curNode.stops + 1));
          } else if (curNode.stops < childNode.stops) {
            pq.add(new Node(childNode.ind, newCost, curNode.stops + 1));
          }
        }
      }

      return -1;
    }

    private static class Node {

      int ind, cost, stops;

      Node(int ind, int cost, int stops) {
        this.ind = ind;
        this.cost = cost;
        this.stops = stops;
      }
    }
  }
}