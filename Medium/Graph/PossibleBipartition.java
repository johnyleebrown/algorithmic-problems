package Medium.Graph;

import java.util.LinkedList;

/**
 * 886 Possible Bipartition
 *
 * Given a set of N people (numbered 1, 2, ..., N), we
 * would like to split everyone into two groups of any size.
 *
 * Each person may dislike some other people, and they
 * should not go into the same group.
 *
 * Formally, if dislikes[i] = [a, b], it means it is not
 * allowed to put the people numbered a and b into the same group.
 *
 * Return true if and only if it is possible to split everyone
 * into two groups in this way.
 *
 * https://leetcode.com/problems/possible-bipartition/
 */
public class PossibleBipartition {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int count;          // number of connected components

    public boolean possibleBipartition(int N, int[][] dislikes) {
        Graph G = new Graph(N);
        for (int[] ar : dislikes) G.addEdge(ar[0], ar[1]);
        System.out.println(checkCC(G));
        return checkCC(G) == 2;
    }

    int checkCC(Graph G) {
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
        return count;
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    class Graph {
        private int V;
        private int E;
        private LinkedList<Integer>[] adj;

        public Graph(int V) {
            this.V = V;
            this.E = 0;
            adj = (LinkedList<Integer>[]) new LinkedList[V];
            for (int v = 0; v < V; v++)
                adj[v] = new LinkedList<>();
        }

        public int V() {
            return V;
        }

        public void addEdge(int v, int w) {
            E++;
            adj[v].add(w);
            adj[w].add(v);
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }
    }
}
