package graph.shortestPaths.mst;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * MSTKruskal
 * TODO create graph adj matrix, adj list impls
 */
public class MSTKruskal {

	private List<List<Integer>> edges;

	private List<int[]> resultEdges;
	private int resultWeight;

	/**
	 * Graph - <v,<w,vw_weight>>
	 */
	public MSTKruskal(Map<Integer, Map<Integer, Integer>> graph) {
		//TODO
	}

	/**
	 * edges - [i][v,w,vw_weight]
	 */
	public MSTKruskal(List<List<Integer>> edges) {
		this.edges = edges;
		precompute();
	}

	private void precompute() {

		// sort the edges
		Collections.sort(edges, (a, b) -> a.get(2) - b.get(2));

		// create UF
		UF uf = new UF();
		// init uf
		for (List<Integer> e : edges) {
			uf.addNode(e.get(0));
			uf.addNode(e.get(1));
		}

		// connect edges in UF
		// -- calc total sum
		// -- collect list of mst's edges
		resultEdges = new LinkedList<>();
		for (List<Integer> e : edges) {

			// dont insert an edge if it creates a cycle
			if (!uf.isConnected(e.get(0), e.get(1))) {
				uf.union(e.get(0), e.get(1));
				resultWeight += e.get(2);
				resultEdges.add(new int[]{e.get(0), e.get(1)});
			}
		}
	}

	/**
	 * Get min spanning tree weight - the total weight of MST - sum of edges in MST.
	 */
	public int getMSTWeight() {
		return resultWeight;
	}

	/**
	 * Get the list of MST's edges not sorted.
	 */
	public List<int[]> getMSTEdges() {
		return resultEdges;
	}

	private class UF {
		// union find
		// with path compression
		// n vertices

		// node_value-parent
		Map<Integer, Integer> ps;
		Map<Integer, Integer> rank;

		public UF() {
			ps = new HashMap<>();
			rank = new HashMap<>();
		}

		public void addNode(int p) {
			ps.put(p, p);
		}

		public void union(int p, int q) {
			int pP = find(p);
			int pQ = find(q);
			if (pP == pQ) {
				return;
			}
			rank.putIfAbsent(pP, 0);
			rank.putIfAbsent(pQ, 0);
			if (rank.get(pP) > rank.get(pQ)) {
				ps.put(pQ, pP);
			} else if (rank.get(pP) < rank.get(pQ)) {
				ps.put(pP, pQ);
			} else {
				ps.put(pQ, pP);
				rank.put(pP, rank.get(pP));
			}
		}

		public boolean isConnected(int p, int q) {
			return find(p) == find(q);
		}

		private int find(int p) {
			if (!ps.containsKey(p)) {
				ps.put(p, p);
			}
			while (ps.get(p) != p) {
				int par = ps.get(ps.get(p));
				ps.put(p, par);
				p = par;
			}
			return p;
		}
	}
}
