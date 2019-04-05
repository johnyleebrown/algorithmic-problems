package Medium.DFS;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 332
 * https://leetcode.com/problems/reconstruct-itinerary/description/
 *
 * Given a list of airline tickets represented by pairs of departure
 * and arrival airports [from, to], reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK. Thus, the
 * itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary
 * that has the smallest lexical order when read as a single string. For
 * example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
 * ["JFK", "LGB"]. All airports are represented by three capital letters
 * (IATA code). You may assume all tickets form at least one valid itinerary.
 */
public class ReconstructItinerary {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution {
        public List<String> findItinerary(String[][] tickets)
        {
            if (tickets == null || tickets.length == 0 || tickets[0].length == 0) return null;

            Map<String, PriorityQueue<String>> m = new HashMap<>();
            fillMap(m, tickets);

            LinkedList<String> l = new LinkedList<>();
            createPath(l, m, "JFK");

            return l;
        }

        private void createPath(LinkedList<String> l, Map<String, PriorityQueue<String>> m, String at)
        {
            PriorityQueue<String> pq = m.get(at);

            while (pq != null && !pq.isEmpty())
            {
                createPath(l, m, pq.poll());
            }

            l.addFirst(at);
        }

        private void fillMap(Map<String, PriorityQueue<String>> m, String[][] tickets)
        {
            for (String[] ticket : tickets)
            {
                String from = ticket[0], to = ticket[1];
                m.putIfAbsent(from, new PriorityQueue<>());
                m.get(from).add(to);
            }
        }
    }
}
