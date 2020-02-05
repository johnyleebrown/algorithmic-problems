package bfs.tree;

import util.ds.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 515
 */
public class FindLargestValueInEachTreeRow
{
	// O(n)
	// keep local maximum for each row
	class Solution
	{
		public List<Integer> largestValues(TreeNode root)
		{
			if (root == null) return new ArrayList<>();

			List<Integer> res = new ArrayList<>();
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);

			while (!queue.isEmpty())
			{
				int size = queue.size();
				int max = queue.peek().val;
				for (int i = 0; i < size; i++)
				{
					TreeNode curr = queue.poll();
					max = Math.max(max, curr.val);
					if (curr.left != null) queue.offer(curr.left);
					if (curr.right != null) queue.offer(curr.right);
				}

				res.add(max);
			}

			return res;
		}
	}

	public class Solution2
	{
		public List<Integer> largestValues(TreeNode root)
		{
			List<Integer> res = new ArrayList<>();
			helper(root, res, 0);
			return res;
		}

		private void helper(TreeNode root, List<Integer> res, int d)
		{
			if (root == null) return;
			if (d == res.size()) res.add(root.val);
			else res.set(d, Math.max(res.get(d), root.val));
			helper(root.left, res, d + 1);
			helper(root.right, res, d + 1);
		}
	}
}
