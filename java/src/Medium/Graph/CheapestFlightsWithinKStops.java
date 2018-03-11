package Medium.Graph;

import static Helpers.Helper.print2Darray;
import static Helpers.Helper.replaceBracets;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
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
    public static class solution1 {
        public static int dp(int n, int[][] flights, int src, int dst, int K) {
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
        public static int dpOptimized(int n, int[][] flights, int src, int dst, int K) {
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
    }

    /**
     * Dijkstra algorithm
     * Time complexity: O()
     * Space complexity: O()
     */
    public static class solution2 {
        public static int solution2(int n, int[][] flights, int src, int dst, int K) {
            return 0;
        }

        // relaxation method - update the cost of getting to the vertex if a shorter path is found
        private static void relax(){

        }
    }

    /**
     * Dijkstras
     * Find Shortest Path
     * Non-negative edge weight
     * Relaxation - update the cost of getting to the point if found a shorter path
     * distTo[w] <= distTo[v] + e.weight()
     */
    public static class solution3 {
        private static int[] costs;
        //private static MinPQ<Integer> pq;
        private static int[] distTo;
        private static final int MAX = Integer.MAX_VALUE / 2;
        private static PriorityQueue<int[]> pQ;

        public static int solution(int n, int[][] flights, int src, int dst, int K) {
            EWDI graph = new EWDI(n, flights);
            distTo = new int[n];

            costs = new int[n];
            Arrays.fill(costs, MAX);
            costs[src] = 0;

            pQ = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pQ.offer(new int[]{src, 0});

            while (!pQ.isEmpty()) {
                int v = pQ.peek()[0];
                int forgottenCost = pQ.poll()[1];

//                System.out.println("v: " + v);
//                pQ.forEach(val -> System.out.print(Arrays.toString(val)));
//                System.out.println();

                for (DWE edge : graph.adj(v))
                    relax(edge, K, forgottenCost);

//                System.out.println();
            }

//            System.out.println("====================");
//            System.out.println(Arrays.toString(costs));
//            System.out.println(Arrays.toString(distTo));
            return costs[dst] >= MAX ? -1 : costs[dst];
        }

        private static void relax(DWE edge, int K, int forgottenCost) {
            int from = edge.getV();
            int to = edge.getW();
            int cost = edge.getWeight();

            if (distTo[from] + 1 > K + 1) {
                costs[from] = forgottenCost;
                costs[to] = costs[from] + cost;
                distTo[to] = distTo[from] + 1;
            }
            else if (costs[to] > costs[from] + cost && distTo[from] + 1 <= K + 1) {
                costs[to] = costs[from] + cost;
                distTo[to] = distTo[from] + 1;
                pQ.add(new int[]{to, costs[to]});

//                pQ.forEach(val -> System.out.print(Arrays.toString(val)));
//                System.out.println();
            }

//            System.out.println(Arrays.toString(costs));
//            System.out.println(Arrays.toString(distTo));
//            System.out.println("------------");
        }

        static class EWDI {
            private int V;
            private int E;
            private LinkedList<DWE>[] adj;

            EWDI(int n, int[][] graph) {
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

            int outdegree(int v) {
                return adj[v].size();
            }

            Iterable<DWE> adj(int v) {
                return adj[v];
            }
        }

        static class DWE {
            private final int v;
            private int w;
            private int weight;

            DWE(int v, int w, int weight) {
                this.v = v;
                this.w = w;
                this.weight = weight;
            }

            int getV() {
                return v;
            }

            int getW() {
                return w;
            }

            int getWeight() {
                return weight;
            }
        }

        static class MinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
            private int maxN;        // maximum number of elements on PQ
            private int n;           // number of elements on PQ
            private int[] pq;        // binary heap using 1-based indexing
            private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
            private Key[] keys;      // keys[i] = priority of i

            MinPQ(int maxN) {
                this.maxN = maxN;
                n = 0;
                keys = (Key[]) new Comparable[maxN + 1];
                pq = new int[maxN + 1];
                qp = new int[maxN + 1];
                for (int i = 0; i <= maxN; i++) qp[i] = -1;
            }

            int delMin() {
                int min = pq[1];
                exch(1, n--);
                sink(1);
                qp[min] = -1;        // delete
                keys[min] = null;    // to help with garbage collection
                pq[n + 1] = -1;        // not needed
                printAll();
                return min;
            }

            boolean isEmpty() {
                return n == 0;
            }

            boolean contains(int i) {
                return qp[i] != -1;
            }

            int size() {
                return n;
            }

            void insert(int i, Key key) {
                n++;
                qp[i] = n;
                pq[n] = i;
                keys[i] = key;
                swim(n);

                printAll();
            }

            // Decrease the key associated with index i to the specified value.
            void decreaseKey(int i, Key key) {
                keys[i] = key;
                swim(qp[i]);

                printAll();
            }

            void printAll() {
                System.out.println("===============");
                Arrays.stream(pq).forEach(val -> System.out.print(" " + val + " "));
                System.out.println();
                System.out.println("===============");
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
                while (k > 1 && greater(k / 2, k)) {
                    exch(k, k / 2);
                    k = k / 2;
                }
            }

            private void sink(int k) {
                while (2 * k <= n) {
                    int j = 2 * k;
                    if (j < n && greater(j, j + 1)) j++;
                    if (!greater(k, j)) break;
                    exch(k, j);
                    k = j;
                }
            }

            public Iterator<Integer> iterator() { return new HeapIterator(); }

            private class HeapIterator implements Iterator<Integer> {
                // create a new pq
                private MinPQ<Key> copy;

                // add all elements to copy of heap
                // takes linear time since already in heap order so no keys move
                public HeapIterator() {
                    copy = new MinPQ<>(pq.length - 1);
                    for (int i = 1; i <= n; i++)
                        copy.insert(pq[i], keys[pq[i]]);
                }

                public boolean hasNext()  { return !copy.isEmpty();                     }
                public void remove()      { throw new UnsupportedOperationException();  }

                public Integer next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    return copy.delMin();
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

        System.out.println(solution1.dp(5, a, 0, 4,1));
        System.out.println(solution3.solution(5, a, 0, 4,1));
        System.out.println("=========");
        String s1 = "[[4,1,1],[1,2,3],[0,3,2],[0,4,10],[3,1,1],[1,4,3]]";
        System.out.println(solution1.dp(5, replaceBracets(s1), 2, 1,1));
        System.out.println(solution3.solution(5, replaceBracets(s1), 2, 1,1));
        System.out.println("=========");
        String s2 = "[[0,1,2],[1,2,1],[2,0,10]]";
        System.out.println(solution1.dp(3, replaceBracets(s2), 1, 2,1));
        System.out.println(solution3.solution(3, replaceBracets(s2), 1, 2,1));
        System.out.println("=========");
        String s3 = "[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]";
        System.out.println(solution1.dpOptimized(4, replaceBracets(s3), 0, 3,1));
        System.out.println(solution3.solution(4, replaceBracets(s3), 0, 3,1));
    }
}
