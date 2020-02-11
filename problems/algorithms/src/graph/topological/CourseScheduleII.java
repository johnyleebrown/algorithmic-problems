// 210
public class CourseScheduleII
{
	// Regular Topological Order
	class Solution 
	{
		private Map<Integer, List<Integer>> g;
		private boolean[] seen;
		private boolean[] isInRecursion;
		private int[] postOrder;
		private int c;
		private boolean[] postOrderSeen;

		public int[] findOrder(int numCourses, int[][] prerequisites) 
		{
			if (numCourses == 0) return new int[]{};

			init(numCourses);
			fillTheGraph(prerequisites, numCourses);

			if (graphHasCycle()) return new int[]{};

			return getTopologicalOrder();
		}

		private int[] getTopologicalOrder()
		{
			for (int v: g.keySet()) 
				dfs2(v);
			
			return postOrder;
		}

		private void dfs2(int v)
		{
			if (postOrderSeen[v]) return;
			
			postOrderSeen[v] = true;
			
			for (int w: g.getOrDefault(v, new ArrayList<>())) 
				dfs2(w);
			
			postOrder[c--] = v;
		}

		private boolean graphHasCycle()
		{
			for (int v: g.keySet()) 
				if (dfs(v)) return true;
			
			return false;
		}

		private boolean dfs(int v)
		{
			if (isInRecursion[v]) return true;
			if (seen[v]) return false;
			
			isInRecursion[v] = seen[v] = true;
			
			for (int w: g.getOrDefault(v, new ArrayList<>())) 
				if (dfs(w)) return true;
			
			isInRecursion[v] = false;
			return false;    
		}

		private void fillTheGraph(int[][] prerequisites, int n)
		{
			for (int i = 0; i < n; i++) g.put(i, new ArrayList<>());
			for (int[] p: prerequisites) g.get(p[1]).add(p[0]);
		}

		private void init(int n)
		{
			g = new HashMap<>();
			seen = new boolean[n];
			isInRecursion = new boolean[n];
			postOrder = new int[n];
			postOrderSeen = new boolean[n];
			c = n - 1;
		}
	}
}

