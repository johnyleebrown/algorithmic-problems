package backtracking.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 22
 */
public class GenerateParentheses
{
	private static class Solution
	{
		private List<String> ans = new ArrayList<>();
		private int n;
		private int target;

		public List<String> generateParenthesis(int n)
		{
			this.n = n;
			target = n * 2;
			gen("", 0, 0);
			return ans;
		}

		private void gen(String cur, int l, int r)
		{
			if (cur.length() == target)
				ans.add(cur);
			else
				if (l < n)
					gen(cur + "(", l + 1, r);
				if (l > r)
					gen(cur + ")", l, r + 1);
		}
	}
}