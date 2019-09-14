package medium.bfs.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 310
public class MinimumHeightTrees 
{
    /**
     * We start from every vertex of degree 1. We let the 
	 * pointers move the same speed. When two pointers meet, 
	 * we keep only one of them, until the last two pointers meet
     * or one step away we then find the roots. It is easy to see 
	 * that the last two pointers are from the two ends of the
	 * longest path in the graph. Remove the leaves, update the
	 * degrees of inner vertexes. Then remove the new leaves.
     * Doing so level by level until there are 2 or 1 nodes left.
     * Basically, the idea is to eat up all the leaves at the same
	 * time, until one/two leaves are left.
     */
    public static List<Integer> solution1(int n, int[][] edges) 
	{
        if (n == 1) return Collections.singletonList(0);

        // init graph
        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
		for (int[] edge : edges) 
		{
            adj.get(edge[0]).add(edge[1]);// O(1)
            adj.get(edge[1]).add(edge[0]);// O(1)
        }

        // find v with degree 1
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            if (adj.get(i).size() == 1) leaves.add(i);

        // until 2 leaves are left on a tree
        while (n > 2) 
		{
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            
			for (int i : leaves) 
			{
                // going to the next leave
                int j = adj.get(i).iterator().next();
                
				// removing connection to prev leave
                adj.get(j).remove(i);
                if (adj.get(j).size() == 1)
				{
                    // add if the node doesn't have any other edges
                    newLeaves.add(j);
				}
            }

            leaves = newLeaves;
        }

        return leaves;
    }
}

