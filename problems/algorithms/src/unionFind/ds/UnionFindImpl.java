package unionFind.ds;

public class UnionFindImpl implements UnionFind
{
	private int[] parent;
	private int[] rank;

	public UnionFindImpl(int n)
	{
		parent = new int[n];
		for (int i = 0; i < n; i++)
			parent[i] = i;
	}

	public void union(int p, int q)
	{
		int rootP = find(p);
		int rootQ = find(q);

		if (rootP == rootQ)
			return;

		if (rank[rootP] < rank[rootQ])
		{
			parent[rootP] = rootQ;
		}
		else
		{
			parent[rootQ] = rootP;
			rank[rootP]++;
		}
	}

	public boolean connected(int p, int q)
	{
		return find(p) == find(q);
	}

	private int find(int p)
	{
		while (parent[p] != p)
			p = parent[parent[p]];

		return p;
	}
}
