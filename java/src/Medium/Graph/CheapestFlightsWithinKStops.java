package Medium.Graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
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

    /**
     * Find Shortest Path
     * Non-negative edge weight
     * Relaxation - update the cost of getting to the point if found a shorter path
     * distTo[w] <= distTo[v] + e.weight()
     */
    public static class Dijkstras {
        private static int[] distTo;
        private static MinPQ<Integer> pq;

        public static int solution(int n, int[][] flights, int src, int dst, int K) {
            EWDI graph = new EWDI(n, flights);
            distTo = new int[n];
            Arrays.fill(distTo, Integer.MAX_VALUE);
            distTo[src] = 0;

            pq = new MinPQ<>(n);
            pq.insert(src, 0);

            while (!pq.isEmpty()) {
                int v = pq.delMin();
                for (DWE edge : graph.adj(v))
                    relax(edge);
            }

            return distTo[dst] == Integer.MAX_VALUE ? -1 : distTo[dst];
        }

        private static void relax(DWE edge) {
            int from = edge.getV();
            int to = edge.getW();
            int cost = edge.getWeight();
            if (distTo[to] > distTo[from] + cost) {
                distTo[to] = distTo[from] + cost;
                if (pq.contains(to)) pq.decreaseKey(to, distTo[to]);
                else pq.insert(to, distTo[to]);
            }
        }

        public static class EWDI {
            private int V;
            private int E;
            private LinkedList<DWE>[] adj;

            public EWDI(int n, int[][] graph) {
                this.V = n;
                adj = (LinkedList<DWE>[]) new LinkedList[n];
                for (int v = 0; v < V; v++) adj[v] = new LinkedList<>();
                for (int[] edge : graph) {
                    int from = edge[0];
                    int to = edge[1];
                    int cost = edge[2];
                    adj[from].add(new DWE(from, to, cost));
                }
            }

            public int outdegree(int v) {
                return adj[v].size();
            }

            public Iterable<DWE> adj(int v) {
                return adj[v];
            }
        }

        public static class DWE {
            private final int v;
            private int w;
            private int weight;

            public int getWeight() {
                return weight;
            }

            public DWE(int v, int w, int weight) {
                this.v = v;
                this.w = w;
                this.weight = weight;
            }

            public int getV() {
                return v;
            }

            public int getW() {
                return w;
            }
        }

        public static class MinPQ<Key extends Comparable<Key>> {
            private int maxN;        // maximum number of elements on PQ
            private int n;           // number of elements on PQ
            private int[] pq;        // binary heap using 1-based indexing
            private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
            private Key[] keys;      // keys[i] = priority of i

            public MinPQ(int maxN) {
                if (maxN < 0) throw new IllegalArgumentException();
                this.maxN = maxN;
                n = 0;
                keys = (Key[]) new Comparable[maxN + 1];
                pq   = new int[maxN + 1];
                qp   = new int[maxN + 1];
                for (int i = 0; i <= maxN; i++) qp[i] = -1;
            }

            public int delMin() {
                int min = pq[1];
                exch(1, n--);
                sink(1);
                qp[min] = -1;        // delete
                keys[min] = null;    // to help with garbage collection
                pq[n+1] = -1;        // not needed
                return min;
            }

            public boolean isEmpty() {
                return n == 0;
            }

            public boolean contains(int i) {
                return qp[i] != -1;
            }

            public int size() {
                return n;
            }

            public void insert(int i, Key key) {
                n++;
                qp[i] = n;
                pq[n] = i;
                keys[i] = key;
                swim(n);
            }

            // Decrease the key associated with index i to the specified value.
            public void decreaseKey(int i, Key key) {
                keys[i] = key;
                swim(qp[i]);
            }

            private boolean greater(int i, int j) {
                return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
            }

            private void exch(int i, int j) {
                int swap = pq[i];
                pq[i] = pq[j];
                pq[j] = swap;
                qp[pq[i]] = i;
                qp[pq[j]] = j;
            }

            private void swim(int k) {
                while (k > 1 && greater(k/2, k)) {
                    exch(k, k/2);
                    k = k/2;
                }
            }

            private void sink(int k) {
                while (2*k <= n) {
                    int j = 2*k;
                    if (j < n && greater(j, j+1)) j++;
                    if (!greater(k, j)) break;
                    exch(k, j);
                    k = j;
                }
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
        System.out.println(Dijkstras.solution(5, a, 0, 4,1));
    }
}
