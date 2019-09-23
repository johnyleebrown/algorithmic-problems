// 1203
class Solution 
{
    // topological sort, assuming item doesn't contain itself in before array
	// we need to add groups condition to topological sort
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) 
    {
        seen = new boolean[];    
    }
    
    private void postOrderTraverse(int v, boolean[] seen)
    {
        if (seen[]) return;
        seen[] = true;
		
		for (int w: g.get(v))
		{
			postOrderTraversal(w, seen);		
		}

		// set to the end

    }
}
