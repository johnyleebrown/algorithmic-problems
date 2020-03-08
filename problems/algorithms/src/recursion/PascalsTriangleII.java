package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 119
 */
public class PascalsTriangleII
{
	class Solution
	{
		public List<Integer> getRow(int c)
		{
			List<Integer> ans = new ArrayList<>();
			ans.add(1);
			return f(1, ans, c - 1);
		}

		List<Integer> f(int n, List<Integer> cur, int c)
		{
			if (c < 0)
				return cur;
			List<Integer> loc = new ArrayList<>();
			loc.add(1);
			for (int i = 1; i < n; i++)
				loc.add(cur.get(i - 1) + cur.get(i));
			loc.add(1);
			return f(n + 1, loc, c - 1);
		}
	}
}
