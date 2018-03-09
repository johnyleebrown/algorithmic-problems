package Medium.Graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 787
 * There are n cities connected by m flights. Each fight
 * starts from city u and arrives at v with a price w.
 * Now given all the cities and fights, together with
 * starting city src and the destination dst, your task
 * is to find the cheapest price from src to dst with up
 * to k stops. If there is no such route, output -1.
 */
public class CheapestFlightsWithinKStops {
    /**
     * DP
     * While the recurrence comes naturally - min(dp[i][to], dp[i - 1][from] + from_to_price)
     * the main problem was to deal with cells whose prev flights weren't analyzed yet.
     * To solve that, we will loop K + 1 times (number of edges between src and dst) to cover the worst case,
     * when the flights are in the wrong order
     *
     * Time complexity: O(K*M), M - dp.length
     * Space complexity: O(n^2)
     */
    public static int solution1(int n, int[][] flights, int src, int dst, int K) {
        int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[K + 2][n];
        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], INF);
        dp[0][src] = 0;

        // loop K + 1 times to fill the path from src to dst
        for (int i = 1; i < dp.length; i++) {
            dp[i][src] = 0;
            for (int[] flight : flights)
                dp[i][flight[1]] = Math.min(dp[i][flight[1]], dp[i - 1][flight[0]] + flight[2]);
        }

        return dp[K + 1][dst] >= INF / 2 ? -1 : dp[K + 1][dst];
    }

    // space O(n) optimized
    public static int solution1optimized(int n, int[][] flights, int src, int dst, int K) {
        int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[2][n];
        Arrays.fill(dp[0], INF);
        Arrays.fill(dp[1], INF);
        dp[0][src] = 0;
        dp[1][src] = 0;

        int x = 0;
        for (int i = 0; i <= K; i++) {
            for (int[] f : flights)
                dp[1 - x][f[1]] = Math.min(dp[1 - x][f[1]], dp[x][f[0]] + f[2]);
            x = 1 - x;
        }

        return dp[(K + 1) % 2][dst] >= INF / 2 ? -1 : dp[(K + 1) % 2][dst];
    }

    /**
     * Dijkstra algorithm
     * Time complexity: O()
     * Space complexity: O()
     */
    public static int solution2(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] flight: flights) graph[flight[0]][flight[1]] = flight[2];
        Map<Integer, Integer> bestCostSoFar = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, src});

        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int cost = info[0], k = info[1], place = info[2];

            if (k > K + 1 || cost > bestCostSoFar.getOrDefault(k * 1000 + place, Integer.MAX_VALUE)) continue;
            if (place == dst) return cost;
            for (int i = 0; i < n; ++i) relax(pq, graph, bestCostSoFar, cost, k, place, i);
        }

        return -1;
    }

    // relaxation method - update the cost of getting to the vertex if a shorter path is found
    private static void relax(PriorityQueue<int[]> pq, int[][] graph, Map<Integer, Integer> bestCostSoFar, int cost, int k, int place, int i){
        if (graph[place][i] > 0) {
            int newCost = cost + graph[place][i];
            if (newCost < bestCostSoFar.getOrDefault((k + 1) * 1000 + i, Integer.MAX_VALUE)) {
                pq.offer(new int[]{newCost, k + 1, i});
                bestCostSoFar.put((k + 1) * 1000 + i, newCost);
            }
        }
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {3,4,200},
                {2,4,300},
                {0,4,700},
                {1,3,200},
                {0,1,100},
                {0,2,300}
        };
        System.out.println(solution2(5, a, 0, 4,1));
        System.out.println(solution1(5, a, 0, 4,1));
    }
}
