package graph.topological;

import java.util.*;

import static test.Out.sout;

// 1203
class Solution 
{
    private Map<Integer, Set<Integer>> groups = new HashMap<>();
	private Map<Integer, List<Integer>> groupsWithItems = new HashMap<>();
	private Map<Integer, List<Integer>> items = new HashMap<>();
	private int[] answer;
	private int[] sortedGroups;
	private int sortGroupsCount;
    private boolean[] groupsSeen;

	private void init(int n, int m, int[] group, List<List<Integer>> beforeItems)
	{
		answer = new int[n];
		
		for (int i = 0; i < n; i++)
		{
			items.put(i, new ArrayList<>());
            groups.put(group[i], new HashSet<>());
			groupsWithItems.put(group[i], new ArrayList<>());
		}

		sortedGroups = new int[groups.size()];
		sortGroupsCount = groups.size() - 1;
        groupsSeen = new boolean[groups.size() + 1];
        
		int i = 0;
		for (List<Integer> l: beforeItems)
		{
			int groupOfI = group[i];

			for (int before: l)
			{
				items.get(before).add(i);
				
				int groupOfBefore = group[before];
				groups.get(groupOfBefore).add(groupOfI);
			}

			i++;
		}
	}

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) 
    {
		init(n, m, group, beforeItems);

		if (hasCycle(items) || hasCycle(groups))
		{
			return answer;
		}
		
		sortGroups();
		//System.out.println(Arrays.toString(sortedGroups));

		sortItems(group);

		postProcess();

		return answer;
    }
   	
	private void postProcess()
	{
		int i = 0;

		for (int group: sortedGroups)
		{
			for (int x: groupsWithItems.get(group))
			{
				answer[i++] = x;
			}
		}
	}

	private void sortItems(int[] group)
	{
		for (int v: items.keySet())
		{
			sortItemsWithDfs(v, group);
		}
	}

	private void sortItemsWithDfs(int v, int[] group)
	{
		if (itemsSeen[v]) return;
		itemsSeen[v] = true;

		for (int w: items.get(v))
		{
			sortItemsWithDfs(w);
		}

		groupsWithItems.get(group[v]).add(0, v);
	}

	private void sortGroups()
	{
		for (int v: groups.keySet())
		{
			sortGroupsWithDfs(v);
		}
	}

	private void sortGroupsWithDfs(int v)
	{
		if (groupsSeen[v + 1]) return;
		groupsSeen[v + 1] = true;

		for (int w: groups.get(v))
		{
			sortGroupsWithDfs(w);
		}

		sortedGroups[sortGroupsCount--] = v;
	}
}

