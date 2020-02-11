package unionFind;

/**
 * 684
 */
public class RedundantConnection
{
	/**
	 * We want to find a connection that is the last one in the cycle because we know that edge could be removed so the
	 * number of connected components won't change if it is in the cycle by using union find, we will union all the
	 * edges, the first one that will have vertices that have the same parent is the answer.
	 */
	static class Solution1
	{
		public int[] findRedundantConnection(int[][] edges)
		{
			// we dont know the exact size, +1 for 1-indexation   
			UF uf = new UF(1000 + 1);
			for (int[] edge : edges)
			{
				if (!uf.union(edge[0], edge[1])) return edge;
			}

			return new int[]{};
		}

		class UF
		{
			private int[] parents;
			private int[] ranks;

			UF(int size)
			{
				ranks = new int[size];
				parents = new int[size];

				for (int i = 0; i < size; i++)
				{
					parents[i] = i;
				}
			}

			public boolean union(int p, int q)
			{
				int pP = find(p);
				int pQ = find(q);

				if (pP == pQ)
				{
					return false;
				}

				// using rank to balance out the tree of connections
				if (ranks[pP] > ranks[pQ])
				{
					parents[pQ] = pP;
				}
				else
				{
					parents[pP] = pQ;
					ranks[pQ]++;
				}

				return true;
			}

			private int find(int p)
			{
				if (p == parents[p])
				{
					return p;
				}

				return find(parents[p]);
			}
		}
	}

	// other solution is O(N(N+M)), we check if the number of CCs hasn't
	// if we remove any vertex
}

