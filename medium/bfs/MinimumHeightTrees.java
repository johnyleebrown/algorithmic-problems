package medium.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MinimumHeightTrees
{
    /**
     * TLE ~ 250ms
     * BFS, but not from the leaves
     * Optimization with memo could be done for visited paths
     */
    class Solution
    {
        public List<Integer> findMinHeightTrees(int n, int[][] edges)
        {
            if (n == 0) return new LinkedList<>();
            if (n == 1) return new LinkedList<>(Arrays.asList(0));
            if (n == 2) return new LinkedList<>(Arrays.asList(0, 1));

            // creating map for the graph
            Map<Integer, List<Integer>> graph = createMap(edges);

            Map<Integer, List<Integer>> minHeightRoots = new HashMap<>();
            int minHeight = Integer.MAX_VALUE;

            for (int key : graph.keySet())
            {
                if (graph.get(key).size() > 1)
                {
                    int maxHeight = 0;
                    Queue<Integer> q = new LinkedList<>(graph.get(key));
                    Set<Integer> seen = new HashSet<>();

                    while (!q.isEmpty())
                    {
                        maxHeight++;
                        // optimization of some sort
                        // break if the height of local tree is already bigger
                        if (maxHeight > minHeight) break;

                        int size = q.size();
                        for (int i = 0; i < size; i++)
                        {
                            int root = q.poll();
                            if (!seen.add(root)) continue;
                            q.addAll(graph.get(root));
                        }
                    }

                    minHeightRoots.putIfAbsent(maxHeight, new LinkedList<>());
                    minHeightRoots.get(maxHeight).add(key);
                    minHeight = Math.min(minHeight, maxHeight);
                }
            }

            return minHeightRoots.get(minHeight);
        }

        private Map<Integer, List<Integer>> createMap(int[][] edges)
        {
            Map<Integer, List<Integer>> graph = new HashMap<>();

            for (int[] edge : edges)
            {
                graph.putIfAbsent(edge[0], new LinkedList<>());
                graph.get(edge[0]).add(edge[1]);
                graph.putIfAbsent(edge[1], new LinkedList<>());
                graph.get(edge[1]).add(edge[0]);
            }

            return graph;
        }

    }

    /**
     * 6ms
     * Graph with LinkedList ~20ms (TLE)
     */
    class Solution2
    {
        public List<Integer> findMinHeightTrees(int n, int[][] edges)
        {
            // edge cases
            if (n == 0) return new LinkedList<>();
            if (n == 1) return new LinkedList<>(Collections.singletonList(0));

            // graph representation
            final List<Set<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) graph.add(new HashSet<>());
            for (int[] edge : edges)
            {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }

            // leaves list instead of regular queue
            // we add only those ones on the outer perimeter
            final List<Integer> leaves = new LinkedList<>();
            for (int i = 0; i < n; i++)
                if (graph.get(i).size() == 1)
                    leaves.add(i);

            // we assume that there will be either 1 or 2 leaves left
            while (n > 2)
            {
                int size = leaves.size();
                for (int i = 0; i < size; i++)
                {
                    // remove each leaf with degree=1
                    int leaf = leaves.remove(0);
                    n--;

                    for (int node : graph.get(leaf))
                    {
                        // remove itself from the node it is connected to
                        graph.get(node).remove(leaf);

                        // if the degree of the node is 1
                        // means that it is a leaf node
                        // we need to put it to leaves
                        if (graph.get(node).size() == 1) leaves.add(node);
                    }
                }
            }

            return leaves;
        }
    }


}
