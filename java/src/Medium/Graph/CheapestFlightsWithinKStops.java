package Medium.Graph;

import java.util.Arrays;

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
     * Thoughts:
     * while the recurrence comes naturally - min(dp[i][to], dp[i - 1][from] + from_to_price)
     * the main problem was to deal with cells whose prev flights weren't analyzed yet
     *
     * Time complexity: O()
     * Space complexity: O()
     */
    public static int solution1(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[K + 2][n];
        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        dp[0][src] = 0;

        // loop K + 1 times to fill the path from src to dst
        for (int i = 1; i < dp.length; i++) {
            dp[i][src] = 0;
            for (int[] flight : flights)
                dp[i][flight[1]] = Math.min(dp[i][flight[1]], dp[i - 1][flight[0]] + flight[2]);
        }

        return dp[K + 1][dst] >= Integer.MAX_VALUE / 2 ? -1 : dp[K + 1][dst];
    }

    // space O(n) optimized
    public static int solution1optimized(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[2][n];
        Arrays.fill(dp[0], Integer.MAX_VALUE / 2);
        Arrays.fill(dp[1], Integer.MAX_VALUE / 2);
        dp[0][src] = 0;
        dp[1][src] = 0;

        int x = 0;
        for (int i = 0; i <= K; i++) {
            for (int[] f : flights)
                dp[1 - x][f[1]] = Math.min(dp[1 - x][f[1]], dp[x][f[0]] + f[2]);
            x = 1 - x;
        }

        return dp[(K + 1) % 2][dst] >= Integer.MAX_VALUE / 2 ? -1 : dp[(K + 1) % 2][dst];
    }

    /**
     * Dijkstra algo
     * Time complexity: O()
     * Space complexity: O()
     */
    public static int solution2(int n, int[][] flights, int src, int dst, int K) {
return 0;
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
        System.out.println(solution1optimized(5, a, 0, 4,1));
    }
}
