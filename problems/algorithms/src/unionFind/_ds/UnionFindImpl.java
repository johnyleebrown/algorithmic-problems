package unionFind._ds;

/**
 * UnionFind
 */
public class UnionFindImpl implements UnionFind {
    private final int[] parent;
    private final int[] rank;
    private int count;

    public UnionFindImpl(int n) {
        count = n;
        rank = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int parentP = find(p);
        int parentQ = find(q);

        if (parentP == parentQ) return;

        // we want to link smaller tree(set) to larger one
        if (rank[parentP] > rank[parentQ]) {
            parent[parentQ] = parentP;
        } else if (rank[parentP] < rank[parentQ]) {
            parent[parentP] = parentQ;
        } else {
            parent[parentQ] = parentP;
            rank[parentP]++;
        }

        count--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Find with Path Compression.
     */
    private int find(int p) {
        while (parent[p] != p) {
            parent[p] = parent[parent[p]];
            p = parent[parent[p]];
        }

        return p;
    }

    public int count() {
        return count;
    }
}
