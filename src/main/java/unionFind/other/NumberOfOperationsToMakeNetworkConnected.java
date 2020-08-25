package unionFind.other;

/**
 * 1319
 *
 * ======
 *
 * Task.
 *
 * There are n computers numbered from 0 to n-1 connected by ethernet cables
 * connections forming a network where connections[i] = [a, b] represents a
 * connection between computers a and b. Any computer can reach any other
 * computer directly or indirectly through the network. Given an initial
 * computer network connections. You can extract certain cables between two
 * directly connected computers, and place them between any pair of disconnected
 * computers to make them directly connected. Return the minimum number of times
 * you need to do this in order to make all the computers connected. If it's not
 * possible, return -1.
 */
public class NumberOfOperationsToMakeNetworkConnected {
    /**
     * Just find number of spare components after union.
     */
    static class Solution {
        public int makeConnected(int n, int[][] connections) {
            if (connections.length < n - 1) {
                return -1;
            }

            UF uf = new UF(100_000 + 1, n);
            for (int[] connection : connections) {
                uf.union(connection[0], connection[1]);
            }

            return uf.ccCount - 1;
        }

        static class UF {
            public int ccCount;
            private int[] parents;
            private int[] ranks;

            UF(int size, int n) {
                ccCount = n;
                ranks = new int[size];

                parents = new int[size];
                for (int i = 0; i < size; i++) {
                    parents[i] = i;
                }
            }

            public void union(int p, int q) {
                int pP = find(p);
                int pQ = find(q);

                if (pP == pQ) {
                    return;
                }

                // using rank to balance out the tree of connections
                if (ranks[pP] > ranks[pQ]) {
                    parents[pQ] = pP;
                } else {
                    parents[pP] = pQ;
                    ranks[pQ]++;
                }

                ccCount--;
            }

            private int find(int p) {
                if (p == parents[p]) {
                    return p;
                }

                return find(parents[p]);
            }
        }
    }
}