package graph.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1203
 */
public class SortItemsByGroupsRespectingDependencies
{
	/**
	 * Sort groups, then while sorting items, put them into places of sorted
	 * groups.
	 */
	class Solution
	{
		private int groupsTotalSize, sortGroupsCount;
		private Map<Integer, List<Integer>> groups = new HashMap<>(),
				groupsWithItems = new HashMap<>(),
				items = new HashMap<>();

		private int[] answer, sortedGroups;
		private boolean[] groupsSeen, itemsSeen;

		public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems)
		{
			init(n, m, group, beforeItems);

			if (hasCycle(groups, new boolean[groupsTotalSize], new boolean[groupsTotalSize]))
			{
				return new int[]{};
			}

			if (hasCycle(items, new boolean[n], new boolean[n]))
			{
				return new int[]{};
			}

			sortGroups();

			sortItems(group);

			postProcess();

			return answer;
		}

		private boolean hasCycle(Map<Integer, List<Integer>> graph,
		                         boolean[] seenOnStack, boolean[] seenOverall)
		{
			for (int v : graph.keySet())
			{
				if (hasCycleDFS(v, graph, seenOnStack, seenOverall))
				{
					return true;
				}
			}

			return false;
		}

		private boolean hasCycleDFS(int v, Map<Integer, List<Integer>> graph,
		                            boolean[] seenOnStack, boolean[] seenOverall)
		{
			if (seenOnStack[v])
			{
				return true;
			}

			if (seenOverall[v])
			{
				return false;
			}

			seenOnStack[v] = seenOverall[v] = true;

			for (int w : graph.get(v))
			{
				if (hasCycleDFS(w, graph, seenOnStack, seenOverall))
				{
					return true;
				}
			}

			seenOnStack[v] = false;
			return false;
		}

		private void postProcess()
		{
			int i = 0;
			for (int group : sortedGroups)
			{
				for (int x : groupsWithItems.get(group))
				{
					answer[i++] = x;
				}
			}
		}

		private void sortItems(int[] group)
		{
			for (int v : items.keySet())
			{
				sortItemsWithDfs(v, group);
			}
		}

		private void sortItemsWithDfs(int v, int[] group)
		{
			if (itemsSeen[v]) return;
			itemsSeen[v] = true;

			for (int w : items.get(v))
			{
				sortItemsWithDfs(w, group);
			}

			groupsWithItems.get(group[v]).add(0, v);
		}

		private void sortGroups()
		{
			for (int v : groups.keySet())
			{
				sortGroupsWithDfs(v);
			}
		}

		private void sortGroupsWithDfs(int v)
		{
			if (groupsSeen[v]) return;
			groupsSeen[v] = true;

			for (int w : groups.get(v))
			{
				sortGroupsWithDfs(w);
			}

			sortedGroups[sortGroupsCount--] = v;
		}

		private void init(int n, int m, int[] group, List<List<Integer>> beforeItems)
		{
			answer = new int[n];
			itemsSeen = new boolean[n];

			for (int i = 0; i < n; i++)
			{
				if (group[i] == -1)
				{
					group[i] = m++;
				}
			}

			groupsTotalSize = m;
			groupsSeen = new boolean[m];

			for (int i = 0; i < n; i++)
			{
				items.put(i, new ArrayList<>());
				groups.put(group[i], new ArrayList<>());
				groupsWithItems.put(group[i], new ArrayList<>());
			}

			sortedGroups = new int[groups.size()];
			sortGroupsCount = groups.size() - 1;

			int i = 0;
			for (List<Integer> l : beforeItems)
			{
				int groupOfI = group[i];

				for (int before : l)
				{
					items.get(before).add(i);

					int groupOfBefore = group[before];
					if (groupOfBefore == groupOfI)
					{
						continue;
					}
					groups.get(groupOfBefore).add(groupOfI);
				}

				i++;
			}
		}
	}
}