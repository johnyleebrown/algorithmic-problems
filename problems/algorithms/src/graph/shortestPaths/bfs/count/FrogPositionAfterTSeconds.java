package graph.shortestPaths.bfs.count;

import util.test.Tester;

import java.util.*;

/**
 * 1377
 *
 * ======
 *
 * Task.
 *
 * Given an undirected tree consisting of n vertices numbered from 1 to n. A
 * frog starts jumping from the vertex 1. In one second, the frog jumps from its
 * current vertex to another unvisited vertex if they are directly connected.
 * The frog can not jump back to a visited vertex. In case the frog can jump to
 * several vertices it jumps randomly to one of them with the same probability,
 * otherwise, when the frog can not jump to any unvisited vertex it jumps
 * forever on the same vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i]
 * = [fromi, toi] means that exists an edge connecting directly the vertices
 * fromi and toi.
 *
 * Return the probability that after t seconds the frog is on the vertex
 * target.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FrogPositionAfterTSeconds
{
	/**
	 *
	 */
	public static class Solution
	{
		public double frogPosition(int n, int[][] edges, int time, int target)
		{
			Map<Integer, List<Integer>> m = new HashMap<>();
			for (int i = 1; i <= n; i++)
			{
				m.put(i, new ArrayList<>());
			}
			for (int[] edge : edges)
			{
				m.get(edge[0]).add(edge[1]);
				m.get(edge[1]).add(edge[0]);
			}

			boolean[] seen = new boolean[n + 1];
			seen[1] = true;

			Deque<Integer> qItem = new ArrayDeque<>();
			qItem.add(1);
			//optimize this
			Deque<Double> qProb = new ArrayDeque<>();
			qProb.add(1D);

			while (!qItem.isEmpty() && time-- >= 0)
			{
				int size = qItem.size();
				while (--size >= 0)
				{
					int currentItem = qItem.removeFirst();
					double currentProbability = qProb.removeFirst();

					int canGoToCount = 0;
					for (int w : m.get(currentItem))
					{
						if (seen[w])
							continue;
						seen[w] = true;
						qItem.addLast(w);
						canGoToCount++;
					}

					if (currentItem == target)
					{
						// edge case
						// if the time is not finished and we are at target
						// and there is someplace else to go, the ans is zero
						if (time >= 0 && canGoToCount > 0)
						{
							return 0;
						}

						return currentProbability;
					}

					//optimize this
					for (int i = 0; i < canGoToCount; i++)
					{
						qProb.addLast(currentProbability / canGoToCount);
					}
				}
			}

			return 0;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(7, Tester.ararFromString("[[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]]"),2,4).expect(0.16666666666666666)
				.add(22, Tester.ararFromString("[[2,1],[3,1],[4,2],[5,2],[6,4],[7,4],[8,4],[9,3],[10,4],[11,4],[12,5],[13,8],[14,9],[15,10],[16,7],[17,7],[18,15],[19,9],[20,11],[21,16],[22,19]]"),4,7).expect(0.0)
				.run();
	}
}