package Hard.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 815
 * <p>
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
 * <p>
 * Example: Input: routes = [[1, 2, 7], [3, 6, 7]] S = 1 T = 6
 * Output: 2 Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 500.
 * 0 <= routes[i][j] < 10 ^ 6.
 */
public class BusRoutes {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
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

        // bfs
        q.offer(S);
        while (!q.isEmpty()) {
            int len = q.size();
            count++;

            for (int i = 0; i < len; i++) {
                int cur = q.poll();
                ArrayList<Integer> routeNums = map.get(cur);

                for (int r : routeNums) {
                    if (marked.contains(r)) continue;
                    marked.add(r);

                    for (int j = 0; j < routes[r].length; j++) {
                        if (routes[r][j] == T) return count;
                        q.offer(routes[r][j]);
                    }
                }
            }
        }

        return -1;
    }
/*


[[7,12],[4,5,15],[6],[15,19],[9,12,13]]
15
12


 */

}
