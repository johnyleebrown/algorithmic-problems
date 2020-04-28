package graph.shortestPaths.bfs.board;

import java.util.*;

/**
 * 815
 */
public class BusRoutes {
    /**
     * Time complexity: O(n^2) Space complexity: O(n)
     */
    public static int numBusesToDestination(int[][] routes, int S, int T) {
        HashSet<Integer> marked = new HashSet<>();// could use an array but we need to know the size
        Queue<Integer> q = new LinkedList<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();// each vertex mapped to the route it is placed on (let's name them i)
        int count = 0;

        if (S == T) return 0;

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                ArrayList<Integer> buses = map.getOrDefault(routes[i][j], new ArrayList<>());
                buses.add(i);
                map.put(routes[i][j], buses);
            }
        }

        // graph.shortestPaths.bfs
        q.offer(S);
        while (!q.isEmpty()) {
            int len = q.size();
            count++;

            for (int i = 0; i < len; i++) {
                int cur = q.poll();
                ArrayList<Integer> routeNums = map.get(cur);

                // add all the bus stops that are reachable by all the bus routes
                for (int r : routeNums) { // routes
                    if (marked.contains(r)) continue;
                    marked.add(r);

                    for (int j = 0; j < routes[r].length; j++) { // route stops
                        if (routes[r][j] == T) return count;
                        q.offer(routes[r][j]);
                    }
                }
            }
        }

        return -1;
    }
}