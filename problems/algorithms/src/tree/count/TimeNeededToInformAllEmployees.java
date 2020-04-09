package tree.count;

import java.util.ArrayList;
import java.util.List;

/**
 * 1376
 *
 * ======
 *
 * Task.
 *
 * A company has n employees with a unique ID for each employee from 0 to n - 1.
 * The head of the company has is the one with headID.
 *
 * Each employee has one direct manager given in the manager array where
 * manager[i] is the direct manager of the i-th employee, manager[headID] = -1.
 * Also it's guaranteed that the subordination relationships have a tree
 * structure.
 *
 * The head of the company wants to inform all the employees of the company of
 * an urgent piece of news. He will inform his direct subordinates and they will
 * inform their subordinates and so on until all employees know about the urgent
 * news.
 *
 * The i-th employee needs informTime[i] minutes to inform all of his direct
 * subordinates (i.e After informTime[i] minutes, all his direct subordinates
 * can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the
 * urgent news.
 *
 * ======
 *
 * Source: Leetcode
 */
public class TimeNeededToInformAllEmployees {
	/**
	 * In other words - find max path sum.
	 */
	public static class Solution {
		private int ans = 0;
		private List<Integer>[] graph;

		public int numOfMinutes(int n, int headID, int[] manager, int[] time) {
			graph = new List[n];
			for (int i = 0; i < n; i++)
				graph[i] = new ArrayList<>();
			for (int i = 0; i < manager.length; i++)
				if (manager[i] != -1)
					graph[manager[i]].add(i);
			dfs(0, headID, time);
			return ans;
		}

		void dfs(int prev, int cur, int[] inf) {
			if (graph[cur].size() == 0)
				ans = Math.max(ans, prev + inf[cur]);
			for (int w : graph[cur])
				dfs(prev + inf[cur], w, inf);
		}
	}
}
