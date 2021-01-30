package tree.count;

import _commons.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113
 */
public class PathSumII
{
	class Solution1
	{
		private List<List<Integer>> paths = new ArrayList<>();

		public List<List<Integer>> pathSum(TreeNode root, int sum)
		{
			if (root == null) return paths;
			helper(new ArrayList<>(), root, sum);
			return paths;
		}

		private void backtrack(int sum, TreeNode root, List<Integer> path)
		{
			if (root.left == null && root.right == null && sum == 0)
			{
				paths.add(new ArrayList(path));
			}
			else
			{
				helper(path, root.left, sum);
				helper(path, root.right, sum);
			}
		}

		private void helper(List<Integer> l, TreeNode root, int sum)
		{
			if (root != null)
			{
				l.add(root.val);
				backtrack(sum - root.val, root, l);
				l.remove(l.size() - 1);
			}
		}
	}
}